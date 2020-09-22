import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleChatServer {
    
    ArrayList clientOutputStreams;

    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }

    public void go(){           
        clientOutputStreams = new ArrayList();
        try(ServerSocket serverSock = new ServerSocket(5000);){         //作为服务器，接收来自5000端口的信息
            while(true){
                Socket clientSocket = serverSock.accept();              //返回一个客户通信地址和端口
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());   //到客户的输出流。字符流
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket)); //传入输出端口，建立另一个进程
                t.start();
                System.out.println("got a connection");
            }
        }catch (Exception e) {
            //TODO: handle exception
        }
    }

    class ClientHandler implements Runnable{

        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket clientSocket){      //传入参数是与客户通信地址和端口
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());  //到客户的输入流
                reader = new BufferedReader(isReader);      //到客户的字符流
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }

        public void run(){
            String message;
            try {
                while((message = reader.readLine()) != null){
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }

    public void tellEveryone(String message){
        Iterator it = clientOutputStreams.iterator();
        while(it.hasNext()){
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);        //将某个用户发送到服务器的信息传到输出流
                writer.flush();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
