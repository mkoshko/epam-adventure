package task7._reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock locker = new ReentrantLock();
        Store store = new Store(locker);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(new Consumer(store));
        service.execute(new Producer(store));
        service.shutdown();
        System.out.println(service.isShutdown());
        System.out.println("Main end");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
    }
}
