package dungeonmania.entities.collectables;

import dungeonmania.entities.Entity;
<<<<<<< HEAD
=======

>>>>>>> 29d9095c6e9e035b36c3426709734fae6d3d9fda
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Arrow extends Entity implements InventoryItem {
    public Arrow(Position position) {
        super(position);
    } // hehes

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }
}
