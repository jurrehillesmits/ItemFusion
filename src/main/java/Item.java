import java.util.ArrayList;
import java.util.List;
public class Item {
    public String getName() {
        return Name;
    }
    public int getCost() {
        return Cost;
    }
    public List<String> getStores() {
        return Stores;
    }
    public boolean isPurchaseable() {
        return Purchaseable;
    }
    private String Name;
    private int Cost;
    private List<String> Stores = new ArrayList<String>();
    private boolean Purchaseable = false;
//Empty item in case an item cannot be bought
    Item(String Name){
        this.Name = Name;
    }

    Item(String Data, String Store){
        String[] Divided =Data.split("~");
        this.Name = Divided[0].trim();
        this.Cost = Integer.parseInt(Divided[1].trim());
        this.Stores.add(Store);
        this.Purchaseable = true;
    }
    void AddStoreAndPrice(List<String> importedStores,String Cost){
        this.Cost = Integer.parseInt(Cost);
        this.Stores.addAll(importedStores);
        this.Purchaseable = true;
    }


}
