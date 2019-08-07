package task5._reentrantlock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

public class CodeChanger implements Runnable {

    CommonResource code;
    ReadWriteLock locker;

    public CodeChanger(CommonResource code, ReadWriteLock lock) {
        this.code = code;
        this.locker = lock;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                locker.writeLock().lock();
                System.out.println("General Mihail is changing code");
                TimeUnit.SECONDS.sleep(2);
                code.changeCode(UUID.randomUUID().toString());
                locker.writeLock().unlock();
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            locker.writeLock().unlock();
        }
    }

}
