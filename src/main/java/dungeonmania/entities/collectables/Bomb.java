package dungeonmania.entities.collectables;

import dungeonmania.util.Position;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.Switch;
import dungeonmania.entities.Subscribable;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;

import dungeonmania.entities.ExplosiveItem;

public class Bomb extends Entity implements InventoryItem, Subscribable, ExplosiveItem {
    public enum State {
        SPAWNED, INVENTORY, PLACED
    }

    public static final int DEFAULT_RADIUS = 1;
    private List<Subscribable> subs = new ArrayList<>();
    private State state;
    private int radius;

    public Bomb(Position position, int radius) {
        super(position);
        this.radius = radius;
        setState(State.SPAWNED);
    }

    public void subscribe(Subscribable s) {
        subs.add(s);
    }

    public List<Subscribable> getSubs() {
        return subs;
    }

    public void unsubscribe(Subscribable s) {
        subs.remove(s);
    }

    public void unsubscribeAll() {
        subs = new ArrayList<>();
    }

    public void notify(GameMap map) {
        explode(map);
    }

    public void explode(GameMap map) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                List<Entity> entities = map.getEntities(new Position(i, j));
                entities = entities.stream().filter(e -> !(e instanceof Player)).collect(Collectors.toList());
                for (Entity e : entities)
                    map.destroyEntity(e);
            }
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State newState) {
        state = newState;
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
