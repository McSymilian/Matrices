package processor;

import java.util.Locale;
import java.util.Scanner;
abstract class Operation {
    Scanner in = new Scanner(System.in).useLocale(Locale.US);
    abstract Matrix matrixOp() throws Exception;
}
class Inverse extends Operation {
    @Override
    Matrix matrixOp() throws Exception {
        System.out.print("Enter matrix size: ");
        final int rows = in.nextInt();
        final int columns = in.nextInt();

        if (rows != columns) throw new Exception();

        System.out.println("Enter matrix: ");
        Matrix operated = new Matrix(rows, columns, Matrix.DOUBLE, in);

        if (Matrix.determinate(operated) == 0) throw new Exception("This matrix doesn't have an inverse.");


        return Matrix.multiply(Matrix.adj(operated), 1 / Matrix.determinate(operated));
    }
}

class Determinant extends Operation {
    @Override
    Matrix matrixOp() throws Exception {
        System.out.print("Enter matrix size: ");
        final int rows = in.nextInt();
        final int columns = in.nextInt();

        if (rows != columns) throw new Exception();

        System.out.println("Enter matrix: ");
        Matrix operated = new Matrix(rows, columns, Matrix.DOUBLE, in);

        double[][] tmp = new double[1][1];

        tmp[0][0] = Matrix.determinate(operated);

        return new Matrix(tmp, 1, 1, Matrix.DOUBLE);
    }
}

class MainDiagonal extends Operation {

    @Override
    Matrix matrixOp() throws Exception {
        System.out.print("Enter size of matrix: ");
        final int rows = in.nextInt();
        final int columns = in.nextInt();

        System.out.println("Enter matrix: ");
        double[][] array = new double[rows][columns];

        for (int i = 0; i < columns; i++) {
            for (int j = 0 ; j < rows; j++) {
                array[j][i] = in.nextDouble();
            }
        }

        return new Matrix(array, rows, columns, Matrix.DOUBLE);
    }
}

class SideDiagonal extends Operation {

    @Override
    Matrix matrixOp() throws Exception {
        System.out.print("Enter size of matrix: ");
        final int rows = in.nextInt();
        final int columns = in.nextInt();

        System.out.println("Enter matrix: ");
        double[][] array = new double[rows][columns];

        for (int i = columns - 1; i >= 0; i--) {
            for (int j = rows - 1 ; j >= 0; j--) {
                array[j][i] = in.nextDouble();
            }
        }

        return new Matrix(array, rows, columns, Matrix.DOUBLE);
    }
}

class VerticalLine extends Operation {

    @Override
    Matrix matrixOp() throws Exception {
        System.out.print("Enter size of matrix: ");
        final int rows = in.nextInt();
        final int columns = in.nextInt();

        System.out.println("Enter matrix: ");
        double[][] array = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = columns - 1 ; j >= 0; j--) {
                array[i][j] = in.nextDouble();
            }
        }

        return new Matrix(array, rows, columns, Matrix.DOUBLE);
    }
}

class HorizontalLine extends Operation {

    @Override
    Matrix matrixOp() throws Exception {
        System.out.print("Enter size of matrix: ");
        final int rows = in.nextInt();
        final int columns = in.nextInt();

        System.out.println("Enter matrix: ");
        double[][] array = new double[rows][columns];

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0 ; j < columns; j++) {
                array[i][j] = in.nextDouble();
            }
        }

        return new Matrix(array, rows, columns, Matrix.DOUBLE);
    }
}

class MultiplyByMatrix extends Operation {

    @Override
    Matrix matrixOp() throws Exception {
        System.out.print("Enter size of first matrix: ");
        final int rows1 = in.nextInt();
        final int columns1 = in.nextInt();

        System.out.println("Enter first matrix: ");
        Matrix one = new Matrix(rows1, columns1, Matrix.DOUBLE, in);

        System.out.print("Enter size of second matrix: ");
        final int rows2 = in.nextInt();
        final int columns2 = in.nextInt();

        System.out.println("Enter second matrix: ");
        Matrix two = new Matrix(rows2, columns2, Matrix.DOUBLE, in);

        return Matrix.multiplyByMatrix(one, two);
    }
}
class MultiplyByConstant extends Operation {

    @Override
    Matrix matrixOp() throws Exception {
        System.out.print("Enter size of matrix: ");
        final int rows = in.nextInt();
        final int columns = in.nextInt();

        System.out.println("Enter matrix: ");
        Matrix one = new Matrix(rows, columns, Matrix.DOUBLE, in);

        System.out.print("Enter constant: ");
        double con = in.nextDouble();

        return Matrix.multiply(one, con);
    }
}
class Add extends Operation {

    @Override
    Matrix matrixOp() throws Exception {

        System.out.print("Enter size of first matrix: ");
        final int rows1 = in.nextInt();
        final int columns1 = in.nextInt();

        System.out.println("Enter first matrix: ");
        Matrix one = new Matrix(rows1, columns1, Matrix.DOUBLE, in);

        System.out.print("Enter size of second matrix: ");
        final int rows2 = in.nextInt();
        final int columns2 = in.nextInt();

        System.out.println("Enter second matrix: ");
        Matrix two = new Matrix(rows2, columns2, Matrix.DOUBLE, in);

        return Matrix.add(one, two);
    }
}
