package dungeonmania.entities.enemies;

import java.util.Random;

import dungeonmania.Game;

import dungeonmania.util.Position;

import dungeonmania.entities.enemies.MovementStrategy.ZombieToastMovement;
import dungeonmania.entities.enemies.MovementStrategy.Movement;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;
    private Random randGen = new Random();
    private Movement movement = new ZombieToastMovement();

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
    }

    // getter for randGen
    public Random getRandGen() {
        return this.randGen;
    }

    @Override
    public void move(Game game) {
        movement.move(game.getMap(), this);
    }
}
