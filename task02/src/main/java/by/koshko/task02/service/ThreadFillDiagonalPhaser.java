package by.koshko.task02.service;

import by.koshko.task02.entity.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Phaser;

/**
 * Runnable class for solution with Phaser.
 */
public class ThreadFillDiagonalPhaser implements Runnable {
    /**
     * Logger.
     */
    private static Logger log
            = LogManager.getLogger(ThreadFillDiagonalPhaser.class);
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
     * Phaser.
     */
    private Phaser phaser;

    /**
     * Constructor.
     *
     * @param num       unique number for thread.
     * @param newMatrix Matrix over which operations will be performed.
     * @param obj       object for index obtaining.
     * @param phaser0   {@code Phaser} used for synchronization.
     */
    public ThreadFillDiagonalPhaser(
            final int num, final Matrix newMatrix,
            final MatrixFillDiagonal.MatrixDiagonalPosition obj,
            final Phaser phaser0) {
        number = num;
        matrix = newMatrix;
        res = obj;
        size = matrix.getVerticalSize();
        phaser = phaser0;
        phaser.register();
    }

    /**
     * Method fills matrix main diagonal with {@link #number} value.
     */
    public void run() {
        var index = 0;
        while ((index = res.getPosition()) < size) {
            log.debug("set " + index + " " + index + " = " + number);
            matrix.setElemFast(index, index, number);
            phaser.arriveAndAwaitAdvance();
        }
        phaser.arriveAndDeregister();
    }
}
