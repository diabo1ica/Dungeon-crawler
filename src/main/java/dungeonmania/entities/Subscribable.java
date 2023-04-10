package dungeonmania.entities;

import java.util.List;

import dungeonmania.util.Position;

public interface Subscribable {
    public void subscribe(Subscribable s);

    public List<Subscribable> getSubs();

    public void unsubscribe(Subscribable s);

    public void unsubscribeAll();

    public Position getPosition();
}
