import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class SimpleChatClientA {
    JTextField outgoing;
    JTextArea incoming;
    PrintWriter writer;
    BufferedReader reader;
    Socket sock;

    public static void main(String[] args) {
        SimpleChatClientA A = new SimpleChatClientA();
        A.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton send = new JButton("Send");
        outgoing = new JTextField(20);
        incoming = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(incoming);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        incoming.setEditable(false);
        incoming.setWrapStyleWord(true);
        incoming.setLineWrap(true);

        send.addActionListener(new SendButtonListener());
        panel.add(scrollPane);
        panel.add(outgoing);
        panel.add(send);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());     //将读取服务器信息的方法放到另一个线程
        readerThread.start();
    }

    private void setUpNetworking(){
        try{
            sock = new Socket("127.0.0.1", 5000);
            InputStreamReader steamReader = new InputStreamReader(sock.getInputStream());   
            reader = new BufferedReader(steamReader);
            writer = new PrintWriter(sock.getOutputStream());
            writer.println("network established");
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
        }

    }

    class SendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            try {
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    class IncomingReader implements Runnable{       //建立一个类，用于执行读取服务器信息
        public void run(){
            String message;
            try {
                while((message = reader.readLine()) != null){
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                }
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }    
    }
}


