package dungeonmania.entities.inventory.recipe;

import java.util.List;
import dungeonmania.entities.inventory.*;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Arrow;

public class BowRecipe implements Recipe {
    private static final int WOOD = 1;
    private static final int ARROWS = 3;

    public boolean checkIngredients(List<InventoryItem> items) {
        if (getItemsInInventory(Wood.class, items).size() >= WOOD
        && getItemsInInventory(Arrow.class, items).size() >= ARROWS) {
            return true;
        }
        return false;
    }

    public List<InventoryItem> removeIngredients(List<InventoryItem> items) {
        List<Wood> woods = getItemsInInventory(Wood.class, items);
        List<Arrow> arrows = getItemsInInventory(Arrow.class, items);
        items.remove(woods.get(0));
        items.remove(arrows.get(0));
        items.remove(arrows.get(1));
        items.remove(arrows.get(2));
        return items;
    }

    public InventoryItem craftItem(EntityFactory factory) {
        return factory.buildBow();
    }
}
