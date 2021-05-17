package processor;

import java.util.Locale;
import java.util.Scanner;

class Matrix {

    private final double[][] matrix;
    private final int rows;
    private final int columns;
    public static final int DOUBLE = 0;
    public static final int INTEGER = 1;
    StringBuilder output;

    private void writeOutput(int kind) {
        switch (kind) {
            case Matrix.DOUBLE:
                output = new StringBuilder();
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        output.append(matrix[i][j] + " ");
                               // .append("\t");
                    }
                    output.append("\n");
                }
                break;
            case Matrix.INTEGER:
                output = new StringBuilder();
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        output.append((int) matrix[i][j]).append("\t");
                    }
                    output.append("\n");
                }
                break;
        }
    }

    public Matrix(double[][] array, int rows, int columns, int kind) {
        matrix = array;
        this.rows = rows;
        this.columns = columns;
        this.writeOutput(kind);

    }

    public Matrix(int rows, int columns, int kind, Scanner input) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = input.nextDouble();
            }

        }
        this.writeOutput(kind);
    }

    public static Matrix adj(Matrix matrix) {
        double[][] first = new double[matrix.columns][matrix.rows];
        for (int i = 0; i < matrix.rows ; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                double[][] second = new double[matrix.rows - 1][matrix.columns - 1];
                for (int k = 0, l = 0; k < matrix.rows - 1; k++, l++) {
                    if (k == i) { l++; }
                    for (int m = 0, n = 0; m < matrix.columns - 1; m++, n++) {
                        if (m == j) { n++; }
                        second[k][m] = matrix.matrix[l][n];
                    }
                }
                Matrix matrix1 = new Matrix(second, matrix.rows - 1, matrix.columns - 1, Matrix.DOUBLE);
                first[j][i] = Math.pow(-1, i + 1 + j + 1) * determinate(matrix1);
            }

        }
        return new Matrix(first, matrix.columns, matrix.rows, Matrix.DOUBLE);
    }

    public static double determinate(Matrix matrix) {
        double result = 0;

        if (matrix.rows == 1) return matrix.matrix[0][0];
        if (matrix.rows == 2) {
            result += matrix.matrix[0][0] * matrix.matrix[1][1];
            result -= matrix.matrix[1][0] * matrix.matrix[0][1];
            return result;
        }
        boolean bool = false;

        for (int i = 0; i < matrix.columns; i++) {
            double[][] tmp = new double[matrix.rows - 1][matrix.columns - 1];

            for (int j = 0; j < matrix.rows - 1; j++) {
                for (int k = 0, l = 0; k < matrix.columns - 1; k++, l++) {
                    if (i == k) {
                        l++;
                    }
                    tmp[j][k] = matrix.matrix[j + 1][l];
                }
            }
            bool = !bool;
            Matrix temp = new Matrix(tmp, matrix.rows - 1, matrix.columns - 1, Matrix.DOUBLE);

            result += bool ? matrix.matrix[0][i] * determinate(temp) : -matrix.matrix[0][i] * determinate(temp);
        }

        return result;
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) throws Exception {
        if (matrix1.columns == matrix2.columns && matrix1.rows == matrix2.rows) {
            double[][] anotherMatrix = new double[matrix1.rows][matrix1.columns];
            for (int i = 0; i < matrix1.rows; i++) {
                for (int j = 0; j < matrix1.columns; j++) {
                    anotherMatrix[i][j] = matrix1.matrix[i][j] + matrix2.matrix[i][j];
                }
            }
            return new Matrix(anotherMatrix, matrix1.rows, matrix1.columns, Matrix.DOUBLE);
        }
        throw new Exception("Different sizes of matrices");
    }

    public static Matrix multiply(Matrix matrix1, double constant) {
        double[][] anotherMatrix = new double[matrix1.rows][matrix1.columns];
        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix1.columns; j++) {
                anotherMatrix[i][j] = matrix1.matrix[i][j] * constant;
            }
        }
        return new Matrix(anotherMatrix, matrix1.rows, matrix1.columns, Matrix.DOUBLE);
    }

    public static Matrix multiplyByMatrix(Matrix matrix1, Matrix matrix2) throws Exception {
        if (matrix1.columns != matrix2.rows) { throw new Exception("Multiply is unavailable"); }
        double[][] anotherMatrix = new double[matrix1.rows][matrix2.columns];
        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix2.columns; j++) {
                for (int k = 0; k < matrix1.columns; k++) {
                    anotherMatrix[i][j] += (matrix1.matrix[i][k] * matrix2.matrix[k][j]);
                }
            }
        }
        return new Matrix(anotherMatrix, matrix1.rows, matrix2.columns, Matrix.DOUBLE);
    }

    @Override
    public String toString() { return output.toString(); }
}
