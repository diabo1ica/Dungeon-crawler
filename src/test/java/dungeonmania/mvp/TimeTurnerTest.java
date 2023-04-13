package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTurnerTest {
    @Test
    @DisplayName("Basic rewind() method")
    public void test1() {
        DungeonManiaController dmc;
        DungeonResponse res;
        dmc = new DungeonManiaController();
        res = dmc.newGame("d_timeTurnerTestBasic", "c_timeTurnerTestBasic");
        System.out.println("There is " + TestUtils.getEntities(res, "bomb").size() + " bomb");
        System.out.println("There is " + TestUtils.getEntities(res, "time_turner").size() + " time_turner");
        assertEquals(1, TestUtils.getEntities(res, "time_turner").size());
        assertEquals(1, TestUtils.getEntities(res, "bomb").size());

        // pick up the bomb
        // player starts from (2, 2)
        // bomb is in (3, 2)
        res = dmc.tick(Direction.RIGHT); // csp = 0
        assertEquals(1, TestUtils.getEntities(res, "time_turner").size());
        assertEquals(0, TestUtils.getEntities(res, "bomb").size());

        res = dmc.tick(Direction.LEFT); // csp = 1
        assertEquals(1, TestUtils.getEntities(res, "time_turner").size());

        res = dmc.tick(Direction.RIGHT); // csp = 2
        assertEquals(1, TestUtils.getEntities(res, "time_turner").size());

        res = dmc.tick(Direction.RIGHT); // csp = 3
        assertEquals(0, TestUtils.getEntities(res, "time_turner").size());
        assertEquals(0, TestUtils.getEntities(res, "bomb").size());

        res = dmc.tick(Direction.RIGHT); // csp = 4
        res = dmc.tick(Direction.RIGHT); // csp = 5

        // try {
        //     res = dmc.rewind(5);
        // } catch (IllegalArgumentException e) {
        //     System.out.println(e.getMessage());
        // } catch (InsufficientTickCount e) {
        //     System.out.println(e.getMessage());
        // }

        System.out.println("There is " + TestUtils.getEntities(res, "time_turner").size() + " time_turners");
        System.out.println("There is " + TestUtils.getEntities(res, "bomb").size() + " bomb");
        // assertEquals(1, TestUtils.getEntities(res, "bomb").size());

    }

    @Test
    @DisplayName("Old Player is in the map as well as New Player")
    public void testOldPlayer() {
        DungeonManiaController dmc;
        DungeonResponse res;
        dmc = new DungeonManiaController();
        res = dmc.newGame("d_timeTurnerTestBasic", "c_timeTurnerTestBasic");

    }

    @Test
    @DisplayName("If game state is still in the past, don't generate new state and manually override gamestate using the next state inside gameState list")
    public void testGameState() {
        DungeonManiaController dmc = new DungeonManiaController();

    }

    @Test
    @DisplayName("All other entities (except new player) will take the 'original' steps")
    public void testEntitiesPosition() {
        DungeonManiaController dmc = new DungeonManiaController();

    }

    @Test
    @DisplayName("Mercenaries and assassins follow the current player, not the older player")
    public void testMercenary1() {
        DungeonManiaController dmc = new DungeonManiaController();

    }

    @Test
    @DisplayName("Mercenary does not attack Old Player and any other enemies")
    public void testMercenary2() {
        DungeonManiaController dmc = new DungeonManiaController();

    }

    @Test
    @DisplayName("Old Player battles New Player")
    public void OldPlayerNewPlayer() {
        DungeonManiaController dmc = new DungeonManiaController();

    }

    @Test
    @DisplayName("The older self should take the same path as was taken initially")
    public void OldPlayerDissapears() {
        DungeonManiaController dmc = new DungeonManiaController();

    }

    @Test
    @DisplayName("Only the player can travel through time travel portals")
    public void OnlyPlayerCanTimeTravel() {
        DungeonManiaController dmc = new DungeonManiaController();

    }

    @Test
    @DisplayName("The older player should still collect items and play out all tick and interact movements with those items as they did before. ")
    public void OldPlayerCollectItems() {
        DungeonManiaController dmc = new DungeonManiaController();

    }

}
