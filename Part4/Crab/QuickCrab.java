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
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;


/**
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
@SuppressWarnings("PMD")
public class QuickCrab extends CrabCritter
{
    public QuickCrab()
    {
        setColor(Color.BLUE);
    }

    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        List<Location> locs = new ArrayList<Location>();
        int currentDir = getDirection();
        int[] dirs =
            {currentDir + Location.LEFT, currentDir + Location.RIGHT};
        getLocationsInTwoSteps(locs, dirs);
        if (locs.size() == 0) {
            return super.getMoveLocations();
        } else {
            return new ArrayList<>(locs);
        }
    }
    
    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public void getLocationsInTwoSteps(List<Location> locs, int[] directions)
    {
        Grid gr = getGrid();
        Location loc = getLocation();
        for (int i = 0; i < directions.length; ++ i)
        {
            Location loc2 = loc.getAdjacentLocation(directions[i]);
            if (gr.isValid(loc2) && gr.get(loc2) == null)
            {
                Location loc3 = loc2.getAdjacentLocation(directions[i]);
                if (gr.isValid(loc3) && gr.get(loc3) == null) {
                    locs.add(loc3);
                }
            }
        }
    }   
}
