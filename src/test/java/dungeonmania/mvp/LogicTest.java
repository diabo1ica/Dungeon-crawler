package dungeonmania.mvp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class LogicTest {
    @Test
    @DisplayName("Testing bomb explode via wires and switch")
    public void bomb() {
        // W   W   W   W   W   W
        // W   P               W
        // W   B               W
        // W   S   -   -   0   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_bomb_test",
                "c_logic_entity_test");

        // Initial wall count is 18
        // Initial wire count is 2

        // Move boulder to switch
        res = dmc.tick(Direction.DOWN);

        long curWallCount = TestUtils.countType(res, "wall");
        long curWireCount = TestUtils.countType(res, "wire");

        // After explosion wall count reduced to 13 and wire to 1
        assertEquals(13, curWallCount);
        assertEquals(1, curWireCount);
    }

    @Test
    @DisplayName("Testing AND logic bomb explode via wires and switch")
    public void andBomb() {
        // W   W   W   W   W   W
        // W   P   B   S   -   W
        // W   B           -   W
        // W   S   -   -   0   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_bomb_AND",
                "c_logic_entity_test");

        // Initial wall count is 18
        // Initial wire count is 4

        // Move boulder to switch bomb not activated
        res = dmc.tick(Direction.DOWN);
        assertEquals(18, TestUtils.countType(res, "wall"));
        assertEquals(4, TestUtils.countType(res, "wire"));

        // Move other boulder to switch and bomb exploded
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);


        // After explosion wall count reduced to 13 and wire to 1
        assertEquals(13, TestUtils.countType(res, "wall"));
        assertEquals(2, TestUtils.countType(res, "wire"));
    }

    @Test
    @DisplayName("Testing XOR logic bomb does not explode via wires and switch")
    public void xorBomb() {
        // W   W   W   W   W   W
        // W   P               W
        // W   B       -   -   W
        // W   S   -   -   0   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_bomb_XOR",
                "c_logic_entity_test");

        // Move boulder to switch activate 2 wires
        res = dmc.tick(Direction.DOWN);

        // Assert bomb does not explode
        assertEquals(18, TestUtils.countType(res, "wall"));
        assertEquals(4, TestUtils.countType(res, "wire"));

        // W   W   W   W   W   W
        // W   P               W
        // W   B           -   W
        // W   S   -   -   0   W
        // W   W   W   W   W   W
        res = dmc.newGame("d_logic_entity_bomb_XOR2",
                "c_logic_entity_test");

        // Move boulder to switch activate 1  out of 2 wires
        res = dmc.tick(Direction.DOWN);

        // Assert bomb does explodes
        assertEquals(13, TestUtils.countType(res, "wall"));
        assertEquals(1, TestUtils.countType(res, "wire"));
    }

    @Test
    @DisplayName("Testing COAND logic bomb does not explode via wires and switch")
    public void coAndBomb() {
        // W   W   W   W   W   W
        // W   P   B   S   -   W
        // W   B           -   W
        // W   S   -   -   0   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_bomb_COAND",
                "c_logic_entity_test");

        // Move boulder to switch activate 1 out of 2 wires
        res = dmc.tick(Direction.DOWN);

        // Assert bomb does not explode
        assertEquals(18, TestUtils.countType(res, "wall"));
        assertEquals(4, TestUtils.countType(res, "wire"));

        // Activate 2nd wire
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);

        // Assert bomb does not explode
        assertEquals(18, TestUtils.countType(res, "wall"));
        assertEquals(4, TestUtils.countType(res, "wire"));

        // W   W   W   W   W   W
        // W   P               W
        // W   B       -   -   W
        // W   S   -   -   0   W
        // W   W   W   W   W   W
        res = dmc.newGame("d_logic_entity_bomb_COAND2",
                "c_logic_entity_test");

        // Move boulder to switch activate wires
        res = dmc.tick(Direction.DOWN);

        // Assert bomb explodes
        assertEquals(13, TestUtils.countType(res, "wall"));
        assertEquals(1, TestUtils.countType(res, "wire"));
    }

    @Test
    @DisplayName("Testing switch door behavior via wires and switch")
    public void switchDoor() {
        // W   W   W   W   W   W
        // W   P               W
        // W   B               W
        // W   S   -   -   D   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_switch_door_test",
                "c_logic_entity_test");

        assertEquals(1, TestUtils.countType(res, "switch_door"));

        // Move boulder to switch and open switch door
        res = dmc.tick(Direction.DOWN);

        // Move to switch door
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);

        // Assert player on same tile as door
        assertEquals(new Position(4, 3), TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @DisplayName("Testing XOR logic door does not open via multiple wires and switch")
    public void xorSwitchDoor() {
        // W   W   W   W   W   W
        // W   P               W
        // W   B       -   -   W
        // W   S   -   -   D   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_door_XOR",
                "c_logic_entity_test");

        // Move boulder to switch activate 2 wires
        res = dmc.tick(Direction.DOWN);

        // Move to switch door
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);

        // Assert player not on same tile as door
        assertEquals(new Position(4, 2), TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @DisplayName("Testing OR logic switch door via wires and switch")
    public void orSwitchDoor() {
        // W   W   W   W   W   W
        // W   P   B   S   -   W
        // W   B           -   W
        // W   S   -   -   D   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_door_OR",
                "c_logic_entity_test");

        // Activate one Wire
        res = dmc.tick(Direction.DOWN);
        assertEquals(18, TestUtils.countType(res, "wall"));
        assertEquals(4, TestUtils.countType(res, "wire"));

        // Move other boulder to switch
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);

        // Move boulder from one switch
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.LEFT);

        // Assert player can stand on door tile
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(4, 3), TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @DisplayName("Testing CO_AND logic door does not open via multiple wires and switch")
    public void coAndSwitchDoor() {
        // W   W   W   W   W   W
        // W   P   B   S   -   W
        // W   B           -   W
        // W   S   -   -   D   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_door_COAND",
                "c_logic_entity_test");

        // Move boulder to switch activate 1 out of 2 wires
        res = dmc.tick(Direction.DOWN);

        // Activate 2nd wire
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);

        // Move to Door
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);

        // Assert player not on same tile as door
        assertEquals(new Position(4, 2), TestUtils.getEntities(res, "player").get(0).getPosition());

        // W   W   W   W   W   W
        // W   P               W
        // W   B       -   -   W
        // W   S   -   -   D   W
        // W   W   W   W   W   W
        res = dmc.newGame("d_logic_entity_door_COAND2",
                "c_logic_entity_test");

        // Move boulder to switch and activate wires
        res = dmc.tick(Direction.DOWN);

        // Move to Door
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);

        // Assert Player on same tile as switch door
        assertEquals(new Position(4, 3), TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @DisplayName("Testing simple light bulb activation")
    public void lightbulbOn() {
        // W   W   W   W   W   W
        // W   P               W
        // W   B               W
        // W   S   -   -   L   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_light_test",
                "c_logic_entity_test");

        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off").size());
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on").size());

        // Move boulder to switch
        res = dmc.tick(Direction.DOWN);

        // Assert light bulb is on
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_off").size());
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_on").size());
    }

    @Test
    @DisplayName("Test logical entity does not activate wires")
    public void lightDoesNotActivateWire() {
        // W   W   W   W   W   W
        // W   P               W
        // W   B               W
        // W   S   L   -   L   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_light_test2",
                "c_logic_entity_test");

        assertEquals(2, TestUtils.getEntities(res, "light_bulb_off").size());
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on").size());

        // Move boulder to switch
        res = dmc.tick(Direction.DOWN);

        // Assert only 1 light bulb is on
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off").size());
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_on").size());
    }

    @Test
    @DisplayName("Test switch does not activate switch")
    public void switchDoesNotActivateWire() {
        // W   W   W   W   W   W
        // W   P               W
        // W   B               W
        // W   S   S   -   L   W
        // W   W   W   W   W   W
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logic_entity_switch_test",
                "c_logic_entity_test");

        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off").size());
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on").size());

        // Move boulder to switch
        res = dmc.tick(Direction.DOWN);

        // Assert light bulb is off
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off").size());
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on").size());
    }

}
