package dungeonmania.entities.logic.logicType;

public class LogicTypeFactory {
    public static LogicType create(String type) {
        switch (type) {
            case "and":
                return new LogicAND();
            case "or":
                return new LogicOR();
            case "xor":
                return new LogicXOR();
            case "co_and":
                return new LogicCOAND();
            default:
                return null;
        }
    }
}
