package task5._reentrantlock;

import java.awt.event.TextEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static void main(String[] args) {
        CommonResource cr = new CommonResource();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new RussianHeckers(cr, lock, "Vladimir" + i));
        }
        executorService.submit(new CodeChanger(cr, lock));
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
