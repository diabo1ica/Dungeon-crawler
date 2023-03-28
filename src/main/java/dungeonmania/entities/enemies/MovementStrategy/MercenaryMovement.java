package dungeonmania.entities.enemies.MovementStrategy;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.InvisibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

import dungeonmania.entities.enemies.Mercenary;

public class MercenaryMovement implements Movement {
    public void move(GameMap map, Entity entity) {
        Mercenary ent = (Mercenary) entity;
        Player player = map.getPlayer();
        Position nextPos;
        if (ent.isAllied()) {
            nextPos = ent.isAdjacentToPlayer() ? player.getPreviousDistinctPosition()
                    : map.dijkstraPathFind(ent.getPosition(), player.getPosition(), ent);
            if (!ent.isAdjacentToPlayer() && Position.isAdjacent(player.getPosition(), nextPos))
                ent.setIsAdjacentToPlayer(true);
        } else if (map.getPlayer().getEffectivePotion() instanceof InvisibilityPotion) {
            // Move random
            Random randGen = new Random();
            List<Position> pos = ent.getPosition().getCardinallyAdjacentPositions();
            pos = pos.stream().filter(p -> map.canMoveTo(ent, p)).collect(Collectors.toList());
            if (pos.size() == 0) {
                nextPos = ent.getPosition();
                map.moveTo(ent, nextPos);
            } else {
                nextPos = pos.get(randGen.nextInt(pos.size()));
                map.moveTo(ent, nextPos);
            }
        } else if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            nextPos = runAway(map, ent);
        } else {
            // Follow hostile
            nextPos = map.dijkstraPathFind(ent.getPosition(), player.getPosition(), ent);
        }
        map.moveTo(ent, nextPos);
    }
}
