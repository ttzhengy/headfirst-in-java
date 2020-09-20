import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoButton
{
	
	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	
	public static void main(String[] args)
	{
		TwoButton gui = new TwoButton();
		gui.go();
	}
	
	public void go()
	{
		JButton colorButton = new JButton("change color");
		JButton labelButton = new JButton("change label");
		MyDrawPanel panel = new MyDrawPanel();
		
		// 将两个按钮分别跟两个内部类相关
		colorButton.addActionListener(new ColorListener());
		labelButton.addActionListener(new LabelListener());
		label.setText("I'm a label");
		
		frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
		frame.getContentPane().add(BorderLayout.EAST, labelButton);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.WEST, label);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		frame.repaint();
	}
	
	// labelButton的内部类，继承ActionListener
	class LabelListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			label.setText("Ouch");
		}
	}
	
	// colorButton的内部类，继承ActionListener
	class ColorListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			frame.repaint();
		}
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