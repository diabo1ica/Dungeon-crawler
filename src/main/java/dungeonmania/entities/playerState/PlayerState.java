package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;

public interface PlayerState {
    public BattleStatistics applyState(BattleStatistics origin);
}
