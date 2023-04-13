// package dungeonmania.entities.inventory.recipe;

// public class SceptreRecipe implements Recipe {
//     private static final int WOOD = 1;
//     private static final int ARROWS = 2;
//     private static final int KEY = 1;
//     private static final int TREASURE = 1;
//     private static final int SUNSTONE = 1;

//     public boolean checkIngredients(List<InventoryItem> items) {
//         if (getItemsInInventory(Wood.class, items).size() >= WOOD
//                 && getItemsInInventory(Arrow.class, items).size() >= ARROWS) {
//             return true;
//         }
//         return false;
//     }

//     public List<InventoryItem> removeIngredients(List<InventoryItem> items) {
//         List<Wood> woods = getItemsInInventory(Wood.class, items);
//         List<Arrow> arrows = getItemsInInventory(Arrow.class, items);
//         items.remove(woods.get(0));
//         items.remove(arrows.get(0));
//         items.remove(arrows.get(1));
//         items.remove(arrows.get(2));
//         return items;
//     }

//     public InventoryItem craftItem(EntityFactory factory) {
//         return factory.buildSceptre();
//     }
// }
