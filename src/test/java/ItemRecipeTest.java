import org.junit.Assert;
import org.junit.Test;
public class ItemRecipeTest {
    @Test
    public void CreationFromDataTest(){
            ItemRecipe IR = new ItemRecipe(" IngredientOne + IngredientTwo = Item ");
            Assert.assertEquals("IngredientOne", IR.getIngredientOneName());
            Assert.assertEquals("IngredientTwo", IR.getIngredientTwoName());
            Assert.assertEquals("Item", IR.getItemName());
    }

    @Test
    public void BothIngredientCanBeBought(){
        ItemRecipe IR = new ItemRecipe(" IngredientOne + IngredientTwo = Item ");
        Item IngredientOneItem = new Item("Metal ~ 720","1");
        Item IngredientTwoItem = new Item("Wood ~ 940","3");
        IR.setIngredientOne(IngredientOneItem);
        IR.setIngredientTwo(IngredientTwoItem);
        Assert.assertTrue(IR.IngredientOnePurchasable());
        Assert.assertTrue(IR.IngredientTwoPurchasable());
    }

    @Test
    public void OneIngredientCanBeBought(){
        ItemRecipe IR = new ItemRecipe(" IngredientOne + IngredientTwo = Item ");
        Item IngredientOneItem = new Item("Metal ~ 720","1");
        Item IngredientTwoItem = new Item("Wood");
        IR.setIngredientOne(IngredientOneItem);
        IR.setIngredientTwo(IngredientTwoItem);
        Assert.assertTrue(IR.IngredientOnePurchasable());
        Assert.assertFalse(IR.IngredientTwoPurchasable());
    }
}
