public abstract class item_system {

    private String id;
    private String name;
    private int quantity;
    private double price;

    public item_system (String id, String name, int quantity, double price){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;

    }

    // Get stuff //

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public abstract String getCategory();

    public String toTableShow() {
        return String.format("%-8s %-15s %-10d %-10.2f %-15s", 
        id, name, quantity, price, getCategory());

    }

}