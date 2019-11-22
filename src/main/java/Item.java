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
    boolean PurchasableAtStore(String store){
        return Stores.contains(store);
    }
/*
    int DeterminePrice(String store){
        if(PurchasableAtStore(store)) {
            return Cost;
        }
        List<Item> checkedItems = new ArrayList<>();
        return DeterminePrice(RecipeList,store,checkedItems);
    }
    int DeterminePrice(String store,List<Item> checkedItems){
        if(PurchasableAtStore(store)) {
            return Cost;
        }
        return DeterminePrice(TempPossible,store,checkedItems);
    }

    int DeterminePrice(List<ItemRecipe> Possible,String Store) {
            int Price;
            int Temp = 0;
            for (ItemRecipe IR : Possible) {
                Price = IR.DetermineRecipePrice(Store);
                if (Price > 0 && Price < Temp) {
                    Temp = Price;
                } else if (Temp == 0) {
                    Temp = Price;
                }
            }
            return Temp;
        }
        return -1;
    }
    int DetermineEarliestPrice(){
        try {
            String Store;
            for (int i = 1; i < 6; i++) {
                Store = Integer.toString(i);
                List<Item> ItemCheck = new ArrayList<>();
                if(PurchaseOrFuseAtStore(Store,ItemCheck)) {
                    List<Item> checkedItems = new ArrayList<>();
                    return DeterminePrice(TempPossible,Store,checkedItems);
                }
            }
            throw new IllegalArgumentException("Never possible to make " + this.Name);
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
*/

/*
    List<ItemRecipe> TempPossible;
    boolean PurchaseOrFuseAtStore(String store,List<Item> checkedItems){
        List<Item> copyCheck = new ArrayList<>(checkedItems);
        if(!copyCheck.contains(this)) {
            copyCheck.add(this);
            this.TempPossible = new ArrayList<>();
            if (PurchasableAtStore(store)) {
                return true;
            }
            else {
                boolean temp = false;
                for (ItemRecipe IR : RecipeList) {
                    if (IR.PurchaseableAtStore(store, copyCheck)) {
                        this.TempPossible.add(IR);
                        temp = true;
                    }
                }
                if (temp) {
                    return true;
                }
            }
        }
        this.TempPossible = new ArrayList<>();
        return false;
    }

*/

    //-----------------------------------------------------------------------------
    void printCheapestItemRecipe() {
        if(itemCanBeBoughtOrMadeFromStore("5")){

        }
    }
    void printEarliestItemRecipe() {
        String store;
        for(int i =1;i<6;i++){
            store =Integer.toString(i);
            if(itemCanBeBoughtOrMadeFromStore(store)){
                if(this.PurchasableAtStore(store)){

                }
                else{

                }
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


    boolean itemCanBeBoughtOrMadeFromStore(String store) {
        List<Item> checkedItems = new ArrayList<>();
        return this.itemCanBeBoughtOrMadeFromStore(store,checkedItems);
    }
    boolean itemCanBeBoughtOrMadeFromStore(String store,List<Item> checkedItems) {
        if(PurchasableAtStore(store)) {
            return true;
        }
        else {
            List<Item> copyCheckedItems = new ArrayList<>(checkedItems);
            copyCheckedItems.add(this);
            return hasRecipeThatCanBeMadeAtStore(store, copyCheckedItems);
        }
    }
    boolean hasRecipeThatCanBeMadeAtStore(String store,List<Item> checkedItems) {
        boolean temp = false;
        for(ItemRecipe IR: RecipeList) {
            if(IR.ThisRecipeCanBeMadeOrBoughtAtStore(store,checkedItems)) {
                temp = true;
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
        if(PurchasableAtStore(store)) {
            return getCost();
        }
        else {
            this.buildCheapestRecipe(store);
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
