import java.util.ArrayList;
import java.util.List;
public class Item {
    private String Name;
    private int Cost;
    private List<String> Stores = new ArrayList<>();
    private List<ItemRecipe> RecipeList = new ArrayList<>();
    private boolean Purchasable = false;
//Empty item in case an item cannot be bought
    Item(String Name){
        this.Name = Name;
    }
    Item(String Data, String Store){
        String[] Divided =Data.split("~");
        this.Name = Divided[0].trim();
        this.Cost = Integer.parseInt(Divided[1].trim());
        this.Stores.add(Store);
        this.Purchasable = true;
    }
    void AddStoreAndPrice(List<String> importedStores,int Cost){
        this.Cost = Cost;
        this.Stores.addAll(importedStores);
        this.Purchasable = true;
    }
    void AddRecipe(ItemRecipe IR){
        RecipeList.add(IR);
    }

    boolean CanBeBoughtAt(String Store){
        return Stores.contains(Store);
    }





    public List<ItemRecipe> getRecipeList() {
        return RecipeList;
    }
    public String getName() {
        return Name;
    }
    public int getCost() {
        return Cost;
    }
    public List<String> getStores() {
        return Stores;
    }
    public boolean isPurchasable() {
        return Purchasable;
    }
}
