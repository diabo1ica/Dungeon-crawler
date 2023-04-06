package dungeonmania.goals.GoalTypes;

import dungeonmania.Game;
import dungeonmania.goals.Goal;

public class GoalOr implements GoalType {
    public boolean typeAchieved(Game game, Goal goal) {
        Goal goal1 = goal.getGoalOne();
        Goal goal2 = goal.getGoalTwo();
        return goal1.achieved(game) || goal2.achieved(game);
    }

    public String toString(Game game, Goal goal) {
        if (typeAchieved(game, goal)) {
            return "";
        }
        Goal goal1 = goal.getGoalOne();
        Goal goal2 = goal.getGoalTwo();
        return "(" + goal1.toString(game) + " OR " + goal2.toString(game) + ")";
    }
}
