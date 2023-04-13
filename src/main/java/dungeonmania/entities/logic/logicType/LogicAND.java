package dungeonmania.entities.logic.logicType;

import java.util.List;
import dungeonmania.util.Position;

public class LogicAND implements LogicType {
    public boolean checkCondition(List<Position> pos, int srcCount, int initCount) {
        if (pos.size() >= srcCount) {
            return true;
        }
        return false;
    }
}
