package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;

public interface PotionState {
    public BattleStatistics applyState(BattleStatistics origin);
}
