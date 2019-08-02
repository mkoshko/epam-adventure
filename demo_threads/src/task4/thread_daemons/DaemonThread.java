package task4.thread_daemons;

import java.util.concurrent.TimeUnit;

public class DaemonThread implements Runnable{
    public void run() {
        while(true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " spins a spinner");
        }
    }
}
