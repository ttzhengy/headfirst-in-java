import java.util.*;
import java.io.*;

public class Jukebox1{

    ArrayList<String> songList = new ArrayList<>(); 
    
    public static void main(String[] args) {
        new Jukebox1().go();
    }

    public void go(){
        String song;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("SongList.txt")))) {
            while((song = reader.readLine()) !=null){
                songList.add(song.split("/")[0]);
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    System.out.println(songList);
    }
}