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


/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class RockHoundRunner
{
    private static final int ROCKXPOS1 = 3;
    private static final int ROCKYPOS1 = 3;
    private static final int ROCKXPOS2 = 4;
    private static final int ROCKYPOS2 = 4;
    private static final int ROCKXPOS3 = 2;
    private static final int ROCKYPOS3 = 8;
    private static final int ROCKHOUNDXPOS = 6;
    private static final int ROCKHOUNDYPOS = 6;

    private RockHoundRunner () {}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(ROCKXPOS1, ROCKYPOS1), new Rock());
        world.add(new Location(ROCKXPOS2, ROCKYPOS2), new Rock());
        world.add(new Location(ROCKXPOS3, ROCKYPOS3), new Rock());
        world.add(new Location(ROCKHOUNDXPOS, ROCKHOUNDYPOS), new RockHound());
        world.show();
    }
}