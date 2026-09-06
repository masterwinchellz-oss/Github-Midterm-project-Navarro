import java.util.List;
import java.util.Scanner;

public class main{

    private static manage_Inventory manager = new manage_Inventory();

    public static void main (String [] args){
        
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        
        int choice;
        String id;
        String category;

        System.out.println("\n\t\t---Welcome to the inventory management!---\n");

        while (isRunning) {

            System.out.println("1) - \"Add Item\"");
            System.out.println("2) - \"Update Item\"");
            System.out.println("3) - \"Remove Item\"");
            System.out.println("4) - \"Display Items by Category\"");
            System.out.println("5) - \"Display All Items\"");
            System.out.println("6) - \"Search Item\"");
            System.out.println("7) - \"Sort Items\"");
            System.out.println("8) - \"Display Low Stock Items\"");
            System.out.println("9) - \"Exit\"");

            boolean isChoiceValid = false;
            choice = 0;

            while (!isChoiceValid) {
                System.out.print("Enter your choice: ");
                String choiceInput = scanner.nextLine().trim();

                if (!choiceInput.matches("[1-9]")) {
                    System.out.println("Please choose 1-9");
                    continue;
                }

                choice = Integer.parseInt(choiceInput);
                isChoiceValid = true;
            }

            switch (choice) {

                case 1: {
                    System.out.print("Input Category: ");
                    category = scanner.nextLine().trim();

                    if (!Validations.isValidCategory(category)) {
                        System.out.println("Invalid category. Please enter a valid category.");
                        continue;
                    }
                    String normalizedCategory = Validations.normalCategory(category);

                    String newId = null;
                    boolean isChecking = true;
                    while (isChecking) {
                        System.out.print("Input ID: ");
                        newId = scanner.nextLine();
                        if (Validations.isValidId(newId)) {
                            break;
                        }
                        System.out.println("Invalid ID. Please enter a valid ID.");
                    }

                    String name = null;
                    boolean isnameChecking = true;
                    while (isnameChecking) {
                        System.out.print("Input Name: ");
                        name = scanner.nextLine();
                        if (Validations.isValidName(name)) {
                            break;
                        }
                        System.out.println("Invalid Name. Please enter a valid Name.");
                    }

                    int quantity = 0;
                    boolean isQuantityChecking = true;
                    while (isQuantityChecking) {
                        System.out.print("Input Quantity: ");
                        String quantityInput = scanner.nextLine();
                        if (Validations.isValidQuantity(quantityInput)) {
                            quantity = Integer.parseInt(quantityInput.trim());
                            break;
                        }
                        System.out.println("Invalid Quantity. Must be a whole number.");
                    }

                    double price = 0;
                    boolean isPriceChecking = true;
                    while (isPriceChecking) {
                        System.out.print("Input Price: ");
                        String priceInput = scanner.nextLine();
                        if (Validations.isValidPrice(priceInput)) {
                            price = Double.parseDouble(priceInput.trim());
                            break;
                        }
                        System.out.println("Invalid price. Must be a number greater than 0.");
                    }

                    manager.additem_system(normalizedCategory, newId, name, quantity, price);
                    System.out.println("Item added successfully.");
                    break;
                }

                case 2: {
                    System.out.print("Input ID: ");
                    id = scanner.nextLine();

                    item_system itemToUpdate = manager.findById(id);
                    if (itemToUpdate == null) {
                        System.out.println("Item not found!");
                        continue;
                    }

                    String field = "";
                    boolean isFieldValid = true;
                    while (isFieldValid) {
                        System.out.print("Update Quantity or Price? (quantity/price): ");
                        field = scanner.nextLine();
                        if (Validations.isValidYes_No(field, "quantity", "price")) {
                            break;
                        }
                        System.out.println("Invalid input. Please enter 'quantity' or 'price'.");
                    }

                    if (field.trim().equalsIgnoreCase("Quantity")) {
                        int oldValue = itemToUpdate.getQuantity();
                        int newValue = oldValue;
                        boolean isNewValueValid = true;
                        while (isNewValueValid) {
                            System.out.print("Input new Quantity: ");
                            String input = scanner.nextLine();
                            if (Validations.isValidQuantity(input)) {
                                newValue = Integer.parseInt(input.trim());
                                break;
                            }
                            System.out.println("Invalid Quantity. Must be a whole number.");
                        }
                        itemToUpdate.setQuantity(newValue);
                        System.out.println("Quantity updated successfully. Old Value: " + oldValue + ", New Value: " + newValue);

                    } else {
                        double oldValue = itemToUpdate.getPrice();
                        double newValue = oldValue;
                        boolean isNewValueValid = true;
                        while (isNewValueValid) {
                            System.out.print("Input new Price: ");
                            String input = scanner.nextLine();
                            if (Validations.isValidPrice(input)) {
                                newValue = Double.parseDouble(input.trim());
                                break;
                            }
                            System.out.println("Invalid Price. Must be a number greater than 0.");
                        }
                        itemToUpdate.setPrice(newValue);
                        System.out.println("Price updated successfully. Old Value: " + oldValue + ", New Value: " + newValue);
                    }
                    break;
                }

                case 3: {
                    System.out.print("Input ID: ");
                    id = scanner.nextLine();

                    item_system removed = manager.removeItem(id);
                    if (removed != null) {
                        System.out.println("Item " + removed.getName() + " has been removed from the inventory.");
                    } else {
                        System.out.println("Item not found!");
                    }
                    break;
                }

                case 4: {
                    System.out.print("Input Category: ");
                    category = scanner.nextLine();

                    if (!Validations.isValidCategory(category)) {
                        System.out.println("Category " + category + " does not exist!");
                        continue;
                    }

                    List<item_system> results = manager.getItemsByCategory(Validations.normalCategory(category));
                    if (results.isEmpty()) {
                        System.out.println("No items found in this category.");
                        continue;
                    }
                    printTable(results, false);
                    break;
                }

                case 5: {
                    List<item_system> all_items = manager.getAllItems();
                    if (all_items.isEmpty()) {
                        System.out.println("Inventory is Empty.");
                        continue;
                    }
                    printTable(all_items, false);
                    break;
                }

                case 6: {
                    System.out.print("Input ID: ");
                    id = scanner.nextLine();

                    item_system item = manager.findById(id);
                    if (item == null) {
                        System.out.println("Item not found!");
                        continue;
                    }

                    System.out.println("ID: " + item.getId());
                    System.out.println("Name: " + item.getName());
                    System.out.println("Quantity: " + item.getQuantity());
                    System.out.println("Price: " + item.getPrice());
                    System.out.println("Category: " + item.getCategory() + "\n");
                    break;
                }

                case 7: {
                    String sortBy = "";
                    boolean isSortByValid = true;
                    while (isSortByValid) {
                        System.out.print("Sort by Quantity or Price? (quantity/price): ");
                        sortBy = scanner.nextLine();
                        if (Validations.isValidYes_No(sortBy, "quantity", "price")) {
                            break;
                        }
                        System.out.println("Invalid input. Please enter 'quantity' or 'price'.");
                    }

                    String order = "";
                    boolean isOrderValid = true;
                    while (isOrderValid) {
                        System.out.print("Ascending or Descending? (Ascending/Descending): ");
                        order = scanner.nextLine();
                        if (Validations.isValidYes_No(order, "ascending", "descending")) {
                            break;
                        }
                        System.out.println("Invalid input. Please enter 'ascending' or 'descending'.");
                    }

                    List<item_system> sortedItems = manager.getSortedItems(sortBy.trim(), order.trim());
                    if (sortedItems.isEmpty()) {
                        System.out.println("Inventory is Empty.");
                        continue;
                    }
                    printTable(sortedItems, true);
                    break;
                }

                case 8: {
                    List<item_system> lowStock = manager.getLowStockItems();
                    if (lowStock.isEmpty()) {
                        System.out.println("No low stock items.");
                        continue;
                    }
                    printTable(lowStock, true);
                    break;
                }

                case 9:
                    isRunning = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
            }
        }

        scanner.close();
    }

    private static void printTable(List<item_system> list, boolean includeCategory) {
        if (includeCategory) {
            System.out.printf("%-8s %-15s %-10s %-10s %-15s%n", "ID", "Name", "Quantity", "Price", "Category");
        } else {
            System.out.printf("%-8s %-15s %-10s %-10s%n", "ID", "Name", "Quantity", "Price");
        }
        for (item_system item : list) {
            if (includeCategory) {
                System.out.println(item.toTableShow());
            } else {
                System.out.printf("%-8s %-15s %-10d %-10.2f%n",
                item.getId(), item.getName(), item.getQuantity(), item.getPrice());
            }
        }
        System.out.println();
    }
}