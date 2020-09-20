import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.sound.midi.*;
import java.util.*;

public class BeatBox
{
	JFrame frame;
	ArrayList<JCheckBox> checkboxList;
	JPanel background;
	Sequence sequence;
	Sequencer sequencer;
	Track track;
	int[] instruments = {34, 42, 46, 38, 49, 39, 50, 60, 
		70, 72, 64, 56, 58, 47, 67, 63};
	
	public static void main(String[] args)
	{
		BeatBox bbox = new BeatBox();
		bbox.buildGUI();
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
		
		rBox.add(startButton);
		rBox.add(stopButton);
		rBox.add(tempoUpButton);
		rBox.add(tempoDownButton);
		
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
		for(int i = 0; i < 16; i++)
		{
			instrumentList = new int[16];
			int key = instruments[i];
			
			for(int j = 0; j < 16; j++)
			{
				JCheckBox jc = (JCheckBox) checkboxList.get(i*16 + j);
				if(jc.isSelected())
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
	
	public void makeTracks(int[] list)
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
	
	public MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick)
	{
		MidiEvent event= null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(command, channel, data1, data2);
			event = new MidiEvent(a, tick);
		}catch(Exception ex){ex.printStackTrace();}
		return event;
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
}