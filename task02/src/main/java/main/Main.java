package main;

import dao.MatrixWriter;
import entity.Matrix;
import exception.DaoException;
import exception.MatrixException;
import exception.ServiceException;
import service.*;

@SuppressWarnings("All")
public class Main {
    public static void main(final String[] args) throws MatrixException,
            ServiceException, DaoException {
        //multiplication
        MatrixGenerator generator = MatrixGeneratorImpl.access();
        MatrixCreatorService mcs = new MatrixCreatorServiceImpl();
        Matrix matrix = mcs.createFromFile("data/matrixA");
        System.out.println(matrix);
//        int m = 12;
//        Matrix matrix1 = new Matrix(m, m);
//        Matrix matrix2 = new Matrix(m, m);
//        generator.fillRandom(matrix1, 1, 10);
//        MatrixWriter writer = new MatrixWriter("data/matrixA");
//        writer.write(matrix1);
//        generator.fillMainDiagonalWithZero(matrix1);
//        MatrixFillDiagonalPhaser mfd = new MatrixFillDiagonalPhaser(matrix1);
//        mfd.fillDiagonalPhaser();
//        System.out.println(matrix1);
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
