import java.util.*;
import java.io.*;

public class Jukebox4 {
    
    ArrayList<Song> songList = new ArrayList<>();

    public static void main(String[] args) {
        new Jukebox4().go();
    }

    public void go(){
        getSong();
        System.out.println(songList);
        ArtistCompare artistCompare = new ArtistCompare();
        TitleCompare titleCompare = new TitleCompare();
        Collections.sort(songList, titleCompare);
        System.out.println(songList);

        // HashSet<Song> songSet = new HashSet<>();
        // songSet.addAll(songList);

        TreeSet<Song> songSet = new TreeSet<>();
        songSet.addAll(songList);

        System.out.println(songSet);
    }

    public void getSong(){
        String song;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("SongListMore.txt")))) {
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

    class Song implements Comparable{
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

        public String toString(){
            return title + " " + artist + " " + rating + " " + bpm;
        }

        public int compareTo(Object aSong){
            Song s = (Song) aSong;
            return title.compareTo(s.getTitle());
        }

        public boolean equals(Object aSong){
            Song s = (Song) aSong;
            return title.equals(s.getTitle());
        }

        public int hashCode(){
            return title.hashCode();
        }
    }

    class ArtistCompare implements Comparator<Song>{
        public int compare(Song a, Song b){
            return a.getArtist().compareTo(b.getArtist());
        }
    }

    class TitleCompare implements Comparator<Song>{
        public int compare(Song a, Song b){
            return a.getTitle().compareTo(b.getTitle());
        }
    }
}
