import java.util.*;

public class TestTree {
    public static void main(String[] args) {
        new TestTree().go();
    }

    public void go(){
        Book one = new Book("ddd");
        Book two = new Book("bbb");
        Book three = new Book("ccc");

        TreeSet<Book> tree = new TreeSet<>();
        tree.add(one);
        tree.add(two);
        tree.add(three);
        System.out.println(tree);
    }

    class Book implements Comparable<Book>{
        String title;

        public Book(String t){
            title = t;
        }

        public int compareTo(Book b){
            return title.compareTo(b.title);
        }

        public String toString(){
            return title;
        }
    }
}
