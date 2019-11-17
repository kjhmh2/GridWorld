/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains dancing bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class DancingBugRunner
{
    private static final int XPOS = 5;
    private static final int YPOS = 5;
    private static final int TURN1 = 1;
    private static final int TURN2 = 2;
    private static final int TURN3 = 3;
    private static final int TURN4 = 4;

    private DancingBugRunner() {}

    public static void main(String[] args)
    {
        int[] array = {TURN1, TURN2, TURN3, TURN4};
        ActorWorld world = new ActorWorld();
        DancingBug dancingBug = new DancingBug(array);
        dancingBug.setColor(Color.ORANGE);
        world.add(new Location(XPOS, YPOS), dancingBug);
        world.show();
    }
}