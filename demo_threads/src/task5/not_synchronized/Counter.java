package task5.not_synchronized;

import java.util.concurrent.TimeUnit;

public class Counter implements Runnable {
    CommonResource cr = null;

    public Counter(CommonResource cr) {
        this.cr = cr;
    }

    public void run() {
        cr.x = 1;
        for (int i = 1; i <= 20; i++) {
            System.out.printf("%s i = %d \n", Thread.currentThread().getName(), cr.x);
            cr.x++;
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
