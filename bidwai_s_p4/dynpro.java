import java.io.*;
import java.util.*;

public class dynpro {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the input file as an argument.");
            return;
        }

        String inputFile = args[0];
        List<String> itemNames = new ArrayList<>();
        List<Integer> profits = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();
        int capacity = 0;
        int numberOfItems = 0;

        try {
            Scanner scanner = new Scanner(new File(inputFile));
            numberOfItems = scanner.nextInt();
            capacity = scanner.nextInt();

            while (scanner.hasNext()) {
                itemNames.add(scanner.next());
                profits.add(scanner.nextInt());
                weights.add(scanner.nextInt());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
            return;
        }

        int[] profitsArray = profits.stream().mapToInt(Integer::intValue).toArray();
        int[] weightsArray = weights.stream().mapToInt(Integer::intValue).toArray();

        int[][] knapsackValues = new int[numberOfItems + 1][capacity + 1];
        boolean[][] itemIncluded = new boolean[numberOfItems + 1][capacity + 1];

        for (int currentItem = 1; currentItem <= numberOfItems; currentItem++) {
            for (int currentCapacity = 1; currentCapacity <= capacity; currentCapacity++) {
                if (weightsArray[currentItem - 1] <= currentCapacity) {
                    if (profitsArray[currentItem - 1] + knapsackValues[currentItem - 1][currentCapacity - weightsArray[currentItem - 1]] > knapsackValues[currentItem - 1][currentCapacity]) {
                        knapsackValues[currentItem][currentCapacity] = profitsArray[currentItem - 1] + knapsackValues[currentItem - 1][currentCapacity - weightsArray[currentItem - 1]];
                        itemIncluded[currentItem][currentCapacity] = true;
                    } else {
                        knapsackValues[currentItem][currentCapacity] = knapsackValues[currentItem - 1][currentCapacity];
                    }
                } else {
                    knapsackValues[currentItem][currentCapacity] = knapsackValues[currentItem - 1][currentCapacity];
                }
            }
        }
        // Retrieve selected items
        int totalProfit = knapsackValues[numberOfItems][capacity];
        List<String> selectedItems = new ArrayList<>();
        int currentItem = numberOfItems;
        int currentCapacity = capacity;

        while (currentItem > 0 && totalProfit > 0) {
            if (knapsackValues[currentItem][currentCapacity] != knapsackValues[currentItem - 1][currentCapacity]) {
                selectedItems.add(itemNames.get(currentItem - 1));
                totalProfit -= profitsArray[currentItem - 1];
                currentCapacity -= weightsArray[currentItem - 1];
            }
            currentItem--;
        }

        // Write entries2.txt
        try {
            FileWriter entriesWriter = new FileWriter("entries2.txt");
            for (int k = 0; k <= numberOfItems; k++) {
                for (int w = 0; w <= capacity; w++) {
                    entriesWriter.write(knapsackValues[k][w] + " ");
                }
                entriesWriter.write("\n");
            }
            entriesWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write output2.txt
        try {
            FileWriter outputWriter = new FileWriter("output2.txt");
            int profit;
            int weight;
            String itemName;
            int totalW=0;
            for (int i = selectedItems.size() - 1; i >= 0; i--) {
                itemName = selectedItems.get(i);
                profit = profitsArray[itemNames.indexOf(itemName)];
                weight = weightsArray[itemNames.indexOf(itemName)];
                totalW += weight;
            }
            outputWriter.write(selectedItems.size() + " " + knapsackValues[numberOfItems][capacity]+" "+totalW + "\n");

            for (int i = selectedItems.size() - 1; i >= 0; i--) {
                itemName = selectedItems.get(i);
                profit = profitsArray[itemNames.indexOf(itemName)];
                weight = weightsArray[itemNames.indexOf(itemName)];
                totalW += weight;
                outputWriter.write(itemName + " " + profit + " " + weight + "\n");
            }
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}