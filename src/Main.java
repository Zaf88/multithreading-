public class Main {

    public static void main(String[] args) {
        Thread myThread = new Thread();
        try {
            Thread.sleep(5000L);
            System.out.println("5 seconds left");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyThread newMyRunnable = new MyThread() {
            @Override
            public void run() {

            }
        };
                newMyRunnable.run();
                myThread.start();
                System.out.println("Thread test");}}

