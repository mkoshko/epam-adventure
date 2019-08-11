package main;

import entity.Matrix;
import exception.ServiceException;
import service.MatrixCreatorService;
import service.MatrixCreatorServiceImpl;
import service.MatrixDiagonalFiller;
import service.MatrixFillDiagonal;
import service.MatrixFillDiagonalPhaser;
import service.MatrixGenerator;
import service.MatrixGeneratorImpl;

@SuppressWarnings("All")
public class Main {
    public static void main(final String[] args) throws ServiceException {
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
    }
}
