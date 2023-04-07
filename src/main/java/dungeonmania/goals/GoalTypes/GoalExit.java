package dungeonmania.goals.GoalTypes;

import java.util.List;
import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Exit;
import dungeonmania.entities.Player;
import dungeonmania.util.Position;
import dungeonmania.goals.Goal;

public class GoalExit implements GoalType {
    public boolean typeAchieved(Game game, Goal goal) {
        Player character = game.getPlayer();
        Position pos = character.getPosition();
        List<Exit> es = game.getMap().getEntities(Exit.class);
        if (es == null || es.size() == 0)
        return false;
        return es.stream().map(Entity::getPosition).anyMatch(pos::equals);
    }

    @Override
    public String toString(Game game, Goal goal) {
        return ":exit";
    }
}
