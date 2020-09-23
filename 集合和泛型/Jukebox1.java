import java.util.*;
import java.io.*;

public class Jukebox1{

    ArrayList<Song> songList = new ArrayList<>(); 
    
    public static void main(String[] args) {
        new Jukebox1().go();
    }

    public void go(){
        getSong();
        System.out.println(songList);
        // Collections.sort(songList);
        // System.out.println(songList);

        ArtistCompare artistCompare = new ArtistCompare();
        Collections.sort(songList, artistCompare);

        System.out.println(songList);
    }

    public void getSong(){
        String song;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("SongList.txt")))) {
            while((song = reader.readLine()) !=null){
                addSong(song);
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public void addSong(String lineToParse){
        String[] tokens = lineToParse.split("/");
        Song song = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(song);
    }

    class Song {
        private String title;
        private String artist;
        private String rating;
        private String bpm;

        public Song(String t, String a, String r, String b){
            title = t;
            artist = a;
            rating = r;
            bpm = b;
        }

        public String getTitle(){
            return title;
        }

        public String getArtist(){
            return artist;
        }

        public String getRating(){
            return rating;
        }

        public String getBpm(){
            return bpm;
        }

        // public int compareTo(Song s){
        //     return title.compareTo(s.getTitle());
        // }

        public String toString(){
            return title + " " + artist + " " + rating + " " + bpm;
        }
    }

    class ArtistCompare implements Comparator<Song>{
        public int compare(Song a, Song b){
            return a.getArtist().compareTo(b.getArtist());
        }
    }
}