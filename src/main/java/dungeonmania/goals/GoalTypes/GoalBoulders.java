package dungeonmania.goals.GoalTypes;

import dungeonmania.Game;
import dungeonmania.entities.Switch;
import dungeonmania.goals.Goal;

public class GoalBoulders implements GoalType {
    public boolean typeAchieved(Game game, Goal goal) {
        return game.getMap().getEntities(Switch.class).stream().allMatch(s -> s.isActivated());
    }

    public String toString(Game game, Goal goal) {
        return ":boulders";
    }
}
