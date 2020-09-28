import java.time.*;

public class PairTest2 {
    public static void main(String[] args) {
      LocalDate[] birthday = {
        LocalDate.of(1906, 12, 9),
        LocalDate.of(1815, 12, 10),
        LocalDate.of(1904, 12, 3),
        LocalDate.of(1910, 6, 22)};
    Pair<LocalDate> mm = ArrayAlg2.minmax(birthday);
    System.out.println(mm);  
    }
}

class ArrayAlg2{
    public static <T extends Comparable> Pair<T> minmax(T[] a) {        //限定类型变量
        T min = a[0];
        T max = a[0];
        if(a.length == 0 || a == null){
            return null;
        }else{
            
            for (int i = 0; i < a.length; i++) {
                if(a[i].compareTo(min) < 0){
                    min = a[i];
                }
                if(a[i].compareTo(max) >0){
                    max = a[i];
                }
            }
        }
        return new Pair<>(min, max);
    }
}
