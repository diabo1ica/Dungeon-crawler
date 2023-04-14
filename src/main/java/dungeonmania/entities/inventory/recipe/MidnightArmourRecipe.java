package dungeonmania.entities.inventory.recipe;

import java.util.List;
import dungeonmania.entities.inventory.*;
import dungeonmania.entities.collectables.Sword;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.EntityFactory;

public class MidnightArmourRecipe implements Recipe {
    public boolean checkIngredients(List<InventoryItem> items) {
        if (getItemsInInventory(SunStone.class, items).size() >= 1
                && getItemsInInventory(Sword.class, items).size() >= 1) {
            System.out.println("true ture nehimomo");
            return true;
        }
        return false;
    }

    public List<InventoryItem> removeIngredients(List<InventoryItem> items) {
        List<Sword> sword = getItemsInInventory(Sword.class, items);
        List<SunStone> sun_stone = getItemsInInventory(SunStone.class, items);
        items.remove(sword.get(0));
        items.remove(sun_stone.get(0));
        return items;
    }

    public InventoryItem craftItem(EntityFactory factory) {
        return factory.buildMidnightArmour();
    }
}
