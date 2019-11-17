import static org.junit.Assert.*;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import org.junit.Test;

public class JumperTest 
{
    private static final int INITXPOS = 2;
    private static final int INITYPOS = 2;

    private static final int ROCKXPOS = 2;
    private static final int ROCKYPOS = 5;

    private static final int JUMPERXPOS = 2;
    private static final int JUMPERYPOS = 7;

    private static final int FLOWERXPOS = 2;
    private static final int FLOWERYPOS = 3;

    private static final int ROCKXPOS2 = 2;
    private static final int ROCKYPOS2 = 6;

    private static final int INITXPOS2 = 0;
    private static final int INITYPOS2 = 1;

    private static final int INITXPOS3 = 1;
    private static final int INITYPOS3 = 1;

    @Test
    public void testAct() 
    {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        jumper.setDirection(Location.EAST);
        world.add(new Location(INITXPOS, INITYPOS), jumper);
        // test1
        jumper.act();
        assertEquals("(2, 4)", jumper.getLocation().toString());

        // test2
        world.add(new Location(ROCKXPOS, ROCKYPOS), new Rock());
        jumper.act();
        assertEquals("(2, 6)", jumper.getLocation().toString());

        // test3
        Jumper jumper2 = new Jumper();
        world.add(new Location(JUMPERXPOS, JUMPERYPOS), jumper2);
        jumper.act();
        assertEquals(jumper.getDirection(), Location.SOUTHEAST);

        // test4
        jumper.moveTo(new Location(INITXPOS, INITYPOS));
        jumper.setDirection(Location.EAST);
        world.add(new Location(FLOWERXPOS, FLOWERYPOS), new Flower());
        jumper.act();
        assertEquals("(2, 4)", jumper.getLocation().toString());

        // test5
        world.add(new Location(ROCKXPOS2, ROCKYPOS2), new Rock());
        jumper.act();
        assertEquals(jumper.getDirection(), Location.SOUTHEAST);

        // test6
        jumper.moveTo(new Location(INITXPOS2, INITYPOS2));
        jumper.setDirection(Location.NORTH);
        jumper.act();
        assertEquals(jumper.getDirection(), Location.NORTHEAST);

        // test7
        jumper.moveTo(new Location(INITXPOS3, INITYPOS3));
        jumper.setDirection(Location.NORTH);
        jumper.act();
        assertEquals(jumper.getDirection(), Location.NORTHEAST);
    }

    @Test 
    public void testTurn() 
    {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        world.add(new Location(2, 2), jumper);
        // set east
        jumper.setDirection(Location.EAST);
        assertEquals(Location.EAST, jumper.getDirection());
        jumper.turn();
        jumper.turn();
        // whether it is south
        assertEquals(Location.SOUTH, jumper.getDirection());
        jumper.turn();
        jumper.turn();
        // whether it is west
        assertEquals(Location.WEST, jumper.getDirection());
        jumper.turn();
        jumper.turn();
        // whether it is north
        assertEquals(Location.NORTH, jumper.getDirection());
    }
}

