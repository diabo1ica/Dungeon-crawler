package dungeonmania.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.collectables.Bomb;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Switch extends Entity implements Subscribable, OverlapBehaviour, MovedAwayBehaviour {
    private boolean activated;
    private List<Subscribable> subs = new ArrayList<>();

    public Switch(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
    }

    public void subscribe(Subscribable b) {
        subs.add(b);
    }

    public List<Subscribable> getSubs() {
        return subs;
    }

    public void subscribe(Bomb bomb, GameMap map) {
        subs.add(bomb);
        if (activated) {
            getBombsFromSubs().forEach(b -> b.notify(map));
        }
    }

    public void unsubscribe(Subscribable s) {
        subs.remove(s);
    }

    public void unsubscribeAll() {
        subs = new ArrayList<>();
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = true;
            getBombsFromSubs().forEach(b -> b.notify(map));
        }
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = false;
        }
    }

    private List<ExplosiveItem> getBombsFromSubs() {
        return subs.stream().filter(ExplosiveItem.class::isInstance)
        .map(ExplosiveItem.class::cast)
        .collect(Collectors.toList());
    }

    public boolean isActivated() {
        return activated;
    }
}
