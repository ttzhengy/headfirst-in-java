
public class PairTest1 {
    public static void main(String[] args) {
        // String[] words = {"Mary", "had", "a", "little", "lamb"};
        // Pair<String> mm = ArrayAlg.minmax(words);
        // System.out.println(mm.toString());

        int a = ArrayAlg1.getMiddle(3,5,1,8,6,2,10);
        System.out.println(a);
    }
}

class ArrayAlg1{
    public static Pair<String> minmax(String[] a) {     //调用泛型类
        String min = a[0];
        String max = a[0];
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

    public static <T> T getMiddle(T... a) {         //普通类中的泛型方法
        return a[a.length/2];
    }
}