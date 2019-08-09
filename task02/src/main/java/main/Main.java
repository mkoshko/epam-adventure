package main;

import entity.Matrix;
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
        System.out.println(Integer.MAX_VALUE);
        int m = 1000;
        Matrix matrix1 = new Matrix(m, m);
        Matrix matrix2 = new Matrix(m, m);
        generator.fillRandom(matrix1, 1, 10);
        generator.fillRandom(matrix2, 1, 10);
        MatrixMultiplication mltpctn
                = new MatrixMultiplication(matrix1, matrix2);
        long start = System.nanoTime();
        Matrix result = mltpctn.useThreads(4).multiply();
        long res = System.nanoTime() - start;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(res));
    }
}
