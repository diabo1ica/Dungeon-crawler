package dungeonmania;

import java.util.Stack;

/*
 * This class stores (at most) 30 tickss
 */
public class GameState {
    private Stack<Game> ListGameState = new Stack<>();
    private Stack<Game> TemporaryGameState = new Stack<>();

    public Stack<Game> getGameStateStack() {
        return this.ListGameState;
    }

    public Stack<Game> getTemporaryGameStateStack() {
        return this.TemporaryGameState;
    }

    // pop the stack
    public Game popFromGameState() {
        return getGameStateStack().pop();
    }

    // push the stack
    public void pushToGameState(Game gameState) {
        getGameStateStack().push(gameState);
    }

    public int getGameStateStackSize() {
        return getGameStateStack().size();
    }
}

// todo
// every time game ticks, push it to the gameState
// GameState.pushToGameState(Game.tick());
