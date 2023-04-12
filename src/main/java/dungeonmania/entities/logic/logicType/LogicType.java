package dungeonmania.entities.logic.logicType;

import java.util.List;
import dungeonmania.util.Position;

public interface LogicType {
    public boolean checkCondition(List<Position> position, int subCount, int initCount);
}
