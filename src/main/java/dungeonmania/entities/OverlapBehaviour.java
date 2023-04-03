package dungeonmania.entities;

import dungeonmania.map.GameMap;

public interface OverlapBehaviour {
    public void onOverlap(GameMap map, Entity entity);
}
