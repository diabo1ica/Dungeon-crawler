package dungeonmania.entities;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;
import dungeonmania.entities.enemies.Spider;

public class SwitchDoor extends LogicalEntity {
    public SwitchDoor(Position position, String type) {
        super(position, type);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return (getActivationStatus() || entity instanceof Spider);
    }
}
