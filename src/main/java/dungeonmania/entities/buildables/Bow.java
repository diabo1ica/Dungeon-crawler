package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.entities.BattleItem;
import dungeonmania.battles.BattleStatistics;

public class Bow extends Buildable implements BattleItem {
    public Bow(int durability) {
        super(durability);
    }

    @Override
    public void use(Game game) {
        reduceDurability();
        if (getDurability() <= 0) {
            game.getPlayer().remove(this);
        }
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 2, 1));
    }

    @Override
    public int getDurability() {
        return getDurabilityStat();
    }
}
