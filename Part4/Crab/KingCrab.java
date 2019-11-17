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
import java.util.*;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.Color;


/**
 * A <code>KingCrab</code> causes each actor that it processes to move one location further away from the KingCrab
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
@SuppressWarnings("PMD")
public class KingCrab extends CrabCritter
{
    public KingCrab()
    {
        setColor(Color.RED);
    }
    
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor actor : actors)
        {
            if (!moveObject(actor)) {
                actor.removeSelfFromGrid();
            }
        }
    }

    // whether the actor has place to move
    private boolean moveObject(Actor actor)
    {
        ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(actor.getLocation());
        Location temp = getLocation();
        for (Location loc : locs)
        {
            if (distance(temp, loc) > 1)
            {
                actor.moveTo(loc);
                return true;
            }
        }
        return false;
    }

    // get the distance
    private int distance(Location loc1, Location loc2)
    {
        int col1 = loc1.getCol();
        int col2 = loc2.getCol();
        int row1 = loc1.getRow();
        int row2 = loc2.getCol();
        double dis = Math.sqrt((col1 - col2) * (col1 - col2) + (row1 - row2) * (row1 - row2));
        return (int)Math.ceil(dis);
    }   
}
