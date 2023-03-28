package dungeonmania.entities.enemies.MovementStrategy;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;
import dungeonmania.entities.Entity;
import dungeonmania.util.Direction;

public interface Movement {
    public void move(GameMap map, Entity entity);

    // enemies run away from INVINCIBLE player (Mercenary, ZombieToast)
    public default Position runAway(GameMap map, Entity entity) {

        Position position = entity.getPosition();

        Position plrDiff = Position.calculatePositionBetween(map.getPlayer().getPosition(), position);

        Position moveX = (plrDiff.getX() >= 0) ? Position.translateBy(position, Direction.RIGHT)
                : Position.translateBy(position, Direction.LEFT);
        Position moveY = (plrDiff.getY() >= 0) ? Position.translateBy(position, Direction.UP)
                : Position.translateBy(position, Direction.DOWN);
        Position offset = position;
        if (plrDiff.getY() == 0 && map.canMoveTo(entity, moveX))
            offset = moveX;
        else if (plrDiff.getX() == 0 && map.canMoveTo(entity, moveY))
            offset = moveY;
        else if (Math.abs(plrDiff.getX()) >= Math.abs(plrDiff.getY())) {
            if (map.canMoveTo(entity, moveX))
                offset = moveX;
            else if (map.canMoveTo(entity, moveY))
                offset = moveY;
            else
                offset = position;
        } else {
            if (map.canMoveTo(entity, moveY))
                offset = moveY;
            else if (map.canMoveTo(entity, moveX))
                offset = moveX;
            else
                offset = position;
        }
        return offset;
    }
}
