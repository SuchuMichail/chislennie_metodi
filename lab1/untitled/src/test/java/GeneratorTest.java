import org.junit.Test;

public class GeneratorTest {
    @Test
    public void Test1() {
        int N = 10;
        double ALPHA = 1E-12;
        double BETA = 1.;
        double[][] a = new double[N][];

        for (int i = 0; i < N; ++i) {
            a[i] = new double[N];
        }

        double[][] a_inv = new double[N][];

        for (int i = 0; i < N; ++i) {
            a_inv[i] = new double[N];
        }

        Generator g = new Generator();
        g.myGen(a, a_inv, N, ALPHA, BETA, 1, 2, 0, 1);
/*
        System.out.println("\n\nA:");
        g.printMatrix(a);
*/
        double normA = g.getNormOfMatrix(a);
        System.out.println("\n||A|| = " + normA);
/*
        System.out.println("\n\nA_inv:");
        g.printMatrix(a_inv);
*/

        //double[][] matrixL = g.getL(g.decomposeToLU(a));
        //double[][] matrixU = g.getU(g.decomposeToLU(a));
        //double[][] product = g.multiplyTwoMatrix(matrixL, matrixU);
        double[][] inv = g.invertMatrix(a);

        double normInv = g.getNormOfMatrix(inv);
        System.out.println("\n||A_inv|| = " + normInv);

        System.out.println("\n||A|| * ||A_inv|| = " + normA * normInv);
/*
        System.out.println("\n\nL-matrix:");
        g.printMatrix(matrixL);

        System.out.println("\n\nU-matrix:");
        g.printMatrix(matrixU);

        System.out.println("\n\nproduct L-matrix on U-matrix:");
        g.printMatrix(product);
*/
/*
      System.out.println("\n\nA_inv after LU:");
        g.printMatrix(inv);
*/
/*
        System.out.println("\n\nA*A_inv:");
        g.printMatrix(g.multiplyTwoMatrix(a,inv));

 */
        double z = g.getNormOfMatrix(g.matrixDifference(a_inv,inv));
        System.out.println("\n\n||z|| = " + z);
        System.out.println("\n\nS = " + z/g.getNormOfMatrix(a_inv));

        double[][] E = new double[a.length][a.length];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                if(i==j){
                    E[i][j]=1;
                }
            }
        }
        System.out.println("\n\np = " + g.getNormOfMatrix(g.matrixDifference(g.multiplyTwoMatrix(a,inv),E)));
    }
}
