package task8.common_res;

import java.util.concurrent.Semaphore;

public class CountThread implements Runnable {
    Semaphore sem;
    CommonResource resource;

    public CountThread(Semaphore sem, CommonResource resource) {
        this.sem = sem;
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " waiting permission");
            sem.acquire();
            resource.i = 1;
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + resource.i);
                resource.i++;
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " releasing permission");
            sem.release();
        }
    }
}
