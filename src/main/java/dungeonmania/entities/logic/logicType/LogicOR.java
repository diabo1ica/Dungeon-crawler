package dungeonmania.entities.logic.logicType;

import java.util.List;
import dungeonmania.util.Position;

public class LogicOR implements LogicType {
    public boolean checkCondition(List<Position> pos, int srcCount, int initCount) {
        if (pos.size() >= 1) {
            return true;
        }
        return false;
    }
}
