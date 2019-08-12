package by.koshko.task02.service;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.ServiceException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class fill main matrix diagonal using {@code ReentrantLock} for thread
 * synchronization.
 */
public final class MatrixFillDiagonal {
    /**
     * Path to a file with parameters for thread creation.
     */
    private String file;
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
     * @param threadProps path to file contains arguments for thread creation.
     */
    public MatrixFillDiagonal(final Matrix newMatrix,
                              final String threadProps) {
        matrix = newMatrix;
        file = threadProps;
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
    /**
     * Creates threads.
     *
     * @throws ServiceException when data for thread creation can't be loaded
     *                          from file.
     */
    private void initThreads() throws ServiceException {
        List<String[]> args = ThreadDataLoader.loadData(file);
        threads = new Thread[args.size()];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(
                    new ThreadFillDiagonal(Integer.parseInt(args.get(i)[0]),
                            matrix, pos), args.get(i)[1]);
        }
    }
    /**
     * Class used like a common resource for concurrent access.
     */
    static class MatrixDiagonalPosition {
        /**
         * Indicates current diagonal position.
         */
        private int position = 0;
        /**
         * Locker for concurrent access.
         */
        private ReentrantLock locker = new ReentrantLock();

        /**
         * Returns index of the next element in {@code Matrix} which must be
         * altered by a thread with thread unique value.
         * @return index of the next diagonal element.
         */
        int getPosition() {
            try {
                locker.lock();
                return position++;
            } finally {
                locker.unlock();
            }
        }
    }
}
