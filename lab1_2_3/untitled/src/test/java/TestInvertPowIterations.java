import org.junit.Test;

public class TestInvertPowIterations {
    @Test
    public void myExampleInvertPow(){
        double[][] matrixA = {{6,-2,1},{-2,4,1},{1,1,4}};
        double[] vecX0 = {1,1,1};
        double approximateOwnValue= 2;
        double epsilon=1e-2;

        InvertPowerIterations invertPowIterations = new InvertPowerIterations();
        double[] ownVector = invertPowIterations.findOwnVector(matrixA,epsilon,vecX0,approximateOwnValue);
        double ownValue= invertPowIterations.findOwnNumber(matrixA,ownVector);

        MatrixService.printVector(ownVector);
        System.out.println("\n"+ownValue);
    }

    @Test
    public void myExamplePow2(){
        //double[][] matrixA = {{2,1},{4,3}};
        //double[][] matrixA = {{2,0,0},{0,7,0},{0,0,3}};
        double[][] matrixA = {{2,0,0},{0,-7,0},{0,0,3}};

        //double[] vecX0 = {1,1};
        double[] vecX0 = {1,1,1};
        double epsilon=1e-9;

        PowerIterations powIterations = new PowerIterations();
        MyTuple tupleA = powIterations.findOwnVector(matrixA,epsilon,vecX0);
        double[] ownVectorA = tupleA.getFirst();
        double maxOwnValue = tupleA.getSecond();

        MatrixService.printVector(ownVectorA);
        System.out.println(maxOwnValue+"\n");

        MyTuple tupleInvertA = powIterations.findOwnVector(MatrixService.Inverse_matrix_jordan_gauss(matrixA),epsilon,vecX0);
        double[] ownVectorAInvert = tupleA.getFirst();
        double minOwnValue = 1 / tupleInvertA.getSecond();
        MatrixService.printVector(ownVectorAInvert);
        System.out.println(minOwnValue+"\n");
    }
}
