package dungeonmania.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import dungeonmania.util.Position;
import dungeonmania.map.GameMap;

public abstract class ExplosiveItem extends Entity implements Subscribable {
    public enum State {
        SPAWNED, INVENTORY, PLACED
    }

    private List<Subscribable> subs = new ArrayList<>();

    private State state;
    private int radius;

    public ExplosiveItem(Position position, int radius) {
        super(position);
        this.radius = radius;
    }

    public void subscribe(Subscribable s) {
        this.subs.add(s);
    }

    public List<Subscribable> getSubs() {
        return subs;
    }

    public void unsubscribe(Subscribable s) {
        subs.remove(s);
    }

    public void unsubscribeAll() {
        subs = new ArrayList<>();
    }

    public void notify(GameMap map) {
        explode(map);
    }

    public void explode(GameMap map) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                List<Entity> entities = map.getEntities(new Position(i, j));
                entities = entities.stream().filter(e -> !(e instanceof Player)).collect(Collectors.toList());
                for (Entity e : entities)
                    map.destroyEntity(e);
            }
        }
    }
    
    public State getState() {
        return state;
    }

    public void setState(State newState) {
        state = newState;
    }
}
