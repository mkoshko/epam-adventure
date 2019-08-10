package main;

import entity.Matrix;
import exception.MatrixException;
import exception.ServiceException;
import service.MatrixFillDiagonal;
import service.MatrixGenerator;
import service.MatrixGeneratorImpl;

@SuppressWarnings("All")
public class Main {
    public static void main(final String[] args) throws MatrixException,
            ServiceException {
        //multiplication
        MatrixGenerator generator = MatrixGeneratorImpl.access();
        int m = 10;
        Matrix matrix1 = new Matrix(m, m);
//        Matrix matrix2 = new Matrix(m, m);
        generator.fillRandom(matrix1, 1, 10);
        generator.fillMainDiagonalWithZero(matrix1);
        MatrixFillDiagonal mfd = new MatrixFillDiagonal(matrix1);
        mfd.fillDiagonal();
        System.out.println(matrix1);
//        generator.fillRandom(matrix2, 1, 10);
//        MatrixMultiplication mltpctn
//                = new MatrixMultiplication(matrix1, matrix2);
//        long start = System.nanoTime();
//        Matrix result = mltpctn.useThreads(4).multiply();
//        long res = System.nanoTime() - start;
//        System.out.println(TimeUnit.NANOSECONDS.toMillis(res));
        //========================================================//
    }
}
