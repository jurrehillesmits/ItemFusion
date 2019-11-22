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
            }
        }
    }
}

