import java.io.*;
import java.net.*;
import java.util.*;

public class MusicServer {
    
    ArrayList<ObjectOutputStream> clientOutputSteams;       //存放所有客户端的out流

    public static void main(String[] args) {
        new MusicServer().go();
    }

    class ClientHandler implements Runnable{        //获取客户端的地址，读取in流，调用tellEveryone
        ObjectInputStream in;
        Socket clientSocket;

        public ClientHandler(Socket sock){
            clientSocket = sock;
        }

        public void run(){
            Object o1;
            Object o2;
            try{
                in = new ObjectInputStream(clientSocket.getInputStream());
                while((o1 = in.readObject()) != null){
                    o2 = in.readObject();
                    System.out.println("read two objects");
                    tellEveryone(o1, o2);
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }

    public void go(){           //监听端口并返回申请连接的端口，建立线程
        clientOutputSteams = new ArrayList<>();

        try(ServerSocket serverSocket = new ServerSocket(5000)){
            while(true){
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutputSteams.add(out);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        
    }

    public void tellEveryone(Object one, Object two){   //用迭代器对每一个out流输出
        Iterator it = clientOutputSteams.iterator();
        while(it.hasNext()){
            try {
                ObjectOutputStream out = (ObjectOutputStream) it;
                out.writeObject(one);
                out.writeObject(two);
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
