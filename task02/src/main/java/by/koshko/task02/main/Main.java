package by.koshko.task02.main;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.MatrixException;
import by.koshko.task02.exception.ServiceException;
import by.koshko.task02.service.MatrixCreatorService;
import by.koshko.task02.service.MatrixCreatorServiceImpl;
import by.koshko.task02.service.MatrixDiagonalFiller;
import by.koshko.task02.service.MatrixFillDiagonal;
import by.koshko.task02.service.MatrixFillDiagonalPhaser;
import by.koshko.task02.service.MatrixGenerator;
import by.koshko.task02.service.MatrixGeneratorImpl;
import by.koshko.task02.service.MatrixMultiplication;

@SuppressWarnings("All")
public class Main {
    public static void main(final String[] args)
            throws ServiceException, MatrixException {
        MatrixGenerator generator = MatrixGeneratorImpl.access();
        MatrixCreatorService mcs = new MatrixCreatorServiceImpl();
        Matrix matrix = mcs.createFromFile("data/matrixA");
        //======================================================//
        generator.fillMainDiagonalWithZero(matrix);
        MatrixFillDiagonal f1 = new MatrixFillDiagonal(matrix);
        f1.fill();
        System.out.println(matrix);
        //======================================================//
        generator.fillMainDiagonalWithZero(matrix);
        MatrixFillDiagonalPhaser f2 = new MatrixFillDiagonalPhaser(matrix);
        f2.fill();
        System.out.println(matrix);
        //======================================================//
        generator.fillMainDiagonalWithZero(matrix);
        MatrixDiagonalFiller f3 = new MatrixDiagonalFiller(matrix);
        f3.fill();
        System.out.println(matrix);
        //======================================================//
        final int n = 100;
        final int threads = 4;
        final int min = 1;
        final int max = 100;
        Matrix matrix1 = new Matrix(n, n);
        Matrix matrix2 = new Matrix(n, n);
        generator.fillRandom(matrix1, min, max);
        generator.fillRandom(matrix2, min, max);
        MatrixMultiplication f4 = new MatrixMultiplication(matrix1, matrix2);
        long start = System.currentTimeMillis();
        f4.useThreads(threads).multiply();
        long past = System.currentTimeMillis() - start;
        System.out.printf("done in %d ms", past);
    }
}
