package task4.thread_disable;

import java.util.concurrent.TimeUnit;

public class ForrestGump implements Runnable {
    private boolean isActive = true;
    public void disable() {
        isActive = false;
    }
    public void run() {
        while(isActive) {
            System.out.println("running");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("ouch... stumbled");
            }
        }
        System.out.printf("Forest %s is tired. \n", Thread.currentThread().getName());
    }
}
