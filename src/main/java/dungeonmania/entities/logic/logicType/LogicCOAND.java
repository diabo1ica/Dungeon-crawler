package dungeonmania.entities.logic.logicType;

import java.util.List;
import dungeonmania.util.Position;

public class LogicCOAND implements LogicType {
    public boolean checkCondition(List<Position> pos, int srcCount, int initCount) {
        if (pos.size() >= srcCount && initCount == 0) {
            return true;
        }
        return false;
    }
}
