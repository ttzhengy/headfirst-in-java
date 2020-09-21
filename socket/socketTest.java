import java.io.*;
import java.net.*;

public class socketTest{
    public static void main(String[] args) {
        socketTest test = new socketTest();
        test.go();
    }

    public void go(){
        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("Hello");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(reader.readLine());
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
