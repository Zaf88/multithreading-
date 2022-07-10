public class App {


        public App(int lifetime) throws InterruptedException {
            A a = new A();
            T1 t1 = new T1(a);
            T2 t2 = new T2(a);
            t1.start();
            t2.start();
            Thread.sleep(lifetime * 1000L );
            t1.interrupt();
            t2.interrupt();
        }


        static class T1 extends Thread {

            private final A app;

            public T1(A app) {
                this.app = app;
            }

            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    this.app.add();
                }
            }
        }

        static class T2 extends Thread {
            private final A app;

            public T2(A app) {
                this.app = app;
            }

            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    int n;
                    try {
                        n = app.last();
                        System.out.println(n);
                        if (n % 5 == 0)
                            System.out.println("5 seconds passed");
                        if (n % 7 == 0)
                            System.out.println("7 seconds passed");
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        static class A {
            private int d = 0;

            public synchronized void add() {
                this.d++;
                notify();
            }

            public synchronized int last() throws InterruptedException {
                wait();
                return this.d;
            }
        }
    }

