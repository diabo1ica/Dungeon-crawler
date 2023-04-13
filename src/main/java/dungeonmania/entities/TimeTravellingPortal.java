package dungeonmania.entities;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class TimeTravellingPortal extends Entity implements OverlapBehaviour {

    // constructor
    public TimeTravellingPortal(Position position) {
        super(position);
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        // if (entity instanceof Player) {
        //     // player's position remain the same
        //     // the dungeon state rewind(30)
        // }
    }

}

// question:

/*abstract
    1. If timeTravellingPortal, then the only thignt hat remains is the Player's position.
    The player's inventory will also rewind(30)?
    ans: the player's inventory remain the same
    2. 'The player's inventory persists across time travelling.
    This means that if a player picks up a sword then travels through a time portal,
    the sword remains in their inventory as well as being back on the map available to pick up.'
    Does this apply to all inventory item that the player has picked throughout the game?
    or only the most recent picked up item?
    3.
*/
