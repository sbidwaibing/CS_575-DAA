import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class floyd 
{
    /**
     * The main function checks if a command line argument is provided, assigns it to a variable, and
     * then calls a method with the argument.
     */
    public static void main(String[] args)
    {
        // This code is the main entry point of the program. It checks if a command line argument is
        // provided and if it is not null, it assigns the argument to the variable `FileName`. Then, it
        // creates an instance of the `floyd` class and calls the `readFloyd` method, passing the
        // `FileName` as an argument.
        try 
        {  
            // This code block checks if a command line argument is provided. If an argument is
            // provided, it assigns the argument to the variable `FileName`. Then, it creates an
            // instance of the `floyd` class and calls the `readFloyd` method, passing the `FileName`
            // as an argument. After executing the `readFloyd` method, it prints some messages
            // indicating that the program executed successfully and is terminating.
            if(args[0] != null)
            {
                String FileName = args[0];
                floyd f = new floyd();
                f.readFloyd(FileName);
                System.out.println();
                System.out.println("Program Executed Successfuly !");
                System.out.println();
                System.out.println("Exiting Program in 3...");
                System.out.println("Exiting Program in 2...");
                System.out.println("Exiting Program in 1...");
                System.out.println();
                System.out.println("Program Terminated !");
                System.out.println();
            }
        } 
        catch (Exception e) 
        {
            System.out.println();
            System.out.println("ERROR: Entered value is not a valid Input Argument");
            System.out.println();
            System.out.println("Exiting Program in 3...");
            System.out.println("Exiting Program in 2...");
            System.out.println("Exiting Program in 1...");
            System.out.println();
            System.out.println("Program Terminated !");
            System.out.println();
            System.exit(0);
            e.printStackTrace();
        }
    }

    /**
     * The FloydW function implements the Floyd-Warshall algorithm to find the shortest paths between
     * all pairs of vertices in a weighted graph and writes the result to a file.
     * 
     * @param fw The parameter "fw" is a FileWriter object that is used to write the output to a file.
     * @param n The parameter `n` represents the number of vertices in the graph.
     * @param D The parameter D is a 2D array that represents the distance matrix. It stores the
     * shortest distances between all pairs of vertices in a graph.
     * @param P The parameter P is a 2D array that represents the predecessor matrix. It is used to
     * store the intermediate vertices that are part of the shortest path between any two vertices in
     * the graph. Each element P[i][j] represents the predecessor of vertex j in the shortest path from
     * vertex i to j
     * @param W The parameter W is a 2D array representing the weight matrix of a graph. Each element
     * W[i][j] represents the weight of the edge from vertex i to vertex j.
     */
    public void FloydW(FileWriter fw, int n, int[][]D, int[][]P, int[][] W) throws IOException
    {    
        // The code block is initializing the distance matrix `D` and the predecessor matrix `P` with
        // initial values.
        for(int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                D[i][j] = W[i][j];
                P[i][j] = 0;
            }
        }

        // The code block is implementing the Floyd-Warshall algorithm to find the shortest paths
        // between all pairs of vertices in a weighted graph.
        for (int k = 0; k < n; k++) 
        {
            for (int i = 0; i < n; i++) 
            {
                for (int j = 0; j < n; j++) 
                {
                    if (D[i][j] > D[i][k] + D[k][j]) 
                    {
                        D[i][j] = D[i][k] + D[k][j];
                        P[i][j] = k + 1;
                    }
                }
            }
        }

        // The code block is writing the contents of the matrix `P` to the `FileWriter` object `fw`.
        fw.write("PMatrix:");
        fw.write("\n");
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                fw.write(P[i][j] + " ");
            }
            fw.write("\n");
        }
        print1(fw, n, P, D);
        fw.write("\n");
    }

    /**
     * The function prints the shortest path and length between each pair of vertices in a graph.
     * 
     * @param fw The parameter "fw" is of type FileWriter. It is used to write data to a file.
     * @param n The parameter "n" represents the number of vertices in the graph.
     * @param P The parameter P is a 2D array that represents the shortest path between vertices in a
     * graph. Each element P[i][j] represents the next vertex in the shortest path from vertex i to
     * vertex j.
     * @param D The parameter D is a 2D array that represents the shortest path lengths between
     * vertices in a graph. Each element D[i][j] represents the shortest path length from vertex i to
     * vertex j.
     */
    public void print1(FileWriter fw, int n, int[][]P, int[][] D) throws IOException
    {
        for (int i = 0; i < n; i++) 
        {
            fw.write("\n");;
            fw.write("V"+(i+1)+"-Vj: Shortest path and length"+"\n");
            for (int j = 0; j < n; j++) 
            {    
                fw.write("V"+(i + 1)+" ");  
                print2(fw, i, j, P);
                fw.write("V"+(j + 1) + ": ");
                fw.write(D[i][j]+"\n");
            }
        }
    }

    /**
     * The function recursively prints a sequence of numbers based on a given matrix P.
     * 
     * @param fw The parameter "fw" is of type FileWriter. It is used to write data to a file.
     * @param n The parameter "n" represents the number of elements in the array or list.
     * @param r The parameter "r" represents the number of elements to be selected from the set of "n"
     * elements. It is used in the recursive function to determine the number of elements to be printed
     * in each recursive call.
     * @param P P is a 2D array of integers. It represents a matrix where each element P[i][j] stores
     * the index of the previous element in the optimal path from node i to node j.
     */
    public void print2(FileWriter fw, int n, int r, int[][] P) throws IOException
    {
        if(P[n][r] != 0)
        {
            print2(fw, n, P[n][r]-1, P);
            fw.write("V"+P[n][r]+" ");
            print2(fw, P[n][r]-1, r, P);
        }
    }
    
    /**
     * The function `readFloyd` reads a file containing problem information, such as the size of a
     * matrix and its values, and performs the Floyd-Warshall algorithm on the matrix.
     * 
     * @param FileName The `FileName` parameter is a string that represents the name of the input file
     * that contains the Floyd problem information.
     */
    public void readFloyd(String FileName)
    {
        try
        {    
            File file = new File(FileName);
            Scanner scanner = new Scanner(file);
            FileWriter fw = new FileWriter("output.txt");
            int count = 1;
            
            while(scanner.hasNext())
            {
                // The line `String s1 = scanner.nextLine().trim();` reads the next line from the input
                // file using the `Scanner` object and assigns it to the variable `s1`. The `trim()`
                // method is then called on `s1` to remove any leading or trailing whitespace from the
                // line.
                String s1 = scanner.nextLine().trim();
                
                // The `if(s1.startsWith("Problem"))` condition is checking if the current line read
                // from the file starts with the word "Problem". This is used to identify the lines
                // that contain the problem information, such as the size of the matrix and the matrix
                // values.
                if(s1.startsWith("Problem"))
                {
                    try 
                    {
                        String[] parts = s1.split(":");
                        String[] subParts = parts[1].trim().split(" ");
                        int n = Integer.parseInt(subParts[2]);
                        
                        int[][] W = new int[n][n];
                        int[][] D = new int[n][n];
                        int[][] P = new int[n][n];

                        for (int i = 0; i < n; i++) 
                        {
                            for (int j = 0; j < n; j++) 
                            {
                                W[i][j] = scanner.nextInt();
                                //System.out.print(W[i][j]+" ");
                            }
                        }
                    
                        fw.write("Problem "+count+": n = "+n+"\n");
                        FloydW(fw, n, D, P, W);
                        count++;
                    } 
                    catch (Exception e) 
                    {
                        System.err.println("ERROR");
                    }
                }
            }
            scanner.close(); 
            fw.close(); 
        }    
        catch (Exception e) 
        {
            System.err.println("Error: File Not Found !");
            e.printStackTrace();
        }
    }
}


