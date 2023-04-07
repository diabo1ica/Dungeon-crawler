package dungeonmania.entities.inventory;

import java.util.Arrays;
import java.util.List;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.inventory.recipe.*;;

public class Craft {
    private Recipe recipe;
    private List<String> recipeList = Arrays.asList("bow", "shield");

    public boolean validInventory(List<InventoryItem> items, String item) {
        switch (item) {
            case ("bow"):
                recipe = new BowRecipe();
                break;
            default:
                recipe = new ShieldRecipe();
        }
        return recipe.checkIngredients(items);
    }

    public List<InventoryItem> removeIngredients(List<InventoryItem> items) {
        return recipe.removeIngredients(items);
    }

    public InventoryItem craftItem(EntityFactory factory) {
        return recipe.craftItem(factory);
    }

    public List<String> getRecipeList() {
        return recipeList;
    }
}
