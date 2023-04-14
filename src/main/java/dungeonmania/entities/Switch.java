package dungeonmania.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.logic.LogicalActivator;
import dungeonmania.entities.logic.LogicalOperator;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Switch extends Entity implements Subscribable, LogicalOperator,
OverlapBehaviour, MovedAwayBehaviour, LogicalActivator {
    private boolean activated;
    private List<Subscribable> subs = new ArrayList<>();
    private List<LogicalEntity> logicTargetList = new ArrayList<>();

    public Switch(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
    }

    public void subscribe(Subscribable b) {
        subs.add(b);
    }

    public List<Subscribable> getSubs() {
        return subs;
    }

    // Subscribe on put down bomb
    public void subscribe(Subscribable bomb, GameMap map) {
        subs.add(bomb);
        if (activated) {
            getBombsFromSubs().forEach(b -> b.notify(map));
        }
    }

    public void unsubscribe(Subscribable s) {
        subs.remove(s);
    }

    public void unsubscribeAll() {
        subs = new ArrayList<>();
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = true;
            getBombsFromSubs().forEach(b -> b.notify(map));
            this.activate(getPosition(), map, logicTargetList);
        }
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = false;
            this.deactivate(getPosition(), logicTargetList);
        }
    }

    private List<ExplosiveItem> getBombsFromSubs() {
        return subs.stream().filter(ExplosiveItem.class::isInstance)
        .map(ExplosiveItem.class::cast)
        .collect(Collectors.toList());
    }

    private List<LogicalOperator> getLogicOperatorsFromSubs() {
        return subs.stream().filter(LogicalOperator.class::isInstance)
        .map(LogicalOperator.class::cast)
        .collect(Collectors.toList());
    }

    public void activate(Position position, GameMap map, List<LogicalEntity> list) {
        if (!activated)
            return;
        // Connect power to all connected logic operators
        Iterator<LogicalOperator> it = getLogicOperatorsFromSubs().iterator();
        while (it.hasNext()) {
            it.next().activate(position, map, list);
        }
        // Activate connected logic entities
        Iterator<LogicalEntity> it2 = logicTargetList.iterator();
        while (it2.hasNext()) {
            it2.next().updateActivationStatus(map);
        }
    }

    public void deactivate(Position position, List<LogicalEntity> list) {
        getLogicOperatorsFromSubs().forEach(e -> e.deactivate(position, list));
    }

    public boolean isActivated() {
        return activated;
    }
}
