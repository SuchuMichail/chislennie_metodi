import org.junit.Test;

import java.util.Objects;

public class TestPowerIterations {
    @Test
    public void myExampleInvertPow() {
        double[][] matrixA = {{6, -2, 1}, {-2, 4, 1}, {1, 1, 4}};
        double[] vecX0 = {1, 2, 1};
        double approximateOwnValue = 2;
        double epsilon = 1e-2;

        InvertPowerIterations invertPowIterations = new InvertPowerIterations();
        MyTuple tuple = invertPowIterations.findOwnVector(matrixA, epsilon, vecX0, approximateOwnValue);
        double[] ownVector = tuple.getFirst();
        double ownValue = invertPowIterations.findOwnNumber(matrixA,ownVector);
        //double ownValue = tuple.getSecond();

        MatrixService.printVector(ownVector);
        System.out.println("\n" + ownValue);
    }

    @Test
    public void myExamplePow2() {
        //double[][] matrixA = {{2,1},{4,3}};
        //double[][] matrixA = {{2,0,0},{0,7,0},{0,0,3}};
        //double[][] matrixA = {{2,0,0},{0,-7,0},{0,0,3}};
        double[][] matrixA = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        //double[] vecX0 = {1,1};
        double[] vecX0 = {1, 2, 3};
        double epsilon = 1e-9;

        PowerIterations powIterations = new PowerIterations();
        MyTuple tupleA = powIterations.findOwnVector(matrixA, epsilon, vecX0);
        double[] ownVectorA = tupleA.getFirst();
        double maxOwnValueA = tupleA.getSecond();

        //MatrixService.printVector(ownVectorA);
        System.out.println("maxOwnValue = " + maxOwnValueA + "\n");

        MyTuple tupleInvertA = powIterations.findOwnVector(MatrixService.invertMatrix(matrixA), epsilon, vecX0);
        double[] ownVectorAInvert = tupleA.getFirst();
        double minOwnValueA = 1 / tupleInvertA.getSecond();
        //MatrixService.printVector(ownVectorAInvert);
        System.out.println("minOwnValue = " + minOwnValueA + "\n");

        matrixA = new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        double[][] matrixB = MatrixService.matrixDifference(matrixA,
                MatrixService.multiplyMatrixOnNumber(MatrixService.getE(matrixA.length), maxOwnValueA));
        MyTuple tupleB = powIterations.findOwnVector(matrixB, epsilon, vecX0);
        double maxOwnValueB_equalMinOwnValueA = tupleB.getSecond();
        System.out.println("maxOwnValueB_equalMinOwnValueA = " + maxOwnValueB_equalMinOwnValueA);

/*
        InvertPowerIterations invertPowerIterations = new InvertPowerIterations();
        MyTuple invertTuple = invertPowerIterations.findOwnVector(matrixA,epsilon,vecX0,maxOwnValueA);
        double[] ownVectorInvert = invertTuple.getFirst();
        double ownNumberInvert = invertPowerIterations.findOwnNumber(matrixA,ownVectorInvert);
        System.out.println("ownNumberInvert = " + ownNumberInvert);*/

        System.out.println("//////////////////////////////////////////");
        double[][] matrixAtranspose = MatrixService.transposeMatrix(matrixA);
        double[][] matrixAA = MatrixService.multiplyTwoMatrix(matrixA,matrixAtranspose);

        System.out.println();
        //MatrixService.printMatrix(matrixAtranspose);
        System.out.println();
        MatrixService.printMatrix(Objects.requireNonNull(matrixAA));

        MyTuple tupleAA = powIterations.findOwnVector(matrixAA,epsilon,vecX0);
        double maxAA = tupleAA.getSecond();

        System.out.println("maxAA = " + maxAA);

        double[][] matrixAA_B = MatrixService.matrixDifference(matrixAA,
                MatrixService.multiplyMatrixOnNumber(MatrixService.getE(matrixAA.length), maxOwnValueA));

        MyTuple tupleAAB = powIterations.findOwnVector(matrixAA_B, epsilon, vecX0);
        double maxAAB = tupleAAB.getSecond();
        System.out.println("maxAAB = " + maxAAB);
    }

    @Test
    public void testDeterminantCalc(){
        double[][] matrixA = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        DeterminantCalc determinantCalc = new DeterminantCalc(matrixA);

        System.out.println(determinantCalc.determinant());
    }
}
