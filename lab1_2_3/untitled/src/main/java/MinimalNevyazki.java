public class MinimalNevyazki {


    public double[] solveNevyazki(double[][] matrixA, double[] vecB) {
        double epsilon = 1E-5;

        return solveNevyazki(matrixA, vecB, epsilon);
    }

    public double[] solveNevyazki(double[][] matrixA, double[] vecB, double epsilon) {
        int countOfIterations = 0;
        int n = matrixA.length;
        double[] resX = new double[n];

        double[] y;
        double t;
        double[] support;
        double criteria;

        do {
            y = MatrixService.matrixDifference(MatrixService.multiplyMatrixOnVector(matrixA, resX), vecB);
            support = MatrixService.multiplyMatrixOnVector(matrixA, y);
            t = MatrixService.multiplyVectorOnVector(y, support) / MatrixService.multiplyVectorOnVector(support, support);
            resX = MatrixService.matrixDifference(resX, MatrixService.multiplyVecOnNumber(t, y));

            criteria = MatrixService.find2NormVec(MatrixService.matrixDifference(
                    MatrixService.multiplyMatrixOnVector(matrixA, resX), vecB)) / MatrixService.find2NormVec(vecB);

            countOfIterations++;
        } while (criteria > epsilon && countOfIterations < 1000);

        return resX;
    }
}
