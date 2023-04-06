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
