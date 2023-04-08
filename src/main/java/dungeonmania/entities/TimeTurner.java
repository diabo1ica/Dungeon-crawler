package dungeonmania.entities;

/*
 * If the player has collected a time turner then two rewind buttons will appear
 * on the frontend. When clicked, these buttons move the state of the game back
 * one tick and 5 ticks respectively and "transport" the current player back to
 * those game states in a time travelling fashion.
 */

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

import dungeonmania.entities.inventory.InventoryItem;

public class TimeTurner extends Entity implements OverlapBehaviour, InventoryItem {

    // new attributes

    // constructor
    // it has 2 buttons
    // button 1:  
    public TimeTurner(Position position) {
        super(position);

    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Player) {
            //
        }
    }
}

// find out how to go back tick
// need to create state (find out how state is being stored in Game)
