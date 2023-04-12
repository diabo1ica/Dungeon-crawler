package dungeonmania.entities;

import java.util.List;
import java.util.ArrayList;

import dungeonmania.util.Position;
import dungeonmania.entities.logic.LogicalOperator;
import dungeonmania.entities.logic.logicType.LogicTypeFactory;
import dungeonmania.map.GameMap;
import dungeonmania.entities.logic.logicType.LogicType;

public class LogicalEntity extends Entity implements LogicalOperator, Subscribable {
    private LogicType type;
    private boolean isActivated = false;
    private List<Subscribable> subs = new ArrayList<>();
    private List<Position> initSrcPowerList = new ArrayList<>();
    private List<Position> srcPowerList = new ArrayList<>();

    public LogicalEntity(Position position, String type) {
        super(position);
        this.type = LogicTypeFactory.create(type);
    }

    public void subscribe(Subscribable s) {
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

    public boolean getActivationStatus() {
        return isActivated;
    }

    private void addPowerSource(Position pos) {
        srcPowerList.add(pos);
    }

    private void removePowerSource(Position pos) {
        srcPowerList.remove(pos);
    }

    public void activate(Position pos, GameMap map, List<LogicalEntity> list) {
        addPowerSource(pos);
        if (!list.contains(this)) list.add(this);
    }

    public void deactivate(Position pos, List<LogicalEntity> list) {
        removePowerSource(pos);
        if (list.contains(this)) list.remove(this);
    }

    public void updateActivationStatus(GameMap map) {
        isActivated = type.checkCondition(srcPowerList, subs.size(), initSrcPowerList.size());
        initSrcPowerList = srcPowerList;
    }

    public List<Position> getSrcPowerList() {
        return srcPowerList;
    }

    public List<Position> getInitSrcPowerList() {
        return initSrcPowerList;
    }

    public void updateInitSrcList() {
        initSrcPowerList = srcPowerList;
    }
}
