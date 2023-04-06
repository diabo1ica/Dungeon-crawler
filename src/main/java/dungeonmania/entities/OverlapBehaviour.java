package dungeonmania.entities;

import dungeonmania.map.GameMap;

public interface OverlapBehaviour {
    public void onOverlap(GameMap map, Entity entity, Entity item);
}

/*abstract

collectables:
bomb: inventoryitem and explosiveitem
the rest: inventoryitem

*/
