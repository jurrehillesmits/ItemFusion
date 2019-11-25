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
        this.Name = Name.trim();
    }
    Item(String Data, String Store){
        String[] Divided =Data.split("~");
        this.Name = Divided[0].trim().replace("*", "");
        this.Cost = Integer.parseInt(Divided[1].trim());
        this.Stores.add(Store);
        this.Purchasable = true;
    }
    void AddStoreAndPrice(List<String> importedStores,int Cost){
        this.Cost = Cost;
        for(String str:importedStores) {
            if(!this.Stores.contains(str)) {
                this.Stores.add(str);
            }
        }
        this.Purchasable = true;
    }
    void AddStoreAndPrice(String Store,int Cost){
        this.Cost = Cost;
        if(!this.Stores.contains(Store)) {
            this.Stores.add(Store);
        }
        this.Purchasable = true;
    }
    void AddRecipe(ItemRecipe IR){
        RecipeList.add(IR);
    }
    boolean purchasableAtStore(String store){
        return Stores.contains(store);
    }

    //-----------------------------------------------------------------------------
    void printCheapestRecipe(){
        printItemRecipeTree("5");
    }

    boolean printItemRecipeTree(String store) {
        if(itemCanBeBoughtOrMadeFromStore(store)){
            if(this.purchasableAtStore(store)){
                System.out.println(this.getName()+" can be bought directly for "+this.getCost());
            }
            else{
                int total = this.determineCheapestPrice(store);
                String itemRecipeTree = cheapestItemRecipe.recipeTree(store);
                System.out.println(itemRecipeTree+"\nThis recipe can be made from store "+store+" for:"+total);
            }
            return true;
        }
        return false;
    }
    void printEarliestItemRecipe() {
        String store;
        for(int i =1;i<6;i++){
            store =Integer.toString(i);
            if(printItemRecipeTree(store)){
                break;
            }
        }
    }
    int DeterminePrice() {
        return this.DeterminePrice("5");
    }

    int DeterminePrice(String store){
        temporaryPurchasableAtStoreList.clear();
        if(itemCanBeBoughtOrMadeFromStore(store)){
            return determineCheapestPrice(store);
        }
        else{
            return -1;
        }
    }

    int DetermineEarliestPrice() {
        String store;
        temporaryPurchasableAtStoreList.clear();
        for (int i = 1; i < 6; i++) {
            store = Integer.toString(i);
            if (itemCanBeBoughtOrMadeFromStore(store)) {
                return determineCheapestPrice(store);
            }
        }
        return -1;
    }

//-------------------------------------------------------------------------

    //Start
    boolean itemCanBeBoughtOrMadeFromStore(String store) {
        List<Item> checkedItems = new ArrayList<>();
        List<ItemRecipe> checkedRecipes = new ArrayList<>();
        return this.itemCanBeBoughtOrMadeFromStore(store,checkedItems,checkedRecipes);
    }

    boolean itemCanBeBoughtOrMadeFromStore(String store,List<Item> checkedItems,List<ItemRecipe> checkedRecipes) {
        if(purchasableAtStore(store)) {
            return true;
        }
        else {
            List<Item> copyCheckedItems = new ArrayList<>(checkedItems);
            copyCheckedItems.add(this);
            return hasRecipeThatCanBeMadeAtStore(store, copyCheckedItems,checkedRecipes);
        }
    }
    boolean hasRecipeThatCanBeMadeAtStore(String store,List<Item> checkedItems,List<ItemRecipe> checkedRecipes) {
        boolean temp = false;
        for(ItemRecipe IR: RecipeList) {
            if(checkedRecipes.contains(IR)) {
                if(IR.tempCanBeMadeFromStore) {
                    temp = true;
                }
            }
            else{
                checkedRecipes.add(IR);
                if (IR.ThisRecipeCanBeMadeOrBoughtAtStore(store, checkedItems,checkedRecipes)) {
                    temp = true;
                }
            }
        }
        return temp;
    }
    List<ItemRecipe> temporaryPurchasableAtStoreList = new ArrayList<>();
    void addRecipeToTemporaryPurchasableAtStoreList(ItemRecipe IR) {
        temporaryPurchasableAtStoreList.add(IR);
    }
//------------------------------------------------------------------------


    ItemRecipe cheapestItemRecipe;
    void buildCheapestRecipe(String store) {
        int storeValue =0;
        int temp;
        for (ItemRecipe IR : temporaryPurchasableAtStoreList) {
        temp = IR.cheapestPrice(store);
            if(temp<storeValue) {
                storeValue = temp;
                this.cheapestItemRecipe =IR;
            }
            else if(storeValue==0) {
                storeValue = temp;
                this.cheapestItemRecipe =IR;
            }
        }
    }
    int determineCheapestPrice(String store) {
        if(purchasableAtStore(store)) {
            return getCost();
        }
        else {
            if(this.cheapestItemRecipe==null) {
                this.buildCheapestRecipe(store);
            }
            return this.cheapestItemRecipe.cheapestPrice(store);
        }
    }



//--------------------------------------------------------------------------


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
