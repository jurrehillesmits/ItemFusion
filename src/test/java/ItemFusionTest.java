import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class ItemFusionTest {
    private ItemFusion itemFusion = new ItemFusion("C:\\Users\\jsmits\\itemfusion-java\\src\\main\\java\\item-fusion-data.txt");
    private HashMap<String, Item> ItemMap = itemFusion.getItemMap();
    private HashMap<String, List<ItemRecipe>> ItemRecipeMap = itemFusion.getItemRecipeMap();
    @Test
    public void CreationTest() {
        Assert.assertFalse(ItemMap.isEmpty());
        Assert.assertFalse(ItemRecipeMap.isEmpty());
    }
    @Test
    public void AddingRecipesToItemsTest(){
        Item TestItem = ItemMap.get("Leather Mail");
        Assert.assertTrue(TestItem.getRecipeList().size()>0);
    }
    @Test
    public void AddingItemsToRecipesTest(){
        for(List<ItemRecipe> ItemRecipeList:ItemRecipeMap.values()) {
            for(ItemRecipe TestItemRecipe:ItemRecipeList) {
                Assert.assertNotNull(TestItemRecipe.getIngredientOne());
                Assert.assertNotNull(TestItemRecipe.getIngredientTwo());
                Assert.assertNotNull(TestItemRecipe.getItem());
            }
        }
    }
    @Test
    public void gettingCheapestPriceSpecificItem(){
        Item testItem = ItemMap.get("At Dusk");
        Assert.assertTrue(testItem.DeterminePrice()>0);
        Assert.assertEquals(1220,testItem.DeterminePrice());
    }
    @Test
    public void gettingEarliestPriceSpecificItem(){
        Item testItem = ItemMap.get("Leather Mail");
        int testValue = testItem.DetermineEarliestPrice();
        Assert.assertTrue(testValue>0);
        Assert.assertEquals(1570,testValue);
    }
    @Test
    public void gettingEarliestPriceSpecificItemThatCantBeBought() {
        Item testItem = ItemMap.get("Combinations II");
        int testValue = testItem.DetermineEarliestPrice();
        Assert.assertTrue(testValue>0);
        Assert.assertEquals(2780,testValue);
    }
    Item testItem = ItemMap.get("Halberd");
    int testValue;
    @Test
    public void gettingEarliestPriceItemCheaperWithManyCombinations() {
        testValue= testItem.DetermineEarliestPrice();
        Assert.assertTrue(testValue>0);
        Assert.assertEquals(1240,testValue);

    }
    @Test
    public void printingTestCheapest(){
        testItem.printCheapestRecipe();

    }
    @Test
    public void printingTestEarliest(){
        Item testItem = ItemMap.get("Ashura");
        testItem.printEarliestItemRecipe();
    }
}

