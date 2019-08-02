package task4.thread_daemons;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Thread bob = new Thread(new DaemonThread(), "Bob");
        Thread clop = new Thread(new DaemonThread(), "Clop");
        bob.setDaemon(true);
        clop.setDaemon(true);
        bob.start();
        clop.start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("spin " + i);
        }
        System.out.println("Spinman done his job");
    }
}
