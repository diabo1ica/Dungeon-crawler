package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;

public class BaseState implements PotionState {
    public BattleStatistics applyState(BattleStatistics origin) {
        return origin;
    }
}
