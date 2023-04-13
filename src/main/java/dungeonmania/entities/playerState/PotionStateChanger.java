package dungeonmania.entities.playerState;

import dungeonmania.entities.collectables.potions.*;

public class PotionStateChanger {
    public static PotionState transition(Potion inEffective) {
        if (inEffective == null) {
            return new BaseState();
        } else if (inEffective instanceof InvisibilityPotion) {
            return new InvisibleState();
        } else {
            return new InvincibleState();
        }
    }
}
