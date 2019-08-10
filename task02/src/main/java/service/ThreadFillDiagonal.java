package service;

import entity.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Runnable class for solution with ReentrantLock.
 */
public class ThreadFillDiagonal implements Runnable {
    /**
     * Logger.
     */
    private static Logger log = LogManager.getLogger(ThreadFillDiagonal.class);
    /**
     * Unique number for the thread which will be write to diagonal element.
     */
    private int number;
    /**
     * Matrix over which operations will be performed.
     */
    private Matrix matrix;
    /**
     * Object for synchronization.
     */
    private MatrixFillDiagonal.MatrixDiagonalPosition res;
    /**
     * Vertical matrix size.
     */
    private int size;

    /**
     * Constructor.
     * @param num unique number for thread.
     * @param newMatrix Matrix over which operations will be performed.
     * @param obj object for index obtaining.
     */
    public ThreadFillDiagonal(final int num, final Matrix newMatrix,
                    final MatrixFillDiagonal.MatrixDiagonalPosition obj) {
        number = num;
        matrix = newMatrix;
        res = obj;
        size = matrix.getVerticalSize();
    }

    /**
     * Method fills matrix main diagonal with {@link #number} value.
     */
    public void run() {
        final var sleep = 5;
        var index = 0;
        while ((index = res.getPosition()) < size) {
            log.debug(Thread.currentThread().getName()
                    + " fill " + index + " " + index
                    + " with number + " + number);
            matrix.setElemFast(index, index, number);
            try {
                TimeUnit.MILLISECONDS.sleep(sleep);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
