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
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * This class runs a world that contains crab critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class BlusterCritterRunner
{
    private static final int CRITTERXPOS1 = 2;
    private static final int CRITTERYPOS1 = 2;
    private static final int CRITTERXPOS2 = 4;
    private static final int CRITTERYPOS2 = 2;
    private static final int CRITTERXPOS3 = 2;
    private static final int CRITTERYPOS3 = 4;
    private static final int CRITTERXPOS4 = 7;
    private static final int CRITTERYPOS4 = 7;
    private static final int INITXPOS = 5;
    private static final int INITYPOS = 5;
    private static final int COURAGE = 1;

    private BlusterCritterRunner() {}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        BlusterCritter blusterCritter = new BlusterCritter(COURAGE);
        blusterCritter.setColor(Color.RED);
        world.add(new Location(CRITTERXPOS1, CRITTERYPOS1), new Critter());
        world.add(new Location(CRITTERXPOS2, CRITTERYPOS2), new Critter());
        world.add(new Location(CRITTERXPOS3, CRITTERYPOS3), new Critter());
        world.add(new Location(CRITTERXPOS4, CRITTERYPOS4), new Critter());
        world.add(new Location(INITXPOS, INITYPOS), blusterCritter);
        world.show();
    }
}