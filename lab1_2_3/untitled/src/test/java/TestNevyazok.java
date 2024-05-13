import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class TestNevyazok {
    private static void getTableRowIter(int N, double ALPHA, double BETA, double[][] a, double[][] a_inv, double[] x, double eps) {
        Generator g = new Generator();
        double[] values = g.myGen(a, a_inv, N, ALPHA, BETA, 1, 2, 0, 1);


        double[] b = MatrixService.multiplyMatrixOnVector(a,x);

        MinimalNevyazki minimalNevyazki = new MinimalNevyazki();
        double[] myX = minimalNevyazki.solveNevyazki(a,b);

        double zNorm = MatrixService.find2NormVec(MatrixService.matrixDifference(x,myX));

        double[] compositionB = MatrixService.matrixDifference(MatrixService.multiplyMatrixOnVector(a,myX),b);

        System.out.printf("%17.8e", values[0]);
        System.out.printf("%23.8e", values[1]);
        System.out.printf("%25.8e", values[2]);
        System.out.printf("%22.8e", values[3]);
        System.out.printf("%19.8e", zNorm);
        System.out.printf("%20.8e", zNorm / MatrixService.find2NormVec(x));
        System.out.printf("%17.8e", MatrixService.find2NormVec(compositionB));
        System.out.printf("%17.8e", ALPHA);
        System.out.printf("%17.8e\n\n", BETA);
        System.out.println("CHECK");
        System.out.println(Arrays.toString(MatrixService.matrixDifference(x,myX)));
        System.out.println("\n");
    }

    @Test
    public void test() {
        int n = 100;
        double ALPHA = 1.;
        double BETA = 10;
        double[][] a = new double[n][n];
        double[][] a_inv = new double[n][n];
        double[] x = new double[n];
        Random random = new Random();
        for (int i = 0; i < n; ++i) {
            //x[i] = random.nextInt(10) + 1;
            x[i]=1;
        }
        double eps = 1e-5;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Увеличиваем beta~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
        System.out.println(
                "||      A      ||      ||    A_inv    ||        //    obusl    //  " +
                        "   ||    R_gen    ||      ||    Z    ||" +
                        "             кси          p                    alpha     beta");
        while (BETA <= 1e17) {
            getTableRowIter(n, ALPHA, BETA, a, a_inv, x, eps);
            BETA *= 10;
        }
        BETA = 1.;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Уменьшаем alpha~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
        System.out.println(
                "||      A      ||      ||    A_inv    ||        //    obusl    //  " +
                        "   ||    R_gen    ||      ||    Z    ||" +
                        "             кси          p                    alpha     beta");
        while (ALPHA >= 1e-17) {
            getTableRowIter(n, ALPHA, BETA, a, a_inv, x, eps);
            ALPHA /= 10;
        }
    }
/*
    @Test
    public void myMatrix1() {
        double[][] A = {{2, 1}, {1, 2}};
        double[] b = {4, 8};
        MinimalNevyazki minimalNevyazki = new MinimalNevyazki();

        double[] x = minimalNevyazki.solveNevyazki(A, b);

        MatrixService.printVector(x);
    }

    @Test
    public void myMatrix2() {
        double[][] A = {{6, 4, 1}, {4, 5, 3}, {1, 3, 6}};
        double[] b = {3, 1, 5};
        MinimalNevyazki minimalNevyazki = new MinimalNevyazki();

        double[] x = minimalNevyazki.solveNevyazki(A, b);

        MatrixService.printVector(x);
    }

    @Test
    public void testMultiplyMatrixOnVector() {
        double[][] A = {{1, 4, 3}, {4, 5, 6}, {7, 8, 2}};
        double[] b = {1, 5, 3};

        MatrixService.printVector(MatrixService.multiplyMatrixOnVector(A, b));
    }

    @Test
    public void testMultiplyNumberOnVector() {
        double[] vec = {1, 2, 3};
        MatrixService.printVector(MatrixService.multiplyVecOnNumber(9, vec));
        MatrixService.printVector(vec);
    }*/
}
