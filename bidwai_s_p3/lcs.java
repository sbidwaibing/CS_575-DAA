public class lcs
{
    public static void main(String[] args)
    {
        // The code snippet is checking if the command line arguments `args[0]` and `args[1]` are not
        // null. If both arguments are not null, it proceeds to convert the arguments to strings `S1`
        // and `S2`. Then, it creates an instance of the `lcs` class and calls the `Lcs` method,
        // passing the converted strings `S1` and `S2` as arguments. This is done to ensure that valid
        // string arguments are provided before executing the logic inside the `Lcs` method.
        try 
        {
            // The code snippet is checking if the command line arguments `args[0]` and `args[1]` 
            //are not null. If both arguments are not null, it proceeds to convert the arguments to
            // strings `S1` and `S2`. Then, it creates an instance of the `lcs` class and calls the
            // `Lcs` method, passing the converted strings `S1` and `S2` as arguments. This is done to
            // ensure that valid string arguments are provided before executing the logic inside the
            // `Lcs` method.
            if(args[0] != null && args[1] != null)
            {
                String S1 = args[0].toString();
                String S2 = args[1].toString();

                lcs l = new lcs();
                l.Lcs(S1, S2);
            }
        }
        // The `catch (ArrayIndexOutOfBoundsException e)` block is used to handle the case when the
        // user does not provide valid string arguments as command line arguments. If an
        // `ArrayIndexOutOfBoundsException` occurs, it means that the user did not provide enough
        // command line arguments.
        catch (ArrayIndexOutOfBoundsException e) 
        {
            // The code snippet is handling the case when the user does not provide valid string
            // arguments as command line arguments. It prints an error message indicating that the
            // entered value is not a valid string argument. Then, it counts down from 3 to 1,
            // indicating that the program is exiting. Finally, it terminates the program by calling
            // `System.exit(0)` and prints the stack trace of the exception that occurred.
            System.out.println();
            System.out.println("ERROR: Entered value is not a valid String Argument");
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
     * The function `Lcs` implements the dynamic programming approach to find the length and the actual
     * Longest Common Subsequence (LCS) between two input strings `s1` and `s2`.
     * 
     * @param s1 The first input string, denoted as `s1`.
     * @param s2 The parameter `s2` is a string representing the second input string for finding the
     * Longest Common Subsequence (LCS).
     */
    public void Lcs(String s1, String s2)
    {
        // The line `int[][] DynA = new int[s1.length() + 1][s2.length() + 1];` is creating a 2D array
        // called `DynA` with dimensions `s1.length() + 1` rows and `s2.length() + 1` columns.
        int[][] DynA = new int[s1.length() + 1][s2.length() + 1];

        // The code snippet is implementing the dynamic programming approach to find the length of the
        // Longest Common Subsequence (LCS) between two strings, `s1` and `s2`.
        for (int i = 1; i <= s1.length(); i++) 
        {
            for (int j = 1; j <= s2.length(); j++) 
            {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) 
                {
                    DynA[i][j] = DynA[i - 1][j - 1] + 1;
                } 
                else 
                {
                    DynA[i][j] = Math.max(DynA[i - 1][j], DynA[i][j - 1]);
                }
            }
        }

        // Reconstruct LCS from DynA table
        // This code snippet is used to reconstruct the Longest Common Subsequence (LCS) from the
        // dynamic programming table `DynA`.
        int i = s1.length();
        int j = s2.length();
        StringBuilder lcs = new StringBuilder();
        
        // The code snippet is used to reconstruct the Longest Common Subsequence (LCS) from the
        // dynamic programming table `DynA`.
        while (i > 0 && j > 0) 
        {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) 
            {
                lcs.insert(0, s1.charAt(i - 1));
                i--;
                j--;
            } 
            else 
            {
                if(DynA[i - 1][j] > DynA[i][j - 1]) 
                {
                    i--;
                }
                else 
                {
                    j--;
                }
            }
        }
        // The code snippet `System.out.println("LCS: "+lcs.toString().length());` is printing the
        // length of the Longest Common Subsequence (LCS) between the two input strings `S1` and `S2`.
        System.out.println();
        System.out.println("LCS: "+lcs.toString().length());
        System.out.println("LCS: "+lcs.toString());
        System.out.println();
    }
}
