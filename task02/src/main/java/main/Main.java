package main;

import entity.Matrix;
import exception.DaoException;
import exception.MatrixException;
import exception.ServiceException;
import service.MatrixGenerator;
import service.MatrixGeneratorImpl;
import service.MatrixMultiplication;

import java.util.concurrent.TimeUnit;


@SuppressWarnings("All")
public class Main {
    public static void main(final String[] args) throws MatrixException,
            ServiceException {
        MatrixGenerator generator = MatrixGeneratorImpl.access();

        int m = 10;
        Matrix matrix1 = new Matrix(m, m);
        Matrix matrix2 = new Matrix(m, m);
        generator.fillRandom(matrix1, -10, 10);
        generator.fillRandom(matrix2, -10, 10);

        MatrixMultiplication mltpctn
                = new MatrixMultiplication(matrix1, matrix2);
        long start = System.nanoTime();
        Matrix result = mltpctn.useThreads(8).multiply();
        long res = System.nanoTime() - start;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(res));
    }
}
