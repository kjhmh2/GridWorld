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
/**
 * This class runs a world with additional grid choices.
 */
public final class NewUnboundedGridRunner
{
    private static final int XPOS = 2;
    private static final int YPOS = 2;

    private NewUnboundedGridRunner() {}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("NewUnboundedGrid");
        world.add(new Location(XPOS, YPOS), new Critter());
        world.show();
    }
}