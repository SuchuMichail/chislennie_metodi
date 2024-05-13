public class InvertPowerIterations {
    public InvertPowerIterations() {
    }

    public double[] findOwnVector(double[][] matrixA, double epsilon, double[] vecX0, double approximateOwnValue) {
        //вернём собственный вектор
        //approximateOwnValue - мы ищем собственное значение, близкое к этому
        double[] vecX = MatrixService.normalizeVec(vecX0);
        double[] vecX_prev;
        double[] y;
        double lambda;

        double[][] matrixI = new double[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrixI.length; i++) {
            matrixI[i][i] = 1;
        }
        double[][] matrixB = MatrixService.Inverse_matrix_jordan_gauss(
                MatrixService.matrixDifference(matrixA,
                        MatrixService.multiplyMatrixOnNumber(matrixI, approximateOwnValue)));

        MatrixService.printMatrix(matrixB);

        int countOfIterations = 0;
        do {
//            vecX_prev = vecX;
//            vecX = MatrixService.multiplyMatrixOnVector(matrixB, vecX_prev);
//            vecX = MatrixService.normalizeVec(vecX);
            MatrixService.printVector(vecX);
            vecX_prev = vecX;
            y = MatrixService.multiplyMatrixOnVector(matrixB, vecX_prev);
            vecX = MatrixService.normalizeVec(y);
            lambda = MatrixService.multiplyVectorOnVector(y, vecX_prev) /
                    MatrixService.multiplyVectorOnVector(vecX_prev, vecX_prev);
            countOfIterations++;

            MatrixService.printVector(vecX);
            // System.out.println("norma = " + MatrixService.find2NormVec(MatrixService.matrixDifference(vecX, vecX_prev)));
            //} while (MatrixService.find2NormVec(MatrixService.matrixDifference(vecX, vecX_prev)) >= epsilon  && countOfIterations < 1000);
        } while (MatrixService.find2NormVec(
                MatrixService.matrixDifference(
                        MatrixService.multiplyMatrixOnVector(matrixA, vecX),
                        MatrixService.multiplyVecOnNumber(lambda, vecX))) >= epsilon && countOfIterations < 1000);
        return vecX;
    }

    public double findOwnNumber(double[][] matrixA, double[] ownVec) {
        return MatrixService.multiplyVectorOnVector(
                MatrixService.multiplyVectorOnMatrix(ownVec, matrixA), ownVec) /
                MatrixService.multiplyVectorOnVector(ownVec, ownVec);
    }
}
