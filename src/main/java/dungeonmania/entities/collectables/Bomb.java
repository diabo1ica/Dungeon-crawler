package dungeonmania.entities.collectables;

import dungeonmania.util.Position;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Switch;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;

import dungeonmania.entities.ExplosiveItem;

public class Bomb extends ExplosiveItem implements InventoryItem {
    public static final int DEFAULT_RADIUS = 1;

    public Bomb(Position position, int radius) {
        super(position, radius);
        setState(State.SPAWNED);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    public void onPutDown(GameMap map, Position p) {
        translate(Position.calculatePositionBetween(getPosition(), p));
        map.addEntity(this);
        setState(State.PLACED);
        List<Position> adjPosList = getPosition().getCardinallyAdjacentPositions();
        adjPosList.stream().forEach(node -> {
            List<Entity> entities = map.getEntities(node).stream().filter(e -> (e instanceof Switch))
                    .collect(Collectors.toList());
            entities.stream().map(Switch.class::cast).forEach(s -> s.subscribe(this, map));
            entities.stream().map(Switch.class::cast).forEach(s -> this.subscribe(s));
        });
    }
}
