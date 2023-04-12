package dungeonmania.entities;

import dungeonmania.map.GameMap;

public interface ExplosiveItem {
    public void notify(GameMap map);

    public void explode(GameMap map);
}
