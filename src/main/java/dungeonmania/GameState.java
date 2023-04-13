package dungeonmania;

import java.util.List;
import java.util.ArrayList;

/*
 * this class handles all activities regarding game state this includes:
 * changing between gamestates
 */
public class GameState {
    public int currentStatePointer = -1;
    private List<Game> ListGameState = new ArrayList<>();

    public List<Game> getListGameState() {
        return this.ListGameState;
    }

    public void addCurrentStatePointer() {
        currentStatePointer = currentStatePointer + 1;
    }

    public Game getCurrentState() {
        return ListGameState.get(currentStatePointer);
    }

    public int getCurrentStatePointer() {
        return currentStatePointer;
    }

    public int getListGameStateSize() {
        return getListGameState().size();
    }

    public void addGameState(Game newState) {
        ListGameState.add(newState);
    }

    public Game timeTravelBoom(int ticks) {
        // get the player before updating currentStatePointer
        // this will be the new player
        Game currentGame = getCurrentState();

        currentStatePointer = currentStatePointer - ticks;

        // i have to change the old player
        // such that it is allied with the enemy
        // and it will battle with 
        currentGame = getCurrentState();
        System.out.println("the current pointer is: " + currentStatePointer);

        return currentGame;
    }

    // public Game moveGameTick(int num) {
    //     return getListGameState().get(num + 1);
    // }

}
