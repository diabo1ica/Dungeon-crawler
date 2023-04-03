package dungeonmania.entities.enemies.MovementStrategy;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

import dungeonmania.entities.enemies.ZombieToast;

public class ZombieToastMovement implements Movement {
    public void move(GameMap map, Entity entity) {
        ZombieToast ent = (ZombieToast) entity;
        Position nextPos;
        if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            nextPos = runAway(map, entity);
        } else {
            List<Position> pos = ent.getPosition().getCardinallyAdjacentPositions();
            pos = pos.stream().filter(p -> map.canMoveTo(ent, p)).collect(Collectors.toList());
            if (pos.size() == 0) {
                nextPos = ent.getPosition();
            } else {
                nextPos = pos.get(ent.getRandGen().nextInt(pos.size()));
            }
        }
        map.moveTo(ent, nextPos);
    }
}
