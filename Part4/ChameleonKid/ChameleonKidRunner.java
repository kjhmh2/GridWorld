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
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ChameleonKidRunner
{
    private static final int ROCKXPOS1 = 3;
    private static final int ROCKYPOS1 = 3;
    private static final int ROCKXPOS2 = 8;
    private static final int ROCKYPOS2 = 8;
    private static final int CRITTERXPOS = 5;
    private static final int CRITTERYPOS = 3;

    private ChameleonKidRunner() {}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(ROCKXPOS1, ROCKYPOS1), new Rock(Color.BLUE));
        world.add(new Location(ROCKXPOS2, ROCKYPOS2), new Rock(Color.RED));
        world.add(new Location(CRITTERXPOS, CRITTERYPOS), new ChameleonKid());
        world.show();
    }
}