package dungeonmania.goals;

import java.io.Serializable;

import dungeonmania.Game;
import dungeonmania.goals.GoalTypes.*;

public class Goal implements Serializable {
    private GoalType gType;
    private int target;
    private Goal goal1;
    private Goal goal2;

    public Goal(String type) {
        this.gType = GoalTypeFactory.createGoalType(type);
    }

    public Goal(String type, int target) {
        this.gType = GoalTypeFactory.createGoalType(type);
        this.target = target;
    }

    public Goal(String type, Goal goal1, Goal goal2) {
        this.gType = GoalTypeFactory.createGoalType(type);
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    public int getTarget() {
        return target;
    }

    public Goal getGoalOne() {
        return goal1;
    }

    public Goal getGoalTwo() {
        return goal2;
    }
    /**
     * @return true if the goal has been achieved, false otherwise
     */
    public boolean achieved(Game game) {
        if (game.getPlayer() == null)
            return false;
        return gType.typeAchieved(game, this);
    }

    public String toString(Game game) {
        if (this.achieved(game)) {
            return "";
        }
        return gType.toString(game, this);
    }

}
