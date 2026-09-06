public class Electronic_Item_System extends item_system {

    public Electronic_Item_System(String id, String name, int quantity, double price){
        super(id, name, quantity, price);
    }
    
    @Override
    public String getCategory(){
        return "Electronic";
    }
}
