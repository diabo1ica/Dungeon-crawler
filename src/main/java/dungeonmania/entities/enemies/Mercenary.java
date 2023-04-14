package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Interactable;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.buildables.*;

import dungeonmania.entities.enemies.MovementStrategy.Movement;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

import dungeonmania.entities.enemies.MovementStrategy.MercenaryMovement;

public class Mercenary extends Enemy implements Interactable {
    public static final int DEFAULT_BRIBE_AMOUNT = 1;
    public static final int DEFAULT_BRIBE_RADIUS = 1;
    public static final double DEFAULT_ATTACK = 5.0;
    public static final double DEFAULT_HEALTH = 10.0;

    private int bribeAmount = Mercenary.DEFAULT_BRIBE_AMOUNT;
    private int bribeRadius = Mercenary.DEFAULT_BRIBE_RADIUS;

    private int mind_being_controlled_duration = 0;

    private double allyAttack;
    private double allyDefence;
    private boolean allied = false;
    private boolean isAdjacentToPlayer = false;

    private Movement movement = new MercenaryMovement();

    public Mercenary(Position position, double health, double attack, int bribeAmount, int bribeRadius,
            double allyAttack, double allyDefence) {
        super(position, health, attack);
        this.bribeAmount = bribeAmount;
        this.bribeRadius = bribeRadius;
        this.allyAttack = allyAttack;
        this.allyDefence = allyDefence;
    }

    public boolean isAllied() {
        return allied;
    }

    public boolean isAdjacentToPlayer() {
        return isAdjacentToPlayer;
    }

    public void setIsAdjacentToPlayer(boolean status) {
        this.isAdjacentToPlayer = status;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (allied)
            return;
        super.onOverlap(map, entity);
    }

    /**
     * check whether the current merc can be bribed
     * @param player
     * @return
     */
    private boolean canBeBribed(Player player) {
        return bribeRadius >= 0 && player.countEntityOfType(Treasure.class) >= bribeAmount;
    }

    private boolean canBeMindControlled(Player player) {
        return player.countEntityOfType(Sceptre.class) > 0;
    }

    /**
     * bribe the merc
     */
    private void bribe(Player player) {
        for (int i = 0; i < bribeAmount; i++) {
            player.use(Treasure.class);
        }
    }

    @Override
    public void interact(Player player, Game game) {
        if (canBeMindControlled(player)) {
            allied = true;
            mind_being_controlled_duration = 2;
        } else {
            allied = true;
            bribe(player);
            if (!isAdjacentToPlayer && Position.isAdjacent(player.getPosition(), getPosition()))
            isAdjacentToPlayer = true;
    }

}

    @Override
    public void move(Game game) {
        movement.move(game.getMap(), this);
        // every time the mercenary moves (meanign 1 tick is consumed)
        // update the mind_being_controlled_duration accordinglysß
        if (mind_being_controlled_duration > 0) {
            mind_being_controlled_duration--;
        }
    }
    // change is interactab
    @Override
    public boolean isInteractable(Player player) {
        return (!allied && canBeBribed(player) || canBeMindControlled(player));
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        if (!allied)
            return super.getBattleStatistics();
        return new BattleStatistics(0, allyAttack, allyDefence, 1, 1);
    }
}
