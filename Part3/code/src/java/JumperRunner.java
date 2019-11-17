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
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains jumper bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class JumperRunner
{
    private static final int XPOS = 3;
    private static final int YPOS = 3;

    private JumperRunner() {}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        world.add(new Location(XPOS, YPOS), jumper);
        world.show();
    }
}
