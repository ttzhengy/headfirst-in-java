import java.time.*;

public class PairTest3 {
    public static void main(String[] args) {
        Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
        Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);

        ceo.setBonus(1000000);
        cfo.setBonus(500000);
        Manager[] managers = {ceo, cfo};

        Pair<Employee> result = new Pair<>();
        minmaxBonus(managers, result);
        System.out.println(result.getFirst() + "\n" + result.getSecond());

        maxminBonus(managers, result);
        System.out.println(result.getFirst() + "\n" + result.getSecond());
    }

    public static void printBuddies(Pair<? extends Employee> p) {       //通配符类型
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies");
    }

    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {     //超类限定通配符
        if(a.length == 0)return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 0; i < a.length; i++) {
            if(min.getBonus() > a[i].getBonus())min = a[i];
            if(max.getBonus() < a[i].getBonus())max = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
        minmaxBonus(a, result);
        ArrayAlg3.swap(result);
    }
}

class ArrayAlg3{
    public static boolean hasNull(Pair<?> p) {      //无限定通配符，测试是否null
        return p.getFirst() == null || p.getSecond() == null;
    }

    public static void swap(Pair<?> p) {
        swapHelper(p);
    }

    public static <T> void swapHelper(Pair<T> p) {      //不能将通配符作为类型，因此捕获通配符，转化为泛型方法
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}

class Employee{
    String name;
    int salary;
    LocalDate hiredDate;

    public Employee(String n, int s, int y, int m, int d){
        name = n;
        salary = s;
        hiredDate = LocalDate.of(y, m, d);
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Manager extends Employee{
    int bonus;

    public Manager(String n, int s, int y, int m, int d){
        super(n, s, y, m, d);
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}