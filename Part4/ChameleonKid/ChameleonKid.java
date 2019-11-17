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
import java.util.*;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>ChameleonKid</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ModifiedChameleonCritter
{
    //Its neighbor just are the front and the back, so it need to change the function getActors.
    public ArrayList<Actor> getActors()
    {
        List<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            {Location.AHEAD, Location.HALF_CIRCLE};
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
            }
        }
        return new ArrayList<>(actors);
    }

    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        List<Location> locs = new ArrayList<Location>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
    
        for (int dir : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + dir);
            if (gr.isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }
        }
        return new ArrayList<>(locs);
    }
}
