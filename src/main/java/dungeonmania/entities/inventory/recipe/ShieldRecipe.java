package dungeonmania.entities.inventory.recipe;

import java.util.List;
import dungeonmania.entities.inventory.*;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Treasure;

public class ShieldRecipe implements Recipe {
    private static final int WOOD = 2;
    private static final int TREASURE = 1;
    private static final int KEY = 1;
    private static final int SUNSTONE = 1;

    public boolean checkIngredients(List<InventoryItem> items) {
        if (getItemsInInventory(Wood.class, items).size() >= WOOD
                && (getItemsInInventory(Treasure.class, items).size() >= TREASURE
                        || getItemsInInventory(Key.class, items).size() >= KEY
                        || getItemsInInventory(SunStone.class, items).size() >= SUNSTONE)) {
            return true;
        }
        return false;
    }

    public List<InventoryItem> removeIngredients(List<InventoryItem> items) {

        List<Wood> woods = getItemsInInventory(Wood.class, items);
        List<Treasure> treasure = getItemsInInventory(Treasure.class, items);
        List<Key> key = getItemsInInventory(Key.class, items);
        List<SunStone> sunstone = getItemsInInventory(SunStone.class, items);

        items.remove(woods.get(0));
        items.remove(woods.get(1));

        if (sunstone.size() >= 1) {
            return items;
        } else if (treasure.size() >= 1) {
            items.remove(treasure.get(0));
        } else {
            items.remove(key.get(0));
        }
        return items;
    }

    public InventoryItem craftItem(EntityFactory factory) {
        return factory.buildShield();
    }
}
