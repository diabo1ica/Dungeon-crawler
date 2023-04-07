package dungeonmania.entities.buildables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.inventory.InventoryItem;

public abstract class Buildable extends Entity implements InventoryItem {
    private int durability;

    public Buildable(int durability) {
        super(null);
        this.durability = durability;
    }

    public int getDurabilityStat() {
        return durability;
    }

    public void reduceDurability() {
        durability--;
    }
}
