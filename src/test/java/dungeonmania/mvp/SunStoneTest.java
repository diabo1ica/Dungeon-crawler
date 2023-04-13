package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SunStoneTest {
    @Test
    @DisplayName("Test picking up a SunStone removes the bomb from the map and adds the bomb to the inventory")
    public void pickUp() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_pickUp", "c_sunStoneTest_pickUp");
        assertEquals(1, TestUtils.getEntities(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up SunStone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getEntities(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
    }

    @Test
    @DisplayName("Test player can use a SunStone to open and walk through a door, SunStone remains in inventory")
    public void openDoor() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_pickUp", "c_sunStoneTest_pickUp");

        // Pick up SunStone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getEntities(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Try to open a door and walk through
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());

        // Sun stone is still inside inventory even though has been used to open door
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
    }

    @Test
    @DisplayName("Test player can use a SunStone to build shield, SunStone remains in inventory")
    public void buildShieldUsingSunStone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_buildShield", "c_sunStoneTest_buildShield");

        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Wood x2
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "wood").size());

        // Pick up SunStone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Build Shield
        assertEquals(0, TestUtils.getInventory(res, "shield").size());
        res = assertDoesNotThrow(() -> dmc.build("shield"));
        assertEquals(1, TestUtils.getInventory(res, "shield").size());

        // Woods are used up, but Sun Stone remains
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
    }

    @Test
    @DisplayName("Test Sun Stone contributes to Treasure goal")
    public void SunStoneTreasureGoal() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_treasureGoal", "c_sunStoneTest_treasureGoal");

        // move player to right
        res = dmc.tick(Direction.RIGHT);

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":treasure"));

        // collect treasure
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":treasure"));

        // collect treasure
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "treasure").size());

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":treasure"));

        // collect treasure
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "treasure").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
    }
}
