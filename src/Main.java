import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
//        Thread myThread = new Thread();
//        try {
//            Thread.sleep(5000L);
//            System.out.println("5 seconds passed");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//
//                        }
//            MyThread newMyRunnable = new MyThread() {
//                @Override
//                public void run() {
//                }
//            };
//            newMyRunnable.run();
//            myThread.start();
//            System.out.println("Thread test");
//        }
//    }
//        Thread myThread = new Thread();
//        try {
//            Thread.sleep(5000L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Loader");
//        try {
//            if (Thread.sleep(5000L)) {
//            }
//            }catch(InterruptedException e){
//                throw new RuntimeException(e);
//                System.out.println(" ");
//
        class Consumer implements Runnable
        {
            private final List<Integer> taskQueue;

            public Consumer(List<Integer> sharedQueue)
            {
                this.taskQueue = sharedQueue;
            }

            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        consume();
                    } catch (InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }

            private void consume() throws InterruptedException
            {
                synchronized (taskQueue)
                {
                    while (taskQueue.isEmpty())
                    {
                        System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                        taskQueue.wait();
                    }
                    Thread.sleep(7000L);
                    int i = (Integer) taskQueue.remove(0);
                    System.out.println("Consumed: " + i);
                    taskQueue.notifyAll();
                }
                class Producer implements Runnable
                {
                    private final List<Integer> taskQueue;
                    private final int           MAX_CAPACITY;

                    public Producer(List<Integer> sharedQueue, int size)
                    {
                        this.taskQueue = sharedQueue;
                        this.MAX_CAPACITY = size;
                    }

                    @Override
                    public void run()
                    {
                        int counter = 0;
                        while (true)
                        {
                            try
                            {
                                produce(counter++);
                            }
                            catch (InterruptedException ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                    }

                    private void produce(int i) throws InterruptedException
                    {
                        synchronized (taskQueue)
                        {
                            while (taskQueue.size() == MAX_CAPACITY)
                            {
                                System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                                taskQueue.wait();
                            }

                            Thread.sleep(7000L);
                            taskQueue.add(i);
                            System.out.println("Produced: " + i);
                            taskQueue.notifyAll();
                        }
                    }
                }}}}}