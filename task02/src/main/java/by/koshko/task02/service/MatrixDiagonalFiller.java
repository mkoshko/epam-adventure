package by.koshko.task02.service;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Solution with ConcurrentLinkedQueue.
 */
public class MatrixDiagonalFiller {
    /**
     * Logger.
     */
    private static Logger log
            = LogManager.getLogger(MatrixDiagonalFiller.class);
    /**
     * Hardcoded path to a file with parameters for thread creation.
     */
    private final String pathToFile = "data/threads.txt";
    /**
     * Matrix over which operations will be performed.
     */
    private Matrix matrix;
    /**
     * Array with {@code Thread} objects which will used for matrix processing.
     */
    private Thread[] threads;
    /**
     * Queue which contains diagonal indexes.
     */
    private ConcurrentLinkedQueue<Integer> queue
            = new ConcurrentLinkedQueue<>();

    /**
     * Constructor.
     *
     * @param newMatrix matrix over which operations will be performed.
     * @throws ServiceException if matrix reference is null.
     */
    public MatrixDiagonalFiller(final Matrix newMatrix)
            throws ServiceException {
        if (newMatrix == null) {
            throw new ServiceException("Matrix is null");
        }
        matrix = newMatrix;
        initQueue();
    }

    /**
     * Method fills matrix by.koshko.task02.main diagonal with threads unique value.
     *
     * @throws ServiceException if some by.koshko.task02.exception occurs while thread creation.
     */
    public void fill() throws ServiceException {
        initThreads();
        ExecutorService service = Executors.newFixedThreadPool(threads.length);
        for (Thread t : threads) {
            service.execute(t);
        }
        service.shutdown();
        while (!service.isTerminated()) {
            try {
                service.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void initQueue() {
        IntStream.range(0, matrix.getVerticalSize()).forEach(i -> queue.add(i));
    }

    private void initThreads() throws ServiceException {
        List<String[]> args = ThreadDataLoader.loadData(pathToFile);
        threads = new Thread[args.size()];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(
                    new Runner(Integer.parseInt(args.get(i)[0])));
        }
    }

    class Runner implements Runnable {
        /**
         * Unique thread value.
         */
        private int number;
        Runner(final int newNumber) {
            number = newNumber;
        }
        @Override
        public void run() {
            final var sleep = 2;
            while (true) {
                var index = queue.poll();
                if (index != null) {
                    log.debug("set " + index + " " + index + " = " + number);
                    matrix.setElemFast(index, index, number);
                    try {
                        TimeUnit.MILLISECONDS.sleep(sleep);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    break;
                }
            }
        }
    }

}
