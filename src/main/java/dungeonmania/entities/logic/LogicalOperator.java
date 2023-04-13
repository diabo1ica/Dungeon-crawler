package dungeonmania.entities.logic;

import java.util.List;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;
import dungeonmania.entities.LogicalEntity;

public interface LogicalOperator {
    public void activate(Position pos, GameMap map, List<LogicalEntity> list);

    public void deactivate(Position pos, List<LogicalEntity> list);
}
