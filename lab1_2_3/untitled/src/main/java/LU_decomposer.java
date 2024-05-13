public class LU_decomposer {
    public LU_decomposer(){
    }

    public double[][] decomposeToLU(double[][] matrix) {
        int n = matrix.length;
        double[][] lumatrix = new double[n][n];

        double support;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lumatrix[i][j] = 0;
                if (i == j) {
                    lumatrix[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                support = 0;
                if (i <= j) {
                    for (int k = 0; k < i; k++) {
                        support += lumatrix[i][k] * lumatrix[k][j];
                    }
                    lumatrix[i][j] = matrix[i][j] - support;
                } else {
                    for (int k = 0; k < j; k++) {
                        support += lumatrix[i][k] * lumatrix[k][j];
                    }
                    lumatrix[i][j] = (matrix[i][j] - support) / lumatrix[j][j];
                }
            }
        }

        return lumatrix;
    }

    public double[][] getL(double[][] lumatrix) {
        int n = lumatrix.length;
        double[][] matrixL = new double[n][n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(lumatrix[i], 0, matrixL[i], 0, n);
        }

        for (int i = 0; i < n; i++) {
            matrixL[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                matrixL[i][j] = 0;
            }
        }
        return matrixL;
    }

    public double[][] getU(double[][] lumatrix) {
        int n = lumatrix.length;
        double[][] matrixU = new double[n][n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(lumatrix[i], 0, matrixU[i], 0, n);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                matrixU[i][j] = 0;
            }
        }

        return matrixU;
    }

    public double[][] invertMatrix(double[][] matrix) {
        int n = matrix.length;

        double[][] inv = new double[n][n];

        double[][] lumatrix = decomposeToLU(matrix);

        double supportL;
        double supportU;
        double supportMM;

        inv[n - 1][n - 1] = 1 / lumatrix[n - 1][n - 1];

        for (int m = n - 1; m >= 0; m--) {
            supportMM = 0;
            for (int k = m + 1; k < n; k++) {
                supportMM += lumatrix[m][k] * inv[k][m];
            }
            inv[m][m] = (1 - supportMM) / lumatrix[m][m];

            for (int i = m - 1; i >= 0; i--) {
                supportL = 0;
                supportU = 0;
                for (int k = i + 1; k < n; k++) {
                    supportL += lumatrix[k][i] * inv[m][k];
                    supportU += lumatrix[i][k] * inv[k][m];
                }
                inv[m][i] = -supportL;
                inv[i][m] = -supportU / lumatrix[i][i];
            }
        }

        return inv;
    }
}
