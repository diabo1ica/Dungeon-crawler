package dungeonmania.entities.enemies.MovementStrategy;

import java.util.List;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Boulder;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

import dungeonmania.entities.enemies.Spider;

public class SpiderMovement implements Movement {
    public void move(GameMap map, Entity entity) {
        Spider ent = (Spider) entity;
        List<Position> movementTrajectory = ent.getMovementTrajectory();
        int nextPositionElement = ent.getNextPositionElement();
        Position nextPos = movementTrajectory.get(nextPositionElement);
        List<Entity> entities = map.getEntities(nextPos);
        if (entities != null && entities.size() > 0 && entities.stream().anyMatch(e -> e instanceof Boulder)) {
            ent.setForward();
            ent.updateNextPosition();
            ent.updateNextPosition();
        }
        nextPositionElement = ent.getNextPositionElement();
        nextPos = movementTrajectory.get(nextPositionElement);
        entities = map.getEntities(nextPos);
        if (entities == null || entities.size() == 0 || entities.stream().allMatch(e -> e.canMoveOnto(map, ent))) {
            map.moveTo(ent, nextPos);
            ent.updateNextPosition();
        }
    }
}
