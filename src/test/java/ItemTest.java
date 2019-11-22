import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemTest {





    @Test
    public void testItemWithoutAStore()
    {
        Item itemEmpty = new Item("Name");
        Assert.assertEquals("Name", itemEmpty.getName());
        Assert.assertEquals(0, itemEmpty.getStores().size());
        Assert.assertEquals(0,itemEmpty.getCost());
        Assert.assertFalse(itemEmpty.isPurchasable());
        Assert.assertEquals(0, itemEmpty.getRecipeList().size());
    }
    @Test
    public void testItemFromDataWithStore()
    {
        Item itemFilled = new Item("  Name   ~   123   ","5");
        Assert.assertEquals("Name", itemFilled.getName());
        Assert.assertEquals(123,itemFilled.getCost());
        Assert.assertTrue(itemFilled.getStores().contains("5"));
        Assert.assertTrue(itemFilled.isPurchasable());
        Assert.assertEquals(0, itemFilled.getRecipeList().size());
    }
    @Test
    public void testItemAddStoreAndCost(){
        Item itemFilled = new Item("  Name   ~   123   ","5");
        List<String> TestList = new ArrayList<>();
        TestList.add("1");
        TestList.add("2");
        itemFilled.AddStoreAndPrice(TestList,123);
        Assert.assertTrue(itemFilled.getStores().contains("2"));
        Assert.assertEquals(123,itemFilled.getCost());
        Assert.assertTrue(itemFilled.isPurchasable());
    }
    @Test
    public void testItemAddStoreAndCost2(){
        Item itemEmpty = new Item("Name");
        List<String> TestList = new ArrayList<>();
        TestList.add("1");
        TestList.add("2");
        itemEmpty.AddStoreAndPrice(TestList,123);
        Assert.assertTrue(itemEmpty.getStores().contains("2"));
        Assert.assertEquals(123,itemEmpty.getCost());
        Assert.assertTrue(itemEmpty.isPurchasable());
    }
    @Test
    public void CanAddItemRecipe(){
        Item itemEmpty = new Item("Name");
        ItemRecipe IR = new ItemRecipe("Metal + Wood = Name");
        itemEmpty.AddRecipe(IR);
        Assert.assertTrue(itemEmpty.getRecipeList().size()>0);
    }

    @Test
    public void CanBeBoughtAt(){
        Item itemFilled = new Item("  Name   ~   123   ","5");
        Assert.assertFalse(itemFilled.PurchasableAtStore("1"));
        Assert.assertTrue(itemFilled.PurchasableAtStore("5"));
    }

    @Test
    public void DeterminePricePurchasable(){
        Item itemFilled = new Item("  Name   ~   123   ","5");
        Assert.assertEquals(123,itemFilled.DeterminePrice("5"));
    }

    //Test Items and ItemRecipes
    Item Metal = new Item("  Metal   ~   100   ","2");
    Item Wood  = new Item("  Wood   ~   250   ","2");
    ItemRecipe IR = new ItemRecipe("Metal + Wood = Name");
    Item Seed = new Item("  Seed   ~   200   ","1");
    Item Water = new Item("  Water   ~   150   ","1");
    ItemRecipe IRWood = new ItemRecipe("Seed + Water = Wood");
    Item Ore = new Item(" Ore   ~ 50","4");
    Item Coal = new Item("Coal ~50","1");
    ItemRecipe IRCheaper = new ItemRecipe("Ore + Coal = Name");
    Item Spark = new Item("Spark");
    ItemRecipe IRSpark = new ItemRecipe("x + z = Spark");
    Item z = new Item(" z   ~ 1000","5");
    Item x = new Item("x");
    ItemRecipe IRMetal2 = new ItemRecipe("Ore + Spark = Metal");
    @Before
    public void InitializingDummy(){
        IR.setIngredientOne(Metal);
        IR.setIngredientTwo(Wood);
        IRWood.setIngredientOne(Seed);
        IRWood.setIngredientTwo(Water);
        IRCheaper.setIngredientOne(Ore);
        IRCheaper.setIngredientTwo(Coal);
        IRSpark.setIngredientOne(x);
        IRSpark.setIngredientTwo(z);
        IRSpark.setItem(Spark);
        Spark.AddRecipe(IRSpark);
        IRMetal2.setIngredientOne(Ore);
        IRMetal2.setIngredientTwo(Spark);
        Wood.AddStoreAndPrice("3",250);
        Wood.AddStoreAndPrice("4",250);
        Wood.AddStoreAndPrice("5",250);
        Metal.AddStoreAndPrice("3",100);
        Metal.AddStoreAndPrice("4",100);
        Metal.AddStoreAndPrice("5",100);
        Seed.AddStoreAndPrice("2",200);
        Seed.AddStoreAndPrice("3",200);
        Seed.AddStoreAndPrice("4",200);
        Seed.AddStoreAndPrice("5",200);
        Water.AddStoreAndPrice("2",150);
        Water.AddStoreAndPrice("3",150);
        Water.AddStoreAndPrice("4",150);
        Water.AddStoreAndPrice("5",150);
        Coal.AddStoreAndPrice("2",50);
        Coal.AddStoreAndPrice("3",50);
        Coal.AddStoreAndPrice("4",50);
        Coal.AddStoreAndPrice("5",50);
        Ore.AddStoreAndPrice("5",50);
    }





    @Test
    public void DeterminePriceThroughRecipe(){
        Item itemEmpty = new Item("Name");
        IR.setItem(itemEmpty);
        itemEmpty.AddRecipe(IR);
        Assert.assertEquals(350,itemEmpty.DeterminePrice("5"));
    }

    @Test
    public void DeterminePriceThroughTwoRecipesWhereOneIngredientCanBePurchasedFirstStep(){
        Item itemEmpty = new Item("Name");
        Item Wood  = new Item("Wood");
        ItemRecipe IR = new ItemRecipe("Metal + Wood = Name");
        IR.setIngredientOne(Metal);
        IR.setIngredientTwo(Wood);
        IR.setItem(itemEmpty);
        itemEmpty.AddRecipe(IR);
        IRWood.setItem(Wood);
        Wood.AddRecipe(IRWood);
        Assert.assertEquals(450,itemEmpty.DeterminePrice("5"));
    }

    @Test
    public void DeterminePriceThroughTwoRecipes() {
        Item itemEmpty = new Item("Name");
        Item Metal = new Item("Metal");
        Item Wood  = new Item("Wood");
        ItemRecipe IR = new ItemRecipe("Metal + Wood = Name");
        IR.setIngredientOne(Metal);
        IR.setIngredientTwo(Wood);
        IR.setItem(itemEmpty);
        itemEmpty.AddRecipe(IR);
        ItemRecipe IRMetal = new ItemRecipe("Ore + Coal = Metal");
        IRMetal.setIngredientOne(Ore);
        IRMetal.setIngredientTwo(Coal);
        IRMetal.setItem(Metal);
        Metal.AddRecipe(IRMetal);
        IRWood.setItem(Wood);
        Wood.AddRecipe(IRWood);
        Assert.assertEquals(450,itemEmpty.DeterminePrice("5"));
    }

    @Test
    public void DetermineCheapestPrice(){
        Item itemEmpty = new Item("Name");
        IRCheaper.setItem(itemEmpty);
        itemEmpty.AddRecipe(IRCheaper);
        IR.setItem(itemEmpty);
        itemEmpty.AddRecipe(IR);
        Assert.assertEquals(100,itemEmpty.DeterminePrice("5"));
    }

    @Test
    public void DetermineEarliestPriceSimple(){
        Item item = new Item("Name");
        IRCheaper.setItem(item);
        item.AddRecipe(IRCheaper);
        IR.setItem(item);
        item.AddRecipe(IR);
        Assert.assertEquals(350,item.DetermineEarliestPrice());
    }

    @Test
    public void DetermineEarliestPriceComplex(){
        Item item = new Item("Name");
        Item Metal = new Item("Metal");
        Item Wood  = new Item("Wood");
        Item Ore = new Item(" Ore   ~ 50","4");
        Ore.AddStoreAndPrice("5",50);
        ItemRecipe IR = new ItemRecipe("Metal + Wood = Name");
        IR.setIngredientOne(Metal);
        IR.setIngredientTwo(Wood);
        IR.setItem(item);
        item.AddRecipe(IR);
        ItemRecipe IROre = new ItemRecipe("Coal + Coal = Ore");
        IROre.setIngredientOne(Coal);
        IROre.setIngredientTwo(Coal);
        IROre.setItem(Ore);
        Ore.AddRecipe(IROre);
        ItemRecipe IRMetal = new ItemRecipe("Ore + Coal = Metal");
        IRMetal.setIngredientOne(Ore);
        IRMetal.setIngredientTwo(Coal);
        IRMetal.setItem(Metal);
        Metal.AddRecipe(IRMetal);
        IRWood.setItem(Wood);
        Wood.AddRecipe(IRWood);
        Assert.assertEquals(500,item.DetermineEarliestPrice());
    }
    @Test
    public void DoesNotCirculate(){
        Item item = new Item("Name");
        Item Metal = new Item("Metal");
        ItemRecipe IR = new ItemRecipe("Metal + Wood = Name");
        IR.setIngredientOne(Metal);
        IR.setIngredientTwo(Wood);
        IR.setItem(item);
        item.AddRecipe(IR);
        ItemRecipe IRMetal = new ItemRecipe("Ore + Coal = Metal");
        IRMetal.setIngredientOne(Ore);
        IRMetal.setIngredientTwo(Coal);
        IRMetal.setItem(Metal);
        Metal.AddRecipe(IRMetal);
        IRMetal2.setItem(Metal);
        Metal.AddRecipe(IRMetal2);
        ItemRecipe IRx = new ItemRecipe("Metal + Wood = x");
        IRx.setIngredientOne(Metal);
        IRx.setIngredientTwo(Wood);
        IRx.setItem(x);
        x.AddRecipe(IRx);
        Assert.assertEquals(350,item.DetermineEarliestPrice());
    }











}
