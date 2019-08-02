package task4.thread_interrupt;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        TryToInterruptMe tryToInterruptMe = new TryToInterruptMe();
        Thread thread = new Thread(tryToInterruptMe);
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
