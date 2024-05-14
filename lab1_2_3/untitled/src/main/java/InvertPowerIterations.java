public class InvertPowerIterations {
    public InvertPowerIterations() {
    }

    public MyTuple findOwnVector(double[][] matrixA, double epsilon, double[] vecX0, double approximateOwnValue) {
        //вернём собственный вектор
        //approximateOwnValue - мы ищем собственное значение, близкое к этому
        int n = matrixA.length;
        double[] vecX = MatrixService.normalizeVec(vecX0);
        double[] vecX_prev;
        double[] y;
        double lambda = 0;

        double[][] matrixI = new double[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrixI.length; i++) {
            matrixI[i][i] = 1;
        }
        double[][] kamikaze = new double[n][n];
        for(int i=0;i<n;i++){
            System.arraycopy(matrixA[i], 0, kamikaze[i], 0, n);
        }
        double[][] matrixB = MatrixService.invertMatrix(
                MatrixService.matrixDifference(kamikaze,
                        MatrixService.multiplyMatrixOnNumber(matrixI, approximateOwnValue)));

        int countOfIterations = 0;
        do {
            vecX_prev = vecX;
            vecX = MatrixService.multiplyMatrixOnVector(matrixB, vecX_prev);
            vecX = MatrixService.normalizeVec(vecX);
            countOfIterations++;
        } while (MatrixService.find2NormVec(MatrixService.matrixDifference(vecX, vecX_prev)) >= epsilon  && countOfIterations < 1000);

        return new MyTuple(vecX,lambda);
    }

    public double findOwnNumber(double[][] matrixA, double[] ownVec) {
        return MatrixService.multiplyVectorOnVector(
                MatrixService.multiplyVectorOnMatrix(ownVec, matrixA), ownVec) /
                MatrixService.multiplyVectorOnVector(ownVec, ownVec);
    }
}
