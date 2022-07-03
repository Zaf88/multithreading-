public abstract class MyThread {
    public abstract void run();

    {
        try {
            Thread.sleep(7000L);
            System.out.println("7 seconds left");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}