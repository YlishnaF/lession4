public class Main {
    static Object monitor = new Object();
    static volatile int currentNum = 1;
    static final int NUM = 5;
    public static void main(String[] args) {
        new Thread(()-> {
            try {
                for (int i = 0; i < NUM; i++) {
                    synchronized (monitor) {
                        while (currentNum != 1) {

                            monitor.wait();

                        }
                        System.out.println("A");
                        currentNum = 2;
                        monitor.notifyAll();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try {
                for (int i = 0; i < NUM; i++) {
                    synchronized (monitor) {
                        while (currentNum != 2) {

                            monitor.wait();

                        }
                        System.out.println("B");
                        currentNum = 3;
                        monitor.notifyAll();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try {
                for (int i = 0; i < NUM; i++) {
                    synchronized (monitor) {
                        while (currentNum != 3) {

                            monitor.wait();

                        }
                        System.out.println("C");
                        currentNum = 1;
                        monitor.notifyAll();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
