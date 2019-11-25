import java.util.List;

public class ItemRecipe {
    private String itemName;
    private String ingredientOneName;
    private String ingredientTwoName;
    private Item item;
    private Item ingredientOne;
    private Item ingredientTwo;

    ItemRecipe(String Data) {
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

    public Item getItem() {
        return item;
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

    public boolean IngredientOnePurchasable() {
        return ingredientOne.isPurchasable();
    }

    public boolean IngredientTwoPurchasable() {
        return ingredientTwo.isPurchasable();
    }

    public Item getIngredientOne() {
        return ingredientOne;
    }

    public Item getIngredientTwo() {
        return ingredientTwo;
    }


    boolean tempCanBeMadeFromStore;

    boolean ThisRecipeCanBeMadeOrBoughtAtStore(String store, List<Item> checkedItems, List<ItemRecipe> checkedRecipes) {
        tempCanBeMadeFromStore = false;
        if (checkedItems.contains(ingredientOne) || checkedItems.contains(ingredientTwo)) {
            return false;
        } else if (ingredientOne.itemCanBeBoughtOrMadeFromStore(store, checkedItems, checkedRecipes) && ingredientTwo.itemCanBeBoughtOrMadeFromStore(store, checkedItems, checkedRecipes)) {
            item.addRecipeToTemporaryPurchasableAtStoreList(this);
            tempCanBeMadeFromStore = true;
            return true;
        }
        return false;
    }

    int cheapestPrice(String store) {
        return ingredientOne.determineCheapestPrice(store) + ingredientTwo.determineCheapestPrice(store);
    }

    String printRecipe() {
        return item.getName() + "=" + ingredientOne.getName() + "+" + ingredientTwo.getName();
    }

    String recipeTree(String store) {
        String recipeTree = "(";
        recipeTree=recipeTree.concat(item.getName() + "=");
        if (ingredientOne.purchasableAtStore(store)) {
            recipeTree=recipeTree.concat(ingredientOne.getName() + "(" + ingredientOne.getCost() + ")");
        } else {
            recipeTree=recipeTree.concat(ingredientOne.cheapestItemRecipe.recipeTree(store));
        }
        recipeTree=recipeTree.concat("+");
        if (ingredientTwo.purchasableAtStore(store)) {
            recipeTree=recipeTree.concat(ingredientTwo.getName() + "(" + ingredientTwo.getCost() + ")");
        } else {
            recipeTree=recipeTree.concat(ingredientTwo.cheapestItemRecipe.recipeTree(store));
        }
        recipeTree=recipeTree.concat(")");
        return recipeTree;
    }
}