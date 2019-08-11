package by.koshko.task02.service;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.ServiceException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Solution with AtomicInteger.
 */
public final class MatrixFillDiagonalPhaser {
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
    private MatrixFillDiagonal.MatrixDiagonalPosition pos
            = new MatrixFillDiagonal.MatrixDiagonalPosition();
    /**
     * Phaser.
     */
    private Phaser phaser;
    /**
     * Creates new {@code MatrixFillDiagonal} object.
     *
     * @param newMatrix matrix on which operations will be performed.
     */
    public MatrixFillDiagonalPhaser(final Matrix newMatrix) {
        matrix = newMatrix;
    }

    /**
     * Fills matrix diagonal with threads unique numbers.
     *
     * @throws ServiceException if some exception occurs while thread creation.
     */
    public void fill() throws ServiceException {
        phaser = new Phaser();
        phaser.register();
        initThreads();
        ExecutorService service = Executors.newFixedThreadPool(threads.length);
        for (Thread t : threads) {
            service.execute(t);
        }
        service.shutdown();
        while (phaser.getRegisteredParties() > 1) {
            phaser.arriveAndAwaitAdvance();
        }
        phaser.arriveAndDeregister();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            phaser.forceTermination();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void initThreads() throws ServiceException {
        List<String[]> args = ThreadDataLoader.loadData(file);
        threads = new Thread[args.size()];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(
                new ThreadFillDiagonalPhaser(Integer.parseInt(args.get(i)[0]),
                     matrix, pos, phaser), args.get(i)[1]);
        }
    }

    static class MatrixDiagonalPosition {
        /**
         * Indicates current diagonal position.
         */
        private AtomicInteger position = new AtomicInteger(0);
        public int getPosition() {
            return position.getAndIncrement();
        }
    }
}
