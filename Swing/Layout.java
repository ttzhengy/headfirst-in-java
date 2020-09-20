import javax.swing.*;
import java.awt.*;

public class Layout
{
	public static void main(String[] args)
	{
		Layout L = new Layout();
		L.BoxLayout();
	}
	
	public void BorderLayout()
	{
		JFrame frame = new JFrame();
		
		JButton east = new JButton("East");
		JButton west = new JButton("West");
		JButton north = new JButton("North");
		JButton south = new JButton("South");
		JButton center = new JButton("Center");
		
		frame.getContentPane().add(BorderLayout.EAST, east);
		frame.getContentPane().add(BorderLayout.WEST, west);
		frame.getContentPane().add(BorderLayout.NORTH, north);
		frame.getContentPane().add(BorderLayout.SOUTH, south);
		frame.getContentPane().add(BorderLayout.CENTER, center);
		
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public void FlowLayout()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setBackground(Color.darkGray);
		
		JButton button = new JButton("shock me");
		JButton buttontwo = new JButton("bliss");
		panel.add(button);
		panel.add(buttontwo);
		
		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.setSize(250, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public void BoxLayout()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setBackground(Color.darkGray);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton button = new JButton("shock me");
		JButton buttontwo = new JButton("bliss");
		panel.add(button);
		panel.add(buttontwo);
		
		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.setSize(250, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}