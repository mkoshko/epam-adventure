package task3.thread_priority_min_norm_max;

import java.util.concurrent.TimeUnit;

public class Counter implements Runnable {
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " i = " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[" + Thread.currentThread().getName() + "]" + " lost his job");
    }
}
