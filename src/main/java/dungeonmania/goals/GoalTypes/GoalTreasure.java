package dungeonmania.goals.GoalTypes;

import dungeonmania.Game;
import dungeonmania.goals.Goal;

public class GoalTreasure implements GoalType {
    public boolean typeAchieved(Game game, Goal goal) {
        return game.getCollectedTreasureCount() >= goal.getTarget();
    }

    @Override
    public String toString(Game game, Goal goal) {
        return ":treasure";
    }
}
