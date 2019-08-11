package service;

import entity.Matrix;
import exception.ServiceException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Solution with AtomicInteger.
 */
public final class MatrixFillDiagonal {
    /**
     * Hardcoded path to a file with parameters for thread creation.
     */
    private String file = "data/threads.txt";
    /**
     * {@code Matrix} object.
     */
    private Matrix matrix;
    /**
     * Array with {@code Thread} objects which will used for matrix processing.
     */
    private Thread[] threads;
    /**
     *
     */
    private MatrixDiagonalPosition pos = new MatrixDiagonalPosition();

    /**
     * Creates new {@code MatrixFillDiagonal} object.
     *
     * @param newMatrix matrix on which operations will be performed.
     */
    public MatrixFillDiagonal(final Matrix newMatrix) {
        matrix = newMatrix;
    }

    /**
     * Fills matrix diagonal with threads unique numbers.
     *
     * @throws ServiceException if some exception occurs while thread creation.
     */
    public void fill() throws ServiceException {
        initThreads();
        ExecutorService service = Executors.newFixedThreadPool(threads.length);
        for (Thread t : threads) {
            service.execute(t);
        }
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void initThreads() throws ServiceException {
        List<String[]> args = ThreadDataLoader.loadData(file);
        threads = new Thread[args.size()];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(
                    new ThreadFillDiagonal(Integer.parseInt(args.get(i)[0]),
                            matrix, pos), args.get(i)[1]);
        }
    }

    static class MatrixDiagonalPosition {
        /**
         * Indicates current diagonal position.
         */
        private int position = 0;
        /**
         * Locker for concurrent access.
         */
        private ReentrantLock locker = new ReentrantLock();
        public int getPosition() {
            try {
                locker.lock();
                return position++;
            } finally {
                locker.unlock();
            }
        }
    }
}
