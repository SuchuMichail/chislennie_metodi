public class PowerIterations {
    public PowerIterations() {
    }

    public MyTuple findOwnVector(double[][] matrixA, double epsilon, double[] vecX0) {
        //вернём собственный вектор
        //approximateOwnValue - мы ищем собственное значение, близкое к этому
        double[] vecX = MatrixService.normalizeVec(vecX0);
        double[] vecX_prev;
        double[] y;
        double lambda;

        int countOfIterations = 0;
        do {
            vecX_prev = vecX;
            y = MatrixService.multiplyMatrixOnVector(matrixA, vecX_prev);
            vecX = MatrixService.normalizeVec(y);
            lambda = MatrixService.multiplyVectorOnVector(y, vecX_prev) /
                    MatrixService.multiplyVectorOnVector(vecX_prev, vecX_prev);
            countOfIterations++;
        } while (MatrixService.find2NormVec(
                MatrixService.matrixDifference(
                        MatrixService.multiplyMatrixOnVector(matrixA, vecX),
                        MatrixService.multiplyVecOnNumber(lambda, vecX))) >= epsilon && countOfIterations < 1000);

        return new MyTuple(vecX,lambda);
    }

}
