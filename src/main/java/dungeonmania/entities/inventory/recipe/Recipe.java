package dungeonmania.entities.inventory.recipe;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.inventory.*;

public interface Recipe {
    public boolean checkIngredients(List<InventoryItem> items);

    public List<InventoryItem> removeIngredients(List<InventoryItem> items);

    public InventoryItem craftItem(EntityFactory factory);

    public default <T> List<T> getItemsInInventory(Class<T> clz ,List<InventoryItem> items) {
        return items.stream().filter(clz::isInstance).map(clz::cast).collect(Collectors.toList());
    }
}
