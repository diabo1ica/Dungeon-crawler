package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;

public class InvincibleState implements PlayerState {
    public BattleStatistics applyState(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(
            0,
            0,
            0,
            1,
            1,
            true,
            true));
    }
}
