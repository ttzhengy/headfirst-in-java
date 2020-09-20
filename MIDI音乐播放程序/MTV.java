import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MTV 
{
	public static void main(String[] args)
	{
		MusicPlayer mplay = new MusicPlayer();
		mplay.play();
	}
}

class MusicPlayer 
{
	static JFrame frame = new JFrame("My First Music Video");
	static MyDrawPanel panel;
	
	public void setUpGUI()
	{
		panel = new MyDrawPanel();
		frame.setContentPane(panel);
		frame.setBounds(30, 30, 300, 300);
		frame.setVisible(true);
	}
	
	public void play()
	{
		setUpGUI();
		try
		{
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			int[] eventsIWant = {127};
			player.addControllerEventListener(panel, eventsIWant);
			
			for(int i = 5; i < 65; i+=2)
			{
				track.add(makeEvent(144, 9, i, 100, i));
				track.add(makeEvent(176, 1, 127, 0, i));
				track.add(makeEvent(128, 9, i, 100, i+2));
			}
			player.setSequence(seq);
			player.setTempoInBPM(120);
			player.start();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick)
	{
		MidiEvent event= null;
		try{
			ShortMessage msg = new ShortMessage();
			msg.setMessage(command, channel, data1, data2);
			event = new MidiEvent(msg, tick);
		}catch(Exception ex){}
		return event;
	}
	
	class MyDrawPanel extends JPanel implements ControllerEventListener
	{
		boolean msg = false;
		
		public void controlChange(ShortMessage event)
		{
			msg = true;
			repaint();
		}
		
		public void paintComponent(Graphics g)
		{
			if(msg)
			{
				Graphics2D g2d = (Graphics2D) g;
				int R = (int) (Math.random() * 255);
				int G = (int) (Math.random() * 255);
				int B = (int) (Math.random() * 255);
				g.setColor(new Color(R, G, B));
				
				int x = (int) (Math.random() * 40 + 10);
				int y = (int) (Math.random() * 40 + 10);
				int width = (int) (Math.random() * 120 + 10);
				int height = (int) (Math.random() * 120 + 10);
				g.fillRect(x, y, width, height);
				msg = false;
			}
		}
	}
}

