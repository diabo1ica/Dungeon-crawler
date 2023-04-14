package dungeonmania.entities.collectables;

import dungeonmania.util.Position;
import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;

import dungeonmania.entities.inventory.InventoryItem;

public class SunStone extends Entity implements InventoryItem {
    public SunStone(Position position) {
        super(position);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }
}
