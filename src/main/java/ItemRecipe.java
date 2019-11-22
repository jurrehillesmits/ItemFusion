import java.util.List;

public class ItemRecipe {
    private String itemName;
    private String ingredientOneName;
    private String ingredientTwoName;



    private Item item;
    private Item ingredientOne;
    private Item ingredientTwo;

    ItemRecipe(String Data){
        String[] DividedByPlus = Data.split("\\+");
        String[] DividedByEquals = DividedByPlus[1].split("=");
        this.ingredientOneName = DividedByPlus[0].trim();
        this.ingredientTwoName = DividedByEquals[0].trim();
        this.itemName = DividedByEquals[1].trim();
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(Item itemName) {
        this.item = itemName;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public String getIngredientOneName() {
        return ingredientOneName;
    }
    public String getIngredientTwoName() {
        return ingredientTwoName;
    }
    public void setIngredientOne(Item ingredientOne) {
        this.ingredientOne = ingredientOne;
    }
    public void setIngredientTwo(Item ingredientTwo) {
        this.ingredientTwo = ingredientTwo;
    }
    public boolean IngredientOnePurchasable(){
        return ingredientOne.isPurchasable();
    }
    public boolean IngredientTwoPurchasable(){
        return ingredientTwo.isPurchasable();
    }
    public Item getIngredientOne() {
        return ingredientOne;
    }
    public Item getIngredientTwo() {
        return ingredientTwo;
    }



    boolean ThisRecipeCanBeMadeOrBoughtAtStore(String store,List<Item> checkedItems){
        if(checkedItems.contains(ingredientOne)||checkedItems.contains(ingredientTwo)){
            return false;
        }
        else if(ingredientOne.itemCanBeBoughtOrMadeFromStore(store,checkedItems)&&ingredientTwo.itemCanBeBoughtOrMadeFromStore(store,checkedItems)){
            item.addRecipeToTemporaryPurchasableAtStoreList(this);
            return true;
        }
        return false;
    }

    int cheapestPrice(String store){
        return ingredientOne.determineCheapestPrice(store)+ingredientTwo.determineCheapestPrice(store);
    }

    String printRecipe(){
        return item.getName()+"="+ingredientOne.getName()+"+"+ingredientTwo.getName();
    }

}
