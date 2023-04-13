package dungeonmania.entities;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import java.util.ArrayList;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;
import dungeonmania.entities.logic.LogicalActivator;
import dungeonmania.entities.logic.LogicalOperator;

public class Wire extends Entity implements LogicalOperator, Subscribable, DestroyedBehaviour, LogicalActivator {
    private List<Subscribable> subs = new ArrayList<>();
    private List<Position> srcPowerList = new ArrayList<>();
    private boolean isActivated = false;

    public Wire(Position position) {
        super(position);
    }

    public void subscribe(Subscribable s) {
        if (subs.contains(s)) return;
        this.subs.add(s);
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

    public void activate(Position position, GameMap map, List<LogicalEntity> list) {
        if (isActivated) return;
        isActivated = true;
        srcPowerList.add(position);
        Iterator<LogicalOperator> iterator = toLogicalEntity().iterator();
        while (iterator.hasNext()) {
            iterator.next().activate(getPosition(), map, list);
        }
    }

    public void deactivate(Position position, List<LogicalEntity> list) {
        if (!isActivated) return;
        srcPowerList.remove(position);
        if (srcPowerList.isEmpty()) {
            isActivated = false;
            subs.stream().map(LogicalOperator.class::cast).forEach(s -> s.deactivate(getPosition(), list));
        }
    }

    public void onDestroy(GameMap map) {
        subs.forEach(s -> {
            s.unsubscribe(this);
            if (!srcPowerList.contains(s.getPosition())) {
                LogicalOperator e = (LogicalOperator) s;
                e.deactivate(getPosition(), new ArrayList<>());
            }
        });
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    private List<LogicalOperator> toLogicalEntity() {
        return subs.stream().map(LogicalOperator.class::cast).collect(Collectors.toList());
    }
}
