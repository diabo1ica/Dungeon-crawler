package dungeonmania;

import java.util.List;

import org.json.JSONException;

import dungeonmania.exceptions.InsufficientTickCount;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ResponseBuilder;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
/**
 * DO NOT CHANGE METHOD SIGNITURES OF THIS FILE
 * */
public class DungeonManiaController {
    private Game game = null;
    private GameState gs = new GameState();

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    /**
     * /dungeons
     */
    public static List<String> dungeons() {
        return FileLoader.listFileNamesInResourceDirectory("dungeons");
    }

    /**
     * /configs
     */
    public static List<String> configs() {
        return FileLoader.listFileNamesInResourceDirectory("configs");
    }

    /**
     * /game/new
     */
    public DungeonResponse newGame(String dungeonName, String configName) throws IllegalArgumentException {
        if (!dungeons().contains(dungeonName)) {
            throw new IllegalArgumentException(dungeonName + " is not a dungeon that exists");
        }

        if (!configs().contains(configName)) {
            throw new IllegalArgumentException(configName + " is not a configuration that exists");
        }

        try {
            GameBuilder builder = new GameBuilder();
            game = builder.setConfigName(configName).setDungeonName(dungeonName).buildGame();
            return ResponseBuilder.getDungeonResponse(game);
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * /game/dungeonResponseModel
     * this gives the current state of the game model
     */
    public DungeonResponse getDungeonResponseModel() {
        return ResponseBuilder.getDungeonResponse(game);
    }

    /**
     * /game/tick/item
     */

    /*
    UPDATES:
    1. The every tiem the game ticks, we get the state of the new game
    2. If the currentStatePointer (int) of gs is lower than the size
    3. This means currentStatePointer is still in the past state (as a result of time travel)
    */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        // Game newState = game.tick(itemUsedId);
        // gs.addGameState(newState);
        // return ResponseBuilder.getDungeonResponse(newState);
        Game currentGame = game.tick(itemUsedId);

        // if already in the present
        if (gs.currentStatePointer == (gs.getListGameStateSize() - 1)) {
            gs.addGameState(currentGame);
            gs.addCurrentStatePointer();
            // if still in the past
        } else {
            currentGame = gs.getCurrentState();
            gs.addCurrentStatePointer();
        }
        return ResponseBuilder.getDungeonResponse(currentGame);
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        Game currentGame = game.tick(movementDirection);
        // if already in the present
        if (gs.currentStatePointer == (gs.getListGameStateSize() - 1)) {
            gs.addGameState(currentGame);
            gs.addCurrentStatePointer();
            // if still in the past
        } else {
            currentGame = gs.getCurrentState();
            gs.addCurrentStatePointer();
        }
        return ResponseBuilder.getDungeonResponse(currentGame);
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        List<String> validBuildables = List.of("bow", "shield", "midnight_armour", "sceptre");
        if (!validBuildables.contains(buildable)) {
            throw new IllegalArgumentException("Only bow, shield, midnight_armour and sceptre can be built");
        }

        Game newState = game.build(buildable);
        gs.addGameState(newState);

        return ResponseBuilder.getDungeonResponse(newState);
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        Game newState = game.interact(entityId);
        gs.addGameState(newState);
        return ResponseBuilder.getDungeonResponse(newState);
    }

    /**
     * /game/new/generate
     */
    public DungeonResponse generateDungeon(int xStart, int yStart, int xEnd, int yEnd, String configName)
            throws IllegalArgumentException {
        return null;
    }

    /**
     * /game/rewind
     * go back n ticks
     * only used for the time turners (not the time travelling portals)
     * because the button will show up
     */
    public DungeonResponse rewind(int ticks) throws IllegalArgumentException, InsufficientTickCount {

        if (ticks <= 0) {
            throw new IllegalArgumentException("The number of ticks must be a positive integer!");
        }

        if (gs.getCurrentStatePointer() < (ticks - 1)) {
            throw new InsufficientTickCount("The argument ticks must not be larger than the current game tick counts!");
        }
        System.out.println("before rewind currentPointer: " + gs.currentStatePointer);
        // create GameState object
        Game currentGame = gs.timeTravelBoom(ticks);
        System.out.println("after rewind currentPointer: " + gs.currentStatePointer);

        return ResponseBuilder.getDungeonResponse(currentGame);
    }
}
