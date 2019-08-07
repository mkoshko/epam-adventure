package task5._reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

public class RussianHeckers implements Runnable {

    CommonResource code;
    ReadWriteLock locker;
    String name;

    public RussianHeckers(CommonResource code, ReadWriteLock lock, String name) {
        this.code = code;
        this.locker = lock;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                while(!locker.readLock().tryLock()) {
                    System.out.println(name + " cant steal the code");
                    TimeUnit.SECONDS.sleep(1);
                }
                System.out.println("hecker" + name + "is stealing code");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Russian \"heckers\""+ name + "again stole nuclear launcher codes: " + code.getCode());
                locker.readLock().unlock();
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            locker.readLock().unlock();
        }
    }
}
