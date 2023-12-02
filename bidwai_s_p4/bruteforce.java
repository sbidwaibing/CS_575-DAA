import java.io.*;
import java.util.*;

public class bruteforce {

    /**
     * The main function reads input from a file, processes the data, solves the knapsack problem, and
     * writes the results to an output file.
     */
    public static void main(String[] args) {
       
        if (args.length != 1) {
            System.out.println("Please provide the input file name.");
            return;
        }

        // These lines are declaring and initializing variables.
        int capacity = 0;
        int[] profits;
        int[] weights;

        // This code block is responsible for reading the input file, processing the data, solving the
        // knapsack problem, and writing the results to an output file.
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] firstLine = br.readLine().split(" ");
            int numItems = Integer.parseInt(firstLine[0]);
            capacity = Integer.parseInt(firstLine[1]);

            profits = new int[numItems];
            weights = new int[numItems];

            String line;
            int index = 0;
            // This code block is responsible for reading each line of the input file and extracting
            // the profit and weight values for each item.
            while ((line = br.readLine()) != null) {
                String[] itemData = line.split(" ");
                weights[index] = Integer.parseInt(itemData[2]);
                profits[index] = Integer.parseInt(itemData[1]);
                index++;
            }

            int[] selectedItems = solveKnapsack(profits, weights, capacity);
            writeToFile(selectedItems, profits, weights, "output1.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function `solveKnapsack` takes in arrays of profits and weights for different items, as well
     * as the capacity of the knapsack, and returns an array indicating which items should be selected
     * to maximize the total profit while staying within the capacity.
     * 
     * @param profits An array of integers representing the profits of each item. The length of the
     * array is the number of items.
     * @param weights An array of integers representing the weights of the items available for
     * selection in the knapsack. The length of the array is equal to the number of items available.
     * @param capacity The capacity parameter represents the maximum weight that the knapsack can hold.
     * @return The method `solveKnapsack` returns an array of integers representing the selected items.
     * Each element in the array corresponds to an item, and a value of 1 indicates that the item is
     * selected, while a value of 0 indicates that the item is not selected.
     */
    public static int[] solveKnapsack(int[] profits, int[] weights, int capacity) {
    int n = profits.length;
    int maxProfit = 0;
    int[] selectedItems = new int[n];
    int[] tempSelection = new int[n];

    // The code block `for (int i = 1; i < (1 << n); i++)` is responsible for iterating through all
    // possible combinations of items in the knapsack.
    for (int i = 1; i < (1 << n); i++) {
        int totalWeight = 0;
        int totalProfit = 0;

        // The code block `for (int j = 0; j < n; j++) {
        //             if ((i & (1 << j)) > 0) {
        //                 totalWeight += weights[j];
        //                 totalProfit += profits[j];
        //                 tempSelection[j] = 1;
        //             }
        //         }` is responsible for calculating the total weight and total profit of a particular
        // combination of items in the knapsack.
        for (int j = 0; j < n; j++) {
            if ((i & (1 << j)) > 0) {
                totalWeight += weights[j];
                totalProfit += profits[j];
                tempSelection[j] = 1;
            }
        }

        // This code block is checking if the current combination of items has a total weight that is
        // less than or equal to the capacity of the knapsack and a total profit that is greater than
        // the current maximum profit.
        if (totalWeight <= capacity && totalProfit > maxProfit) {
            maxProfit = totalProfit;
            System.arraycopy(tempSelection, 0, selectedItems, 0, n);
        }
        Arrays.fill(tempSelection, 0); 
    }
    return selectedItems;
}

    public static void writeToFile(int[] selectedItems, int[] profits, int[] weights, String filename) throws IOException {
        
        // The code block is initializing variables and creating a PrintWriter object to write data to
        // a file.
        PrintWriter writer = new PrintWriter(new FileWriter(filename, false));
        int totalProfit = 0;
        int totalWeight = 0;
        int itemCount = 0; 

       
        // The code block is iterating over the `selectedItems` array and checking if the value at each
        // index is equal to 1. If it is, it means that the corresponding item was selected in the
        // knapsack.
        for (int i = 0; i < selectedItems.length; i++) {
            if (selectedItems[i] == 1) {
                totalProfit += profits[i];
                totalWeight += weights[i];
                itemCount++;
            }
        }

        // The line `writer.println(itemCount + " " + totalProfit + " " + totalWeight);` is writing a
        // line to the output file. It concatenates the values of `itemCount`, `totalProfit`, and
        // `totalWeight` into a single string, separated by spaces, and then writes that string to the
        // file followed by a new line character.
        writer.println(itemCount + " " + totalProfit + " " + totalWeight); 

        // The code block is iterating over the `selectedItems` array and checking if the value at each
        // index is equal to 1. If it is, it means that the corresponding item was selected in the
        // knapsack.
        for (int i = 0; i < selectedItems.length; i++) {
            if (selectedItems[i] == 1) {
                writer.println("Item" + (i + 1) + " " + profits[i] + " " + weights[i]);
            }
        }
        writer.close();
    }   
}
