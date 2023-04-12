package dungeonmania.entities.collectables;

import dungeonmania.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Switch;
import dungeonmania.entities.Player;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.entities.logic.LogicalActivator;
import dungeonmania.entities.logic.logicType.LogicTypeFactory;
import dungeonmania.entities.logic.logicType.LogicType;
import dungeonmania.map.GameMap;

import dungeonmania.entities.ExplosiveItem;
import dungeonmania.entities.LogicalEntity;

public class LogicalBomb extends LogicalEntity implements InventoryItem, ExplosiveItem {
    public enum State {
        SPAWNED, INVENTORY, PLACED
    }

    public static final int DEFAULT_RADIUS = 1;
    private int radius;
    private LogicType type;
    private State state;
    private List<Position> srcPowerList = new ArrayList<>();

    public LogicalBomb(Position position, int radius, String type) {
        super(position, type);
        this.radius = radius;
        setState(State.SPAWNED);
        this.type = LogicTypeFactory.create(type);
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
            List<Entity> entities = map.getEntities(node).stream().filter(e -> (e instanceof LogicalActivator))
                    .collect(Collectors.toList());
            entities.stream().map(Switch.class::cast).forEach(s -> s.subscribe(this, map));
            entities.stream().map(Switch.class::cast).forEach(s -> this.subscribe(s));
        });
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

    public void activate(Position pos, GameMap map) {
        srcPowerList.add(pos);
    }

    public void deactivate(Position pos) {
        srcPowerList.remove(pos);
    }

    @Override
    public void updateActivationStatus(GameMap map) {
        if (type.checkCondition(getSrcPowerList(), getSubs().size(), getInitSrcPowerList().size())) {
            this.notify(map);
        }
        updateInitSrcList();
    }

    public State getState() {
        return state;
    }

    public void setState(State newState) {
        state = newState;
    }
}

