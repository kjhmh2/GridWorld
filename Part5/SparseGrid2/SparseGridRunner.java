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
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
/**
 * This class runs a world with additional grid choices.
 */
public final class SparseGridRunner
{
    private static final int XPOS = 3;
    private static final int YPOS = 3;
    private static final int ROCKXPOS = 5;
    private static final int ROCKYPOS = 5;

    private SparseGridRunner() {}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.add(new Location(XPOS, YPOS), new Critter());
        world.add(new Location(ROCKXPOS, ROCKYPOS), new Rock());
        world.show();
    }
}