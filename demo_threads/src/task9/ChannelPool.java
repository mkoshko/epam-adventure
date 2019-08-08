package task9;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ChannelPool<T> {
    private static final int POOL_SIZE = 5;
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    private final Queue<T> resources = new LinkedList<>();

    public ChannelPool(Queue<T> source) {
        resources.addAll(source);
    }
    public T getResource(long waitMillis) throws ResourceException {
        try {
            if (semaphore.tryAcquire(waitMillis, TimeUnit.MILLISECONDS)) {
                return resources.poll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new ResourceException("Time limit exceeded");
    }
    public void returnResources(T res) {
        resources.add(res);
        semaphore.release();
    }
}
