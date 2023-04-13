package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

public class TimeTravellingPortalTest {
    @Test
    @DisplayName("Basic Time Travel Behaviour")
    public void test1() {
        DungeonManiaController dmc;
        DungeonResponse res;
        dmc = new DungeonManiaController();
        res = dmc.newGame("d_timeTurnerTestBasic", "c_timeTurnerTestBasic");
        System.out.println("There is " + TestUtils.getEntities(res, "bomb").size() + " bomb");
        System.out.println("There is " + TestUtils.getEntities(res, "time_turner").size() + " time_turner");
        assertEquals(1, TestUtils.getEntities(res, "time_turner").size());
        assertEquals(1, TestUtils.getEntities(res, "bomb").size());

        res = dmc.tick(Direction.RIGHT); // csp = 0
        assertEquals(1, TestUtils.getEntities(res, "time_turner").size());
        assertEquals(0, TestUtils.getEntities(res, "bomb").size());
    }
}
