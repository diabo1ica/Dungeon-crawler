package dungeonmania.goals.GoalTypes;

import dungeonmania.Game;
import dungeonmania.goals.Goal;

public interface GoalType {
    public boolean typeAchieved(Game game, Goal goal);

    public String toString(Game game, Goal goal);
}
