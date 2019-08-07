package task7._reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Store {
    private int stored = 0;
    private final int capacity = 3;
    ReentrantLock lock;
    Condition condition;

    public Store(ReentrantLock lock) {
        this.lock = lock;
        condition = lock.newCondition();
    }

    public  void getProduct() {
        try {
            while(!lock.tryLock()) {
                System.out.println("Consumer trying to lock");
                TimeUnit.MILLISECONDS.sleep(200);
            }
            while(stored < 1) {
                System.out.println("Consumer is waiting");
                condition.await();
            }
            stored--;
            System.out.println("покупатель купил 1 товар");
            System.out.println("товаров на складе " + stored);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void putProduct() {
        try {
            while(!lock.tryLock()) {
                System.out.println("Produces trying to lock");
                TimeUnit.MILLISECONDS.sleep(200);
            }
            while(stored == capacity) {
                System.out.println("Producer is waiting");
                condition.await();
            }
            stored++;
            System.out.println("производитель добавил 1 товар на склад");
            System.out.println("товаров на складе " + stored);
            condition.signal();
        } catch (InterruptedException e) {
            System.out.println("что-то пошло не так");
        } finally {
            lock.unlock();
        }
    }
}
