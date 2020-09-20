import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class SwingTest
{
	// JTextField field = new JTextField();
	// JTextArea area = new JTextArea();
	// JCheckBox checkbox = new JCheckBox();
	// JList list = new JList();
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JTextField field = new JTextField("Your name");
	JTextArea area = new JTextArea(10, 20);
	JCheckBox checkbox = new JCheckBox("Unselected");
	JList list = new JList();
	
	public static void main(String[] args)
	{
		SwingTest t = new SwingTest();
		t.list();
	}
	
	public void textfield()
	{
		JLabel label = new JLabel("Label");
		panel.setBackground(Color.darkGray);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(label);
		panel.add(field);
		
		field.addActionListener(new TextFieldListener());
		
		frame.getContentPane().add(BorderLayout.NORTH, panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public void textarea()
	{
		JButton button = new JButton("click me");
		JScrollPane scroller = new JScrollPane(area);
		area.setLineWrap(true);
		
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroller);
		
		button.addActionListener(new ButtonListener());
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public void checkbox()
	{
		checkbox.addItemListener(new CheckBoxListener());
		
		panel.setBackground(Color.darkGray);
		panel.add(checkbox);
		
		frame.getContentPane().add(BorderLayout.NORTH, panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public void list()
	{
		String[] listEntries = {"Java", "C", "C++", "PHP", "Python"};
		list.setListData(listEntries);
		
		JScrollPane scroller = new JScrollPane(list);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroller);
		
		list.setVisibleRowCount(4);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		list.addListSelectionListener(new ListListener());
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	class ListListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent lse)
		{
			if(!lse.getValueIsAdjusting())
			{
				String seletion = (String) list.getSelectedValue();
				System.out.println(seletion);
			}
		}
	}

	class CheckBoxListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent ev)
		{
			if(checkbox.isSelected())
			{
				checkbox.setText("Selected");
			}else
			{
				checkbox.setText("Unselected");
			}
		}
	}
	
	//对textfield按回车后的动作
	class TextFieldListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				System.out.println(field.getText());
				field.setText("");
				field.requestFocus();
			}catch(Exception ex)
			{ex.printStackTrace();}
		}
		
	}
	
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			area.append("button clicked \n");
		}
	}
}