import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import javax.sound.midi.*;
import java.util.*;
import javax.swing.Box;
import java.net.*;

public class BeatBox
{
	JFrame frame;
	ArrayList<JCheckBox> checkboxList;
	JPanel background;
	JTextField outcoming;
	JList incomingList;
	int nextNum;
	Sequence sequence;
	Sequencer sequencer;
	Track track;
	String userName;
	ObjectOutputStream out;
	ObjectInputStream in;
	int[] instruments = {34, 42, 46, 38, 49, 39, 50, 60, 
		70, 72, 64, 56, 58, 47, 67, 63};
	HashMap<String, boolean[]> otherSeqsMap = new HashMap<>();
	Vector<String> listVector = new Vector<>();
	
	
	public static void main(String[] args)
	{
		new BeatBox().startUp(args[0]);		//将命令行的输入作为用户名传入
	}

	public void startUp(String name){		//初始化socket，IO流，线程，MIDI和GUI
		userName = name;
		try(Socket sock = new Socket("127.0.0.1", 5000);){
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			Thread remote = new Thread(new RemoteReader());
			remote.start();
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}
		setUpMIDI();
		buildGUI();
	}
	
	public void buildGUI()
	{
		frame = new JFrame("Cyber BeatBox");
		checkboxList = new ArrayList<JCheckBox>();
		background = new JPanel(new BorderLayout());
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
        background.add(BorderLayout.WEST, leftBox());
		background.add(BorderLayout.CENTER, centerPanel());
		background.add(BorderLayout.EAST, rightBox());
		frame.getContentPane().add(background);
		
		setUpMIDI();
		
		frame.setBounds(50, 50, 300, 300);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public Box leftBox()
	{
		Box lBox = new Box(BoxLayout.Y_AXIS);
		String[] instrumentName = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", 
			"Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo",
			"Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", 
			"Low-mid Tom", "High Agogo", "Open Hi Conga"};
		for(String element:instrumentName)
		{
			lBox.add(new JLabel(element));
			lBox.add(Box.createGlue());
		}
		return lBox;
	}
	
	public JPanel centerPanel()
	{
		JPanel cPanel = new JPanel();
		GridLayout grid = new GridLayout(16, 16, 2, 1);
		cPanel.setLayout(grid);
		for(int i=0;i<256;i++)
		{
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			cPanel.add(c);
			checkboxList.add(c);
		}
		return cPanel;
	}
	
	public Box rightBox()
	{
		Box rBox = new Box(BoxLayout.Y_AXIS);
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new startListener());
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new stopListener());
		JButton tempoUpButton = new JButton("Tempo Up");
		tempoUpButton.addActionListener(new tempoUpListener());
		JButton tempoDownButton = new JButton("Tempo Down");
        tempoDownButton.addActionListener(new tempoDownListener());
        // JButton serializelt = new JButton("serializelt");
        // serializelt.addActionListener(new serializeltListener());
        // JButton restore = new JButton("restore");
		// restore.addActionListener(new restoreListener());

		JButton sendIt = new JButton("Send It");
		sendIt.addActionListener(new SendItListener());
		outcoming = new JTextField();
		incomingList = new JList();
		incomingList.addListSelectionListener(new myListSelectionListener());
		incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane theList = new JScrollPane(incomingList);
		
		rBox.add(startButton);
		rBox.add(stopButton);
		rBox.add(tempoUpButton);
        rBox.add(tempoDownButton);
        // rBox.add(serializelt);
		// rBox.add(restore);
		rBox.add(sendIt);
		rBox.add(outcoming);
		rBox.add(theList);
		
		return rBox;
	}
	
	public void setUpMIDI()
	{
		try
		{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		}catch(Exception ex){ex.printStackTrace();}

	}
	
	public void buildTrackAndStart()
	{
		int[] instrumentList = null;
		
		sequence.deleteTrack(track);
		track = sequence.createTrack();
		
		//
		for(int i = 0; i < 16; i++)			//逐行读取checkbox
		{
			instrumentList = new int[16];
			int key = instruments[i];
			
			for(int j = 0; j < 16; j++)		//逐列
			{
				JCheckBox jc = (JCheckBox) checkboxList.get(i*16 + j);
				if(jc.isSelected())			//checkbox被选中，则对应拍子发出声音
				{
					instrumentList[j] = key;
				}else
				{
					instrumentList[j] = 0;
				}	
			}
			makeTracks(instrumentList);
			track.add(makeEvent(176, 1, 127, 0, 16));
		}
		
		track.add(makeEvent(192, 9, 1, 0, 15));
		try
		{
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void makeTracks(int[] list)		//制作完整时间内的音轨
	{
		for(int i = 0; i < 16; i++)
		{
			int key = list[i];
			
			if(key != 0)
			{
				track.add(makeEvent(144, 9, key, 100, i));
				track.add(makeEvent(128, 9, key, 100, i+1));
			}
		}
	}
	
	public MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick)	//制作tick到tick+1时刻的声音
	{
		MidiEvent event= null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(command, channel, data1, data2);
			event = new MidiEvent(a, tick);
		}catch(Exception ex){ex.printStackTrace();}
		return event;
	}

	public class RemoteReader implements Runnable{		//另一个进程，实现读取服务器发送的信息

		boolean[] checkboxState;
			String nameToShow;
			Object obj;

		public void run(){
			try {
				while((obj = in.readObject()) != null){
					System.out.println("got a n object from server");
					System.out.println(obj.getClass());
					String nameToShow = (String) obj;		//获得服务器发送对象的类名
					checkboxState = (boolean[]) in.readObject();
					otherSeqsMap.put(nameToShow, checkboxState);	//按键值对写入
					listVector.add(nameToShow);
					incomingList.setListData(listVector);
				}
			} catch (Exception e) {
				//TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	class startListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			buildTrackAndStart();
		}
	}
	
	class stopListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			sequencer.stop();
		}
	}
	
	class tempoUpListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor*1.03));
		}
	}
	
	class tempoDownListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor*0.97));
		}
    }
    
    // class serializeltListener implements ActionListener{
    //     public void actionPerformed(ActionEvent ev){
    //         boolean[] checkBoxState = new boolean[256];
    //         for (int i = 0; i < 256; i++) {
    //             JCheckBox jc = (JCheckBox) checkboxList.get(i);
    //             if(jc.isSelected()){
    //                 checkBoxState[i] = true;
    //             }
    //         }

    //         try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("Checkbox.ser")))) {
    //             os.writeObject(checkBoxState);
    //         } catch (Exception e) {
    //             //TODO: handle exception
    //             e.printStackTrace();
    //         }
    //     }
    // }

    // class restoreListener implements ActionListener{
    //     public void actionPerformed(ActionEvent ev){
    //         boolean[] checkBoxState = null;
    //         JFileChooser fileChooser = new JFileChooser();
    //         fileChooser.showOpenDialog(frame);
    //         try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))) {
    //             checkBoxState = (boolean[]) is.readObject();
    //         } catch (Exception e) {
    //             //TODO: handle exception
    //             e.printStackTrace();
    //         }

    //         for (int i = 0; i < 256; i++) {
    //             JCheckBox check = (JCheckBox) checkboxList.get(i);
    //             if(checkBoxState[i]){
    //                 check.setSelected(true);
    //             }else{
    //                 check.setSelected(false);
    //             }
    //         }
    //         sequencer.stop();
    //         buildTrackAndStart();
    //     }
	// }

	class SendItListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			boolean[] checkBoxState = new boolean[256];
			for (int i = 0; i < 256; i++) {		//读此刻的checkbox状态
				JCheckBox jc = checkboxList.get(i);
				if(jc.isSelected()){
					checkBoxState[i] = true;
				}
			}
			String messageToSend;
			try  {
				out.writeObject(userName + nextNum++ + ":" + outcoming.getText());
				out.writeObject(checkBoxState);
			} catch (Exception e) {
				//TODO: handle exception
				System.out.println("Could not send it to server.");
			}
		}
	}
	
	class myListSelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent ev){
			if(!ev.getValueIsAdjusting()){		//判断是否改变选项
				String selected = (String) incomingList.getSelectedValue();
				if(selected != null){
					boolean[] selectedState = (boolean[]) otherSeqsMap.get(selected);
					changeSequence(selectedState);
					sequencer.stop();
					buildTrackAndStart();
				}
			}
		}
	}

	public void changeSequence(boolean[] checkboxState){
		for (int i = 0; i < 256; i++) {
			JCheckBox jc = (JCheckBox) checkboxList.get(i);
			if(checkboxState[i]){
				jc.setSelected(true);
			}else{
				jc.setSelected(false);
			}
		}
	}
}