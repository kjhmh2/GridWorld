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
 * This class runs a world that contains ModifiedChameleonCritter. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ModifiedChameleonCritterRunner
{
    private static final int ROCKXPOS = 3;
    private static final int ROCKYPOS = 3;
    private static final int CRITTERXPOS = 5;
    private static final int CRITTERYPOS = 3;
    
    private ModifiedChameleonCritterRunner() {}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(ROCKXPOS, ROCKYPOS), new Rock(Color.BLUE));
        world.add(new Location(CRITTERXPOS, CRITTERYPOS), new ModifiedChameleonCritter());
        world.show();
    }
}