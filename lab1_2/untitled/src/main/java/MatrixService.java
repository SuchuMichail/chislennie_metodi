public class MatrixService {
    public static double[][] multiplyTwoMatrix(double[][] matrixOne, double[][] matrixTwo) {
        int n = matrixOne.length;
        int m = matrixTwo[0].length;
        int countStrTwo = matrixTwo.length;
        int countColumnsOne = matrixOne[0].length;

        if (countStrTwo != countColumnsOne) {
            return null;
        }

        double[][] matrix = new double[n][m];

        double support;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                support = 0;
                for (int k = 0; k < countStrTwo; k++) {
                    support += matrixOne[i][k] * matrixTwo[k][j];
                }
                matrix[i][j] = support;
            }
        }

        return matrix;
    }

    public static double getNormOfMatrix(double[][] matrix) {
        double n = matrix.length;
        double norm = 0;
        double sum;

        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                sum += Math.abs(matrix[i][j]);
            }
            if (sum > norm) {
                norm = sum;
            }
        }

        return norm;
    }

    public static double[][] matrixDifference(double[][] first, double[][] second) {
        double[][] result = new double[first.length][first.length];
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first.length; j++) {
                result[i][j] = first[i][j] - second[i][j];
            }
        }
        return result;
    }

    public static double[] matrixDifference(double[] first, double[] second) {
        double[] result = new double[first.length];
        for (int i = 0; i < first.length; i++) {
            result[i] = first[i] - second[i];
        }
        return result;
    }

    public static void printMatrix(double[][] a) {
        int m = a[0].length;
        for (double[] doubles : a) {
            for (int j = 0; j < m; ++j) {
                System.out.print(doubles[j] + " ");
            }
            System.out.println();
        }
    }

    public static void printVector(double[] vec) {
        for (double v : vec) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    public static double[] multiplyMatrixOnVector(double[][] matrix, double[] vector) {
        if (matrix[0].length != vector.length) {
            throw new IllegalArgumentException();
        }

        int n = vector.length;
        double[] res = new double[n];
        double sum;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j] * vector[j];
            }
            res[i] = sum;
        }

        return res;
    }

    public static double[] multiplyVecOnNumber(double t, double[] vec) {
        double[] copy = new double[vec.length];
        System.arraycopy(vec, 0, copy, 0, vec.length);
        for (int i = 0; i < vec.length; i++) {
            copy[i] = vec[i] * t;
        }
        return copy;
    }

    public static double getScalarVec(double[] vecA, double[] vecB) {
        double res = 0;
        for (int i = 0; i < vecA.length; i++) {
            res += vecA[i] * vecB[i];
        }
        return res;
    }

    public static double find2NormVec(double[] vec) {
        double sum = 0;
        for (double v : vec) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }
}
