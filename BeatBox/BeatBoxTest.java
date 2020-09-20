import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.sound.midi.*;

public class BeatBoxTest
{
	public static void main(String[] args)
	{
		BeatBoxTest t = new BeatBoxTest();
		t.test();
	}
	
	public void test()
	{
		JFrame frame = new JFrame();
		JPanel cPanel = new JPanel();
		GridLayout grid = new GridLayout(16, 16, 2, 1);
		// grid.setHgap(2);
		// grid.setVgap(1);
		cPanel.setLayout(grid);
		for(int i=0;i<256;i++)
		{
			cPanel.add(new Checkbox());
		}
		
		frame.getContentPane().add(BorderLayout.CENTER, cPanel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}