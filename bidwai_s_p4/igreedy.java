import java.io.*;
import java.util.*;

/**
 * The Item class represents an item with a name, weight, and profit.
 */
class Item {
    String name;
    int weight;
    int profit;

    Item(String name, int weight, int profit) {
        this.name = name;
        this.weight = weight;
        this.profit = profit;
    }
}

public class igreedy {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File(args[0]));
             FileWriter writer = new FileWriter("output3.txt")) {

            // These lines of code are reading the first two integers from the input file using the
            // `Scanner` object `scanner`.
            int numItems = scanner.nextInt();
            int capacity = scanner.nextInt();

            List<Item> items = new ArrayList<>();

            // This code block is reading the input file and creating a list of `Item` objects.
            for (int i = 0; i < numItems; i++) {
                String itemName = scanner.next();
                int itemProfit = scanner.nextInt();
                int itemWeight = scanner.nextInt();
                items.add(new Item(itemName, itemWeight, itemProfit));
            }

            // Find the item with maximum benefit
            int maxB = 0;
            for (Item item : items) {
                maxB = Math.max(maxB, item.profit);
            }

            // These lines of code are initializing variables that will be used to keep track of the
            // current weight, current profit, and selected items in the knapsack problem.
            int bGreed = 0;
            int currWeight = 0;
            int currentProfit = 0;
            List<Item> selectedItems = new ArrayList<>();

            // Sort items by profit-to-weight ratio
            items.sort(Comparator.comparingDouble(item -> -((double) item.profit / item.weight)));

            // The code block is iterating over each item in the `items` list and checking if adding
            // the weight of the current item to the current weight (`currWeight`) is less than or
            // equal to the capacity (`capacity`). If it is, the item is added to the `selectedItems`
            // list, the weight and profit of the item are added to the respective variables
            // (`currWeight` and `currentProfit`), and the loop moves on to the next item. This process
            // continues until either the capacity is reached or there are no more items that can be
            // added without exceeding the capacity.
            for (Item item : items) {
                if (currWeight + item.weight <= capacity) {
                    selectedItems.add(item);
                    currWeight += item.weight;
                    currentProfit += item.profit;
                }
            }

            // `bGreed = currentProfit;` is assigning the value of `currentProfit` to the variable
            // `bGreed`.
            bGreed = currentProfit;

            // The line `List<Item> finalItems;` declares a variable named `finalItems` of type
            // `List<Item>`. This variable will be used to store the final set of items that will be
            // selected.
            List<Item> finalItems;
            int APP = Math.max(bGreed, maxB);

            // This code block is determining the final set of items to be selected based on two
            // conditions:
            if (bGreed >= APP) {
                finalItems = selectedItems;
            } else {
                finalItems = new ArrayList<>();
                // Select only the item with maximum benefit
                for (Item item : items) {
                    if (item.profit == maxB) {
                        finalItems.add(item);
                        break;
                    }
                }
            }
 
           // The line `finalItems.sort(Comparator.comparing(item -> item.name));` is sorting the
           // `finalItems` list of items based on their names in ascending order.
            finalItems.sort(Comparator.comparing(item -> item.name));
            
            // The code is writing the final solution to the output file.
            writer.write(finalItems.size() + " " + currentProfit + " " + currWeight + "\n");
            for (Item selectedItem : finalItems) {
                writer.write(selectedItem.name + " " + selectedItem.profit + " " + selectedItem.weight + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
