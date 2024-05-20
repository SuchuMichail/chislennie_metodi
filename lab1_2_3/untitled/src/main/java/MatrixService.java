public class MatrixService {
    public static double[][] getE(int size) {
        double[][] E = new double[size][size];
        for (int i = 0; i < size; i++) {
            E[i][i] = 1;
        }
        return E;
    }

    public static double[][] transposeMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

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

    public static double[] multiplyVectorOnMatrix(double[] vector, double[][] matrix) {
        if (matrix.length != vector.length) {
            throw new IllegalArgumentException();
        }

        int n = vector.length;
        double[] res = new double[n];
        double sum;
        for (int j = 0; j < n; j++) {
            sum = 0;
            for (int i = 0; i < n; i++) {
                sum += vector[i] * matrix[i][j];
            }
            res[j] = sum;
        }

        return res;
    }

    public static double multiplyVectorOnVector(double[] vectorA, double[] vectorB) {
        if (vectorA.length != vectorB.length) {
            throw new IllegalArgumentException();
        }

        double result = 0;
        for (int i = 0; i < vectorA.length; i++) {
            result += vectorA[i] * vectorB[i];
        }

        return result;
    }

    public static double[] multiplyVecOnNumber(double t, double[] vec) {
        double[] copy = new double[vec.length];
        System.arraycopy(vec, 0, copy, 0, vec.length);
        for (int i = 0; i < vec.length; i++) {
            copy[i] = vec[i] * t;
        }
        return copy;
    }

    public static double[][] multiplyMatrixOnNumber(double[][] matrix, double number) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j] * number;
            }
        }
        return result;
    }


    public static double find2NormVec(double[] vec) {
        double sum = 0;
        for (double v : vec) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    public static double[] normalizeVec(double[] vec) {
        double sum = 0;
        for (double v : vec) {
            sum += v * v;
        }
        sum = Math.sqrt(sum);

        double[] result = new double[vec.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = vec[i] / sum;
        }
        return result;
    }


    //матрица перезаписывается (портится)
    public static double[][] invertMatrix(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];

        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }


// Method to carry out the partial-pivoting Gaussian
// elimination. Here index[] stores pivoting order.

    private static void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    public static double[][] Inverse_matrix_jordan_gauss(double[][] A) {
        int i, j, k;
        int size = A.length;
        double[][] E = getE(size);
        //Задаём номер ведущей строки (сначала 0,1...size)
        for (k = 0; k < size; k++) {
            for (j = k + 1; j < size; j++) {
                A[k][j] = A[k][j] / A[k][k];//все элементы k-ой строки матрицы A, кроме k-ого и до него, делим на разрешающий элемент - a[k][k]
            }
            for (j = 0; j < size; j++) {
                E[k][j] = E[k][j] / A[k][k];//все элементы k-ой строки матрицы e, делим на разрешающий элемент - a[k][k]
            }
            A[k][k] = 1.0;//элемент соответствующий  разрещающему - делим на самого себя(т.е получит. 1 )
            //идём сверху вниз, обходя k-ую строку
            if (k > 0) {//если номер ведущей строки не первый
                for (i = 0; i < k; i++) {   //строки, находящиеся выше k-ой
                    for (j = 0; j < size; j++) {
                        E[i][j] = E[i][j] - E[k][j] * A[i][k];//Вычисляем элементы матрицы e,идя по столбцам с 0 -ого  к последнему
                    }
                    for (j = size - 1; j >= k; j--) {
                        A[i][j] = A[i][j] - A[k][j] * A[i][k];//Вычисляем элементы матрицы a,идя по столбцам с последнего к k-ому
                    }
                }
            }
            for (i = k + 1; i < size; i++) {   //строки, находящиеся ниже k-ой
                for (j = 0; j < size; j++) {
                    E[i][j] = E[i][j] - E[k][j] * A[i][k];
                }
                for (j = size - 1; j >= k; j--) {
                    A[i][j] = A[i][j] - A[k][j] * A[i][k];
                }
            }
        }
        return E;//На месте исходной матрицы должна получиться единичная а на месте единичной - обратная.
    }
}
