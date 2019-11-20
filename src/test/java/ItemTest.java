import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemTest {
    @Test
    public void testItemWithoutAStore()
    {
        Item item = new Item("Name");
        Assert.assertEquals("Name", item.getName());
        Assert.assertEquals(0, item.getStores().size());
        Assert.assertEquals(0,item.getCost());
        Assert.assertFalse(item.isPurchasable());
        Assert.assertEquals(0, item.getRecipeList().size());
    }
    @Test
    public void testItemFromDataWithStore()
    {
        Item item = new Item("  Name   ~   123   ","3");
        Assert.assertEquals("Name", item.getName());
        Assert.assertEquals(123,item.getCost());
        Assert.assertTrue(item.getStores().contains("3"));
        Assert.assertTrue(item.isPurchasable());
        Assert.assertEquals(0, item.getRecipeList().size());
    }
    @Test
    public void testItemAddStoreAndCost(){
        Item item = new Item("  Name   ~   123   ","3");
        List<String> TestList = new ArrayList<>();
        TestList.add("1");
        TestList.add("2");
        item.AddStoreAndPrice(TestList,123);
        Assert.assertTrue(item.getStores().contains("2"));
        Assert.assertEquals(123,item.getCost());
        Assert.assertTrue(item.isPurchasable());
    }
    @Test
    public void testItemAddStoreAndCost2(){
        Item item = new Item("Name");
        List<String> TestList = new ArrayList<>();
        TestList.add("1");
        TestList.add("2");
        item.AddStoreAndPrice(TestList,123);
        Assert.assertTrue(item.getStores().contains("2"));
        Assert.assertEquals(123,item.getCost());
        Assert.assertTrue(item.isPurchasable());
    }
    @Test
    public void CanAddItemRecipe(){
        Item item = new Item("Name");
        ItemRecipe IR = new ItemRecipe("Metal + Wood = Name");
        item.AddRecipe(IR);
        Assert.assertTrue(item.getRecipeList().size()>0);
    }

    @Test
    public void CanBeBoughtAt(){
        Item item = new Item("  Name   ~   123   ","3");
        Assert.assertFalse(item.CanBeBoughtAt("1"));
        Assert.assertTrue(item.CanBeBoughtAt("3"));
    }

}
