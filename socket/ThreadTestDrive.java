public class ThreadTestDrive {
    public static void main(String[] args) {
        Runnable threadJob = new MyRunnable();
        Thread myThread = new Thread(threadJob);
        myThread.start();
        System.out.println("aaa");
    }
}

class MyRunnable implements Runnable{   //作为内部类会报错
        public void run(){
            go();
        }

        public void go(){
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            doMore();
        }

        public void doMore(){
            System.out.println("bbb");
        }
    }