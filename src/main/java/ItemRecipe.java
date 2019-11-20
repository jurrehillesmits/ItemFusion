public class ItemRecipe {
    private String Item;
    private String IngredientOne;
    private String IngredientTwo;
    private Item IngredientOneItem;
    private Item IngredientTwoItem;

    ItemRecipe(String Data){
        String[] DividedByPlus = Data.split("\\+");
        String[] DividedByEquals = DividedByPlus[1].split("=");
        this.IngredientOne = DividedByPlus[0].trim();
        this.IngredientTwo = DividedByEquals[0].trim();
        this.Item = DividedByEquals[1].trim();
    }

    public String getItem() {
        return Item;
    }
    public String getIngredientOne() {
        return IngredientOne;
    }
    public String getIngredientTwo() {
        return IngredientTwo;
    }
    public void setIngredientOneItem(Item ingredientOneItem) {
        IngredientOneItem = ingredientOneItem;
    }
    public void setIngredientTwoItem(Item ingredientTwoItem) {
        IngredientTwoItem = ingredientTwoItem;
    }
    public boolean IngredientOnePurchasable(){
        return IngredientOneItem.isPurchasable();
    }
    public boolean IngredientTwoPurchasable(){
        return IngredientTwoItem.isPurchasable();
    }

}
