package task8.common_res;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CommonResource res = new CommonResource();
        Semaphore semaphore = new Semaphore(4);
        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            service.execute(new CountThread(semaphore, res));
        }
        service.shutdown();
        while(!service.isTerminated()) {
            Thread.sleep(1000);
        }
    }
}
