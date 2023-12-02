import java.util.Random;

public class Strassen {

    public static void main(String[] args) 
    {
        try 
        {
            int N = Integer.parseInt(args[0]);
            if(args[0] == null)
            {
                System.out.println();
                System.out.println("ERROR: Entered value is not a valid Numerical Argument");
                System.out.println();
                System.out.println("Exiting Program in 3...");
                System.out.println("Exiting Program in 2...");
                System.out.println("Exiting Program in 1...");
                System.out.println();
                System.out.println("Program Terminated !");
                System.out.println();
                System.exit(0);
            }
            else
            {
                if(N%2 !=0 && N == 0 || N < 1 || N > 1024)
                {
                    System.out.println();
                    System.out.println("ERROR: The value of Variable (n) is 2k \n(where k is a positive integer, 1 ≤ n ≤ 1,024)");
                    System.out.println();
                    System.out.println("ERROR: Please Enter a valid Numerical Argument !");
                    System.out.println();
                    System.out.println("Exiting Program in 3...");
                    System.out.println("Exiting Program in 2...");
                    System.out.println("Exiting Program in 1...");
                    System.out.println();
                    System.out.println("Program Terminated !");
                    System.out.println();
                    System.exit(0);
                }
                else
                {
                    //Max Number Condition floor of sqrt.(𝑚𝑎𝑥𝑖𝑚𝑢𝑚_𝑖𝑛𝑡𝑒𝑔𝑒𝑟/𝑛) to avoid number overflow
                    int MaxNumber = (int)(Math.floor(Math.sqrt(Integer.MAX_VALUE / N)));
       
                    //Generating Matrix A for value n
                    int[][] A = new int[N][N];
                    int row = A.length;
                    int col = A[0].length;
                    for(int i=0;i<row;i++)
                    {
                        for(int j=0;j<col;j++)
                        {
                            Random R = new Random();
                            int randomNumber = R.nextInt(MaxNumber);
                            A[i][j] = randomNumber;
                        }
                    }

                    //Generating Matrix B for value n
                    int[][] B = new int[N][N];
                    int row1 = B.length;
                    int col1 = B[0].length;
                    for(int i=0;i<row1;i++)
                    {
                        for(int j=0;j<col1;j++)
                        {
                            Random R = new Random();
                            int randomNumber = R.nextInt(MaxNumber);
                            B[i][j] = randomNumber;      
                        }
                    }

                    Strassen MM = new Strassen();
                    MM.Compute_Normal(A,B);
                    
                    int[][] res = MM.Compute_Strassen(A, B);
                    MM.PrintS(res);
                }
                
            }
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR: Please enter a valid Numerical Argument");
            e.printStackTrace();
            System.exit(0);
        }

    }
    public int[][] Compute_Strassen(int[][] X, int[][] Y) {
        int n = X.length;
        int[][] product = new int[n][n];

        if (n == 1) 
        {
            product[0][0] = X[0][0] * Y[0][0];
        } 
        else 
        {
            int[][] A11 = DivideStrasM(X, 0, 0);
            int[][] A12 = DivideStrasM(X, 0, n / 2);
            int[][] A21 = DivideStrasM(X, n / 2, 0);
            int[][] A22 = DivideStrasM(X, n / 2, n / 2);

            int[][] B11 = DivideStrasM(Y, 0, 0);
            int[][] B12 = DivideStrasM(Y, 0, n / 2);
            int[][] B21 = DivideStrasM(Y, n / 2, 0);
            int[][] B22 = DivideStrasM(Y, n / 2, n / 2);

            int[][] P1 = Compute_Strassen(AddStrasM(A11, A22), AddStrasM(B11, B22));
            int[][] P2 = Compute_Strassen(AddStrasM(A21, A22), B11);
            int[][] P3 = Compute_Strassen(A11, SubStrassM(B12, B22));
            int[][] P4 = Compute_Strassen(A22, SubStrassM(B21, B11));
            int[][] P5 = Compute_Strassen(AddStrasM(A11, A12), B22);
            int[][] P6 = Compute_Strassen(SubStrassM(A21, A11), AddStrasM(B11, B12));
            int[][] P7 = Compute_Strassen(SubStrassM(A12, A22), AddStrasM(B21, B22));

            int[][] C11 = AddStrasM(SubStrassM(AddStrasM(P1, P4), P5), P7);
            int[][] C12 = AddStrasM(P3, P5);
            int[][] C21 = AddStrasM(P2, P4);
            int[][] C22 = AddStrasM(SubStrassM(AddStrasM(P1, P3), P2), P6);

            SubSStrassM(C11, product, 0, 0);
            SubSStrassM(C12, product, 0, n / 2);
            SubSStrassM(C21, product, n / 2, 0);
            SubSStrassM(C22, product, n / 2, n / 2);
        }
        return product;
    }

    public static int[][] DivideStrasM(int[][] matrix, int row, int col) {
        
        int size = matrix.length / 2;
        int[][] newMatrix = new int[size][size];
        
        for (int i = 0; i < size; i++) 
        {
            for (int j = 0; j < size; j++)
            {
                newMatrix[i][j] = matrix[row + i][col + j];
            }
        }
        return newMatrix;
    }

    public static int[][] AddStrasM(int[][] A, int[][] B) 
    {
        int n = A.length;
        int[][] R = new int[n][n];

        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                R[i][j] = A[i][j] + B[i][j];
            }
        }
        return R;
    }

    public static int[][] SubStrassM(int[][] A, int[][] B) 
    {
        int n = A.length;
        int[][] R = new int[n][n];
        
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                R[i][j] = A[i][j] - B[i][j];
            }
        }
        return R;
    }

    public static void SubSStrassM(int[][] A, int[][] B, int R, int C) 
    {
        int size = A.length;
        
        for (int i = 0; i < size; i++) 
        {
            for (int j = 0; j < size; j++) 
            {
                B[R + i][C + j] = A[i][j];
            }
        }
    }

    public void PrintS(int[][] result)
    {
        System.out.println("The Strassens Matrix Multiplication A * B = ");
        System.out.println();
        for (int[] row : result) 
        {
            for (int col : row) 
            {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void Compute_Normal(int[][] A, int[][] B)
    {

        int n = A.length;
        
        System.out.println();
        System.out.println("Matrix A = ");
        System.out.println();
        for(int i=0;i<A.length;i++)
        {
            for(int j=0;j<A[0].length;j++)
            {
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        } 

        System.out.println();
        System.out.println("Matrix B = ");
        System.out.println();
        for(int i=0;i<B.length;i++)
        {
            for(int j=0;j<B[0].length;j++)
            {
                System.out.print(B[i][j]+" ");
            }
            System.out.println();
        } 

        int row2 = A.length;
        int col2 = A[0].length;
        int coln2 = B[0].length;
        int[][] product = new int[n][n];

        for (int i = 0; i < row2; i++) 
        {
            for (int j = 0; j < col2; j++) 
            {
                for (int k = 0; k < coln2; k++) 
                {
                    product[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        System.out.println();
        System.out.println("The Standard Matrix Multiplication A * B = ");
        System.out.println();
        for(int i=0;i<product.length;i++)
        {
            for(int j=0;j<product[0].length;j++)
            {
                System.out.print(product[i][j]+" ");
            }
            System.out.println();
        } 
        System.out.println();
    }
}