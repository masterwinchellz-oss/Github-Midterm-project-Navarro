import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class manage_Inventory {

    private List<item_system> items;

    public manage_Inventory() {
        this.items = new ArrayList<>();
    }

    private item_system createitem_system(String category, String id, String name, int quantity,
    double price) {

        switch (category) {
            case "Clothing":
                return new Clothing_Item_System(id, name, quantity, price);

            case "Electronic":
                return new Electronic_Item_System(id, name, quantity, price);

            case "Entertainment":
                return new Entertainment_Item_System(id, name, quantity, price);

            default:
                throw new IllegalArgumentException("Unknown Category " + category);
        }

    }

    public boolean additem_system(String category, String id, String name, int quantity, double price){

        item_system item = createitem_system(category, id, name, quantity, price);
        items.add(item);
        return true;

    }

    public item_system findById(String id){
        for (item_system item: items) {
            if (item.getId().equalsIgnoreCase(id)){
                return item;
            }

        }
        return null;

    }

    public item_system removeItem(String id){
        item_system item = findById(id);
        if (item != null) {
            items.remove(item);
        }
        return item;

    }

    public List<item_system> getItemsByCategory(String category) {
        List<item_system> result = new ArrayList<>();
        for (item_system item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return result;

    }

    public List<item_system> getAllItems(){
        return items;
    }

    public List<item_system> getLowStockItems() {
        List<item_system> result = new ArrayList<>();
        for (item_system item : items) {
            if (item.getQuantity() <= 5) {
                result.add(item);
            }
        }
        return result;
    }

    public List<item_system> getSortedItems(String sortBy, String order){
        List<item_system> sorted = new ArrayList<>(items);

        Comparator<item_system> comparator;

        if (sortBy.equalsIgnoreCase("Quantity")) {
            comparator = Comparator.comparingInt(item_system::getQuantity);
        } else {
            comparator = Comparator.comparingDouble(item_system::getPrice);
        }

        if (order.equalsIgnoreCase("Descending")) {
            comparator = comparator.reversed();
        }

        sorted.sort(comparator);
        return sorted;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
    
}
