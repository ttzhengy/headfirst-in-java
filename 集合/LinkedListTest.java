import java.util.*;

public class LinkedListTest{
    List<String> a = new LinkedList<>();
    List<String> b = new LinkedList<>();

    public static void main(String[] args) {
        new LinkedListTest().go();
    }

    public void go() {
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");
        b.add("Bod");
        b.add("Doug");
        b.add("France");
        b.add("Gloria");

        insert();
        deleteInterval();
    }

    public void insert() {
        ListIterator<String> iter1 = a.listIterator();
        Iterator<String> iter2 = b.iterator();

        while(iter2.hasNext()){
            if(iter1.hasNext()){
                iter1.next();
            }
            iter1.add(iter2.next());
        }
        System.out.println(a);
    }

    public void deleteInterval() {
        Iterator<String> iter = b.iterator();
        while(iter.hasNext()){
            iter.next();
            if(iter.hasNext()){
                iter.next();
                iter.remove();
            }
        }
        System.out.println(b);
    }
}