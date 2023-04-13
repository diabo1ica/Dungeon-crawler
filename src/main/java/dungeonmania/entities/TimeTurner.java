package dungeonmania.entities;

import dungeonmania.util.Position;
import dungeonmania.map.GameMap;

/*
 * If the player has collected a time turner then two rewind buttons will appear
 * on the frontend. When clicked, these buttons move the state of the game back
 * one tick and 5 ticks respectively and "transport" the current player back to
 * those game states in a time travelling fashion.
 */

import dungeonmania.entities.inventory.InventoryItem;

public class TimeTurner extends Entity implements InventoryItem {
    public TimeTurner(Position position) {
        super(position);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }
}
