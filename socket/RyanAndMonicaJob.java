

public class RyanAndMonicaJob implements Runnable{

    BankAccount account = new BankAccount();

    public static void main(String[] args) {
        Runnable runner = new RyanAndMonicaJob();
        Thread a = new Thread(runner);
        Thread b = new Thread(runner);
        a.setName("Ryan");
        b.setName("Monica");
        a.start();
        b.start();
    }

    public void run(){
        for (int i = 0; i < 10; i++) {
            makeWithdrawal(10);
            if(account.balance < 0){
                System.out.println("OverDrawn!");
            }
        }
    }

    private synchronized void makeWithdrawal(int amount){       //关键字synchronized 同步化
        if(account.balance >= amount){
            System.out.println(Thread.currentThread().getName() + " is about to withdraw.");
            try {
                System.out.println(Thread.currentThread().getName() + " is going to sleep.");
                // Thread.sleep(500);
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " woke up.");
            account.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " completes to withdral");
        }else{
            System.out.println("Sorry, not enough for " + Thread.currentThread().getName());
        }
    }
}

class BankAccount{
    int balance = 100;

    public int getBalance(){
        return balance;
    }
    
    public void withdraw(int amount){
        balance -= amount;
    }
}
