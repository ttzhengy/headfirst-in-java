import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui1B implements ActionListener
{
	JButton button;
	
	public static void main(String[] args)
	{
		SimpleGui1B gui = new SimpleGui1B();
		gui.go();
	}
	
	public void go()
	{
		JFrame frame = new JFrame();
		
		// button = new JButton("click me");
		// button.addActionListener(this);
		// frame.getContentPane().add(button);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(300, 300);
		// frame.setVisible(true);
		
		MyDrawPanel panel = new MyDrawPanel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		button.setText("I've been click!");
	}
}

class MyDrawPanel extends JPanel
{
	public void paintComponent(Graphics g)
	{
		// 矩形
		// g.setColor(Color.orange);
		// g.fillRect(20, 50, 100, 100);
		
		// 显示图片
		// Image image = new ImageIcon("111.jpg").getImage();
		// g.drawImage(image, 1, 1, this);
		
		// 在黑色背景上画随机颜色圆
		// g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// int R = (int) (Math.random() * 255);
		// int G = (int) (Math.random() * 255);
		// int B = (int) (Math.random() * 255);
		// Color randomcolor = new Color(R, G, B);
		// g.setColor(randomcolor);
		// g.fillOval(0, 0, this.getWidth(), this.getHeight());
		
		// 渐变圆
		// Graphics2D g2d = (Graphics2D) g;
		// GradientPaint gradient = new GradientPaint(70, 70, Color.blue, 150, 150, Color.orange);
		// g2d.setPaint(gradient);
		// g2d.fillOval(70, 70, 100, 100);
		
		// 随机渐变圆
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