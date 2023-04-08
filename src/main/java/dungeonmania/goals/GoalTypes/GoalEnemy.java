package dungeonmania.goals.GoalTypes;

import dungeonmania.Game;
import dungeonmania.goals.Goal;

public class GoalEnemy implements GoalType {
    public boolean typeAchieved(Game game, Goal goal) {
        return (game.getPlayersKillCount() >= goal.getTarget()) && game.getSpawnerCount() == 0;
    }

    public String toString(Game game, Goal goal) {
        return ":enemies";
    }
}
