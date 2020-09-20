import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleAnimation
{
	int x = 20;
	int y = 50;
	JFrame frame = new JFrame();
	
	public static void main (String[] args)
	{
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}
	
	public void go()
	{
		MyDrawPanel panel = new MyDrawPanel();
		
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		for(int i = 0; i < 130; i++)
		{
			x+=3;
			y+=3;
			panel.repaint();
			
			// 延迟
			try
			{
				Thread.sleep(50);
			}catch(Exception ex){}
		}
	}
	
	class MyDrawPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(Color.orange);
			g.fillOval(x, y, 40, 40);	//在内部类里调用外部类的参数
		}
	}
}