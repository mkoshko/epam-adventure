package task7._stampedlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class CommonResource {
    int i = 0;
    StampedLock lock = new StampedLock();

    public int getI() {
        long stamp = lock.tryOptimisticRead();
        var temp = i;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                temp = i;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return temp;
    }

    public void putI() {
        long stamp = lock.writeLock();
        try {
            i++;
            System.out.println("value changed by"
                    + Thread.currentThread().getName() + " to " + i);
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
