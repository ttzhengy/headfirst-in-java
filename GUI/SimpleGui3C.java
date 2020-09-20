import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui3C implements ActionListener
{
	
	JFrame frame = new JFrame();
	
	public static void main(String[] args)
	{
		SimpleGui3C gui = new SimpleGui3C();
		gui.go();
	}
	
	public void go()
	{
		JButton button = new JButton("change color");
		MyDrawPanel panel = new MyDrawPanel();
		
		
		button.addActionListener(this);
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		frame.repaint();
	}
}

class MyDrawPanel extends JPanel
{
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		int R = (int) (Math.random() * 255);
		int G = (int) (Math.random() * 255);
		int B = (int) (Math.random() * 255);
		Color startColor = new Color(R, G, B);
		R = (int) (Math.random() * 255);
		G = (int) (Math.random() * 255);
		B = (int) (Math.random() * 255);
		Color endColor = new Color(R, G, B);
		GradientPaint gradient = new GradientPaint(70, 70, startColor, 150, 150, endColor);
		g2d.setPaint(gradient);
		g2d.fillOval(70, 70, 100, 100);
	}
}