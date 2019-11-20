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
        Assert.assertFalse(item.isPurchaseable());
    }
    @Test
    public void testItemFromDataWithStore()
    {
        Item item = new Item("  Name   ~   123   ","3");
        Assert.assertEquals("Name", item.getName());
        Assert.assertEquals(123,item.getCost());
        Assert.assertTrue(item.getStores().contains("3"));
        Assert.assertTrue(item.isPurchaseable());
    }
    @Test
    public void testItemAddStoreAndCost(){
        Item item1 = new Item("  Name   ~   123   ","3");
        List<String> TestList = new ArrayList<>();
        TestList.add("1");
        TestList.add("2");
        item1.AddStoreAndPrice(TestList,"123");
        Assert.assertTrue(item1.getStores().contains("2"));
        Assert.assertEquals(123,item1.getCost());
        Assert.assertTrue(item1.isPurchaseable());
    }
    @Test
    public void testItemAddStoreAndCost2(){
        Item item1 = new Item("Name");
        List<String> TestList = new ArrayList<>();
        TestList.add("1");
        TestList.add("2");
        item1.AddStoreAndPrice(TestList,"123");
        Assert.assertTrue(item1.getStores().contains("2"));
        Assert.assertEquals(123,item1.getCost());
        Assert.assertTrue(item1.isPurchaseable());
    }
    @Test
    public void Purchaseable(){


    }


}
