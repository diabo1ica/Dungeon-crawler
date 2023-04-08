package dungeonmania.goals.GoalTypes;

public class GoalTypeFactory {
    public static GoalType createGoalType(String type) {
        switch (type) {
            case "AND":
                return new GoalAnd();
            case "OR":
                return new GoalOr();
            case "exit":
                return new GoalExit();
            case "boulders":
                return new GoalBoulders();
            case "treasure":
                return new GoalTreasure();
            case "enemies":
                return new GoalEnemy();
            default:
                return null;
        }
    }
}
