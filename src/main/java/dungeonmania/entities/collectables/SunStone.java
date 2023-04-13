package dungeonmania.entities.collectables;

import dungeonmania.util.Position;
import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;

import dungeonmania.entities.inventory.InventoryItem;

public class SunStone extends Entity implements InventoryItem {
    public SunStone(Position position) {
        super(position);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

}

// special form of treasure

// can be picked up by player

// can be used to open doors

// can be used interchangeably with treasure or keys when building entities

// cannot bribe Assassin / Mercenary

// counts towards TReasure Goals

// Galaxy Buddha Mode:

/*abstract
1. Empty interafce CanOpenDoor, CanBribe
2. Door.java --> onOverlap --> if hasKey || if hasSunStone
3. Door.java --> hasSunStone(Player player)
4. ShieldRecipe.java --> can be used interchangly with trasure or keys when building entities
5. Player.java --> new attribute and method getSunStoneCount
6. Game.java --> new method getSunStoneCount
7. Player.java --> if pickup sunstone then countsunstone++;
8. GoalTreasure.java --> typeAchieved, sunstone now constributes to the calculation of treasure counts
9. Also in ShieldRecipe.java --> Sunstone can replace key, it is used, but the item is never used up
10. Door.java --> if sunstone is used to open door, sunstone is not being used
*/
