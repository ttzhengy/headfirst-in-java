import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class SimpleChatClientA {
    JTextField text;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args) {
        SimpleChatClientA A = new SimpleChatClientA();
        A.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton send = new JButton("Send");
        text = new JTextField(20);
        send.addActionListener(new SendButtonListener());
        panel.add(text);
        panel.add(send);
        frame.getContentPane().add(BorderLayout.CENTER, panel);

        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        setUpNetworking();
    }

    private void setUpNetworking(){
        try{
            sock = new Socket("127.0.0.1", 5000);
            PrintWriter writer = new PrintWriter(sock.getOutputStream());
            writer.println("network established");
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

    }

    class SendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            try {
                writer.println(text.getText());
                writer.flush();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            text.setText("");
            text.requestFocus();
        }
    }
}
