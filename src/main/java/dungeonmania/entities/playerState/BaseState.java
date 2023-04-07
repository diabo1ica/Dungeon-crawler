package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;

public class BaseState implements PlayerState {
    public BattleStatistics applyState(BattleStatistics origin) {
        return origin;
    }
}
