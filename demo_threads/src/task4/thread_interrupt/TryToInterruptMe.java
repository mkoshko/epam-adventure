package task4.thread_interrupt;

import java.util.concurrent.TimeUnit;

public class TryToInterruptMe implements Runnable{
    public void run() {
        while(true){
            System.out.println("beep beep bop");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
        System.out.println("beep bop");
    }
}
