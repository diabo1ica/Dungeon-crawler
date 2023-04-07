<<<<<<< HEAD
package dungeonmania.entities;

import dungeonmania.map.GameMap;

public class handleOverlapInventory implements OverlapBehaviour {
    @Override
    public void onOverlap(GameMap map, Entity entity, Entity item) {
        if (entity instanceof Player) {
            if (!((Player) entity).pickUp(item))
                return;
            map.destroyEntity(item);
        }
    }
}
=======
// package dungeonmania.entities;

// public class handleOverlapInventory {
//     @Override
//     public void onOverlap() {

//     }
// }
>>>>>>> 29d9095c6e9e035b36c3426709734fae6d3d9fda
