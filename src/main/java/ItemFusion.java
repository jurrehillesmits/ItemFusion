import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemFusion {
    private HashMap<String,Item> ItemMap = new HashMap<>();
    private HashMap<String, List<ItemRecipe>> ItemRecipeMap = new HashMap<>();
    //We build a list(Map with item name as key for the item) of all the items referenced in the file and for all of the recipes we do the same, using the created items name as key
    ItemFusion(String filename){
        BufferedReader reader;
        try{
            reader = new BufferedReader (new FileReader(filename));
            String line = reader.readLine();
            int StoreNr = 1;
            while (line !=  null){
                if(line.contains("*****")){
                    StoreNr+=1;
                }
                if(line.contains("~")&&!line.contains("Cost")){
                    CreateItemFromData(line, StoreNr);
                }
                else if(line.contains("+")&&line.contains("=")){
                    ItemRecipe IR = new ItemRecipe(line);
                    CreateAndAddItemRecipe(IR);
                    CreateItemIfRecipeMakesIt(IR);
                }
                line = reader.readLine();
            }
            reader.close();
            //This removes all recipes that contain an item that can't be bought nor made
            RemoveRecipesThatCantBeMadeOrBought();
            //Adding the recipes to the items
            AddRecipeToItem();
            AddItemsToRecipe();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void CreateItemIfRecipeMakesIt(ItemRecipe IR) {
        if(!ItemMap.containsKey(IR.getItemName())){
            Item item = new Item(IR.getItemName());
            ItemMap.put(item.getName(),item);
        }
    }
    private void CreateAndAddItemRecipe(ItemRecipe IR) {
        List<ItemRecipe> IRlist;
        if(!ItemRecipeMap.containsKey(IR.getItemName())){
            IRlist = new ArrayList<>();
            IRlist.add(IR);
            ItemRecipeMap.put(IR.getItemName(),IRlist);
        }
        else if(ItemRecipeMap.containsKey(IR.getItemName())){
            IRlist =ItemRecipeMap.get(IR.getItemName());
            IRlist.add(IR);
            ItemRecipeMap.put(IR.getItemName(),IRlist);
        }
    }
    private void CreateItemFromData(String line, int storeNr) {
        Item item = new Item(line,Integer.toString(storeNr));
        if(!ItemMap.containsKey(item.getName())){
            ItemMap.put(item.getName(),item);
        }
        else if(ItemMap.containsKey(item.getName())){
            //We get the StoreAndPrice list from the duplicate item, overwrite the duplicate with the one in the hashmap and add the Stores and Prices to it.
            List<String> Stores = item.getStores();
            int Price = item.getCost();
            item = ItemMap.get(item.getName());
            item.AddStoreAndPrice(Stores,Price);
            ItemMap.put(item.getName(),item);
        }
    }
    private void RemoveRecipesThatCantBeMadeOrBought() {
        for(List<ItemRecipe> IRL:ItemRecipeMap.values()){
            for(int i = 0;i<IRL.size();i++){
                ItemRecipe IR = IRL.get(i);
                if(!ItemRecipeMap.containsKey(IR.getIngredientOneName())&&!ItemMap.containsKey(IR.getIngredientOneName())){
                    IRL.remove(IR);
                    i -=1;
                }
                else if(!ItemRecipeMap.containsKey(IR.getIngredientTwoName())&&!ItemMap.containsKey(IR.getIngredientTwoName())){
                    IRL.remove(IR);
                    i -=1;
                }
            }
        }
    }
    private void AddRecipeToItem() {
        for(Item item:ItemMap.values()){
            if(ItemRecipeMap.containsKey(item.getName())){
                for(ItemRecipe IR:ItemRecipeMap.get(item.getName()))
                item.AddRecipe(IR);
            }
        }
    }
    private void AddItemsToRecipe(){
        for(List<ItemRecipe> IRList:ItemRecipeMap.values()){
            for(ItemRecipe IR:IRList){
                IR.setItem(ItemMap.get(IR.getItemName()));
                IR.setIngredientOne(ItemMap.get(IR.getIngredientOneName()));
                IR.setIngredientTwo(ItemMap.get(IR.getIngredientTwoName()));
            }
        }
    }
    HashMap<String, Item> getItemMap() {
        return ItemMap;
    }
    HashMap<String, List<ItemRecipe>> getItemRecipeMap() {
        return ItemRecipeMap;
    }

    Item getItem(String name){
        return ItemMap.get(name);
    }
}
