import java.io.*;
import java.util.*;

public class createkn01 {
    /**
     * The main function checks if an output file name is provided as a command line argument, and if
     * so, calls the generateAndWriteToFile function with that file name.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            String opfile = args[0];
            generateAndWriteToFile(opfile);
        } else {
            System.out.println("Please provide the output file name as an argument.");
        }
    }

    /**
     * The function generates random profits and weights for a given number of items, calculates the
     * capacity based on the weights, and writes the data to a file.
     * 
     * @param opfile The parameter "opfile" is a string that represents the name or path of the output
     * file where the generated data will be written to.
     */
    public static void generateAndWriteToFile(String opfile) {
        Random random = new Random();
        int n = random.nextInt(6) + 5;
        List<Integer> profits = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();

        generateItems(n, profits, weights, random);

        int capacity = CalcCap(weights);
        writeToFile(n, capacity, opfile, profits, weights);
    }

    /**
     * The function generates a specified number of items with random profits and weights and adds them
     * to the given lists.
     * 
     * @param n The parameter "n" represents the number of items to generate.
     * @param profits A list of integers representing the profits of each item.
     * @param weights The "weights" parameter is a List of Integers that represents the weights of the
     * items being generated.
     * @param random The "random" parameter is an instance of the Random class, which is used to
     * generate random numbers. It is passed as an argument to the method so that the method can use it
     * to generate random values for the profits and weights of the items.
     */
    public static void generateItems(int n, List<Integer> profits, List<Integer> weights, Random random) {
        for (int i = 0; i < n; i++) {
            int profit = random.nextInt(21) + 10;
            int weight = random.nextInt(16) + 5;
            profits.add(profit);
            weights.add(weight);
        }
    }

    /**
     * The function calculates the capacity by summing up the weights in a list and returning 60% of
     * the total weight.
     * 
     * @param weights The "weights" parameter is a List of Integers. It represents the weights of
     * different items.
     * @return The method is returning an integer value, which is the calculated capacity based on the
     * total weight of the items in the given list.
     */
    public static int CalcCap(List<Integer> weights) {
        int totalWeight = weights.stream().mapToInt(Integer::intValue).sum();
        return (int) (0.6 * totalWeight);
    }

    /**
     * The function writes the values of n, capacity, profits, and weights to a file in a specific
     * format.
     * 
     * @param n The parameter "n" represents the number of items in the list.
     * @param capacity The capacity parameter represents the maximum weight that can be carried in a
     * knapsack. It is used to determine if an item can be included in the knapsack or not based on its
     * weight.
     * @param opfile The parameter "opfile" is a String that represents the file path where the output
     * will be written. It specifies the location and name of the file where the data will be saved.
     * @param profits The `profits` parameter is a list of integers representing the profits of each
     * item. Each element in the list corresponds to the profit of a specific item.
     * @param weights The `weights` parameter is a list of integers representing the weights of each
     * item. Each weight corresponds to an item in the list.
     */
    public static void writeToFile(int n, int capacity, String opfile, List<Integer> profits, List<Integer> weights) {
        String filePath = opfile;
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(n + " " + capacity + "\n");
            for (int i = 0; i < n; i++) {
                bufferedWriter.write("Item"+(i + 1)+" "+profits.get(i)+" "+ weights.get(i)+"\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Content has not been written to the File");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
