package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int commend;
        do {
            System.out.println("\n1. Add matrices\n" +
                    "2. Multiply matrix by constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "5. Calculate a determinant\n" +
                    "6. Inverse matrix\n" +
                    "0. Exit");
            System.out.print("Your choice: ");
            commend = in.nextInt();
            Operation operation = null;
            switch (commend) {
                case 1:
                    operation = new Add();
                    break;
                case 2:
                    operation = new MultiplyByConstant();
                    break;
                case 3:
                    operation = new MultiplyByMatrix();
                    break;
                case 4:
                    System.out.println("\n1. Main diagonal\n" +
                            "2. Side diagonal\n" +
                            "3. Vertical line\n" +
                            "4. Horizontal line");
                    System.out.print("Your choice: ");
                    commend = in.nextInt();

                    switch (commend) {
                        case 1:
                            operation = new MainDiagonal();
                            break;
                        case 2:
                            operation = new SideDiagonal();
                            break;
                        case 3:
                            operation = new VerticalLine();
                            break;
                        case 4:
                            operation = new HorizontalLine();
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    operation = new Determinant();
                    break;
                case 6:
                    operation = new Inverse();
                    break;
                default:
                    break;
            }
            Matrix solution;
            if (commend == 0) { break; }
            try{
                assert operation != null;
                solution = operation.matrixOp();
                System.out.println("The result is:");
                System.out.print(solution);
            }catch (Exception e) {
                    System.out.println(e.getMessage() == null ? "This operation cannot  be performed." : e.getMessage());

            }

        } while (true);
    }
}
