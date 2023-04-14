package dungeonmania.entities.inventory.recipe;

import java.util.List;
import dungeonmania.entities.inventory.*;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.Arrow;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Treasure;

public class SceptreRecipe implements Recipe {
    public boolean checkIngredients(List<InventoryItem> items) {

        // case if there is no sun_stone
        // definitelty cannot build Sceptre
        if (getItemsInInventory(SunStone.class, items).size() == 0) {
            return false;
        }

        // case if there is 1 sun_stone
        if (getItemsInInventory(SunStone.class, items).size() == 1) {
            // then (1 WOOD or 2 Arrows) && (1 Key || 1 Treasure)
            if ((getItemsInInventory(Wood.class, items).size() == 1
                    || getItemsInInventory(Arrow.class, items).size() == 2)
                    && (getItemsInInventory(Key.class, items).size() == 1
                            || getItemsInInventory(Treasure.class, items).size() == 1)) {
                return true;
            }
        } else if (getItemsInInventory(SunStone.class, items).size() == 2) {
            // if 2 sun stone
            // then (1 WOOD or 2 Arrows) || (1 Key || 1 Treasure)
            if ((getItemsInInventory(Wood.class, items).size() == 1
                    || getItemsInInventory(Arrow.class, items).size() == 2)
                    || (getItemsInInventory(Key.class, items).size() == 1
                            || getItemsInInventory(Treasure.class, items).size() == 1)) {
                return true;
            }
        } else if (getItemsInInventory(SunStone.class, items).size() >= 3) {
            // if 3 or more sunstone, confirm can build sceptre
            return true;
        }
        return false;
    }

    public List<InventoryItem> removeIngredients(List<InventoryItem> items) {

        List<Wood> woods = getItemsInInventory(Wood.class, items);
        List<Arrow> arrows = getItemsInInventory(Arrow.class, items);
        List<Treasure> treasure = getItemsInInventory(Treasure.class, items);
        List<Key> keys = getItemsInInventory(Key.class, items);
        List<SunStone> sunStone = getItemsInInventory(SunStone.class, items);

        if (sunStone.size() == 1) {
            // (1 WOOD or 2 ARROWS) && (1 KEY or 1 TREASURE)
            if (woods.size() == 1) {
                items.remove(woods.get(0));
            } else {
                items.remove(arrows.get(0));
                items.remove(arrows.get(1));
            }

            if (keys.size() == 1) {
                items.remove(keys.get(0));
            } else {
                items.remove(treasure.get(0));
            }
            items.remove(sunStone.get(0));
        } else if (sunStone.size() == 2) {
            // (1 WOOD or 2 ARROWS) || (1 KEY or 1 TREASURE)
            System.out.println("nehimomo");
            if (woods.size() == 1 || arrows.size() == 2) {
                if (woods.size() == 1) {
                    items.remove(woods.get(0));
                } else {
                    items.remove(arrows.get(0));
                    items.remove(arrows.get(1));
                }
            } else {
                if (keys.size() == 1) {
                    items.remove(keys.get(0));
                } else {
                    items.remove(treasure.get(0));
                }
            }
            items.remove(sunStone.get(0));
        } else if (sunStone.size() > 2) {
            items.remove(sunStone.get(0));
        }
        return items;
    }

    public InventoryItem craftItem(EntityFactory factory) {
        return factory.buildSceptre();
    }
}
