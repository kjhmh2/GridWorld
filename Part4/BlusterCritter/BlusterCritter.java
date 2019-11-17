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
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A <code>BlusterCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
@SuppressWarnings("PMD")
public class BlusterCritter extends Critter
{
    private static final double DARKENING_FACTOR = 0.05;
    private static final double BRIGHTING_FACTOR = 0.05;
    private static final int RGBLIMIT = 255;
    private int courage;

    public BlusterCritter(int c)
    {
        super();
        courage = c;
    }

    public ArrayList<Actor> getActors()
    {
        List<Actor> actors = new ArrayList<Actor>();
        Location loc = getLocation(); 
        // get the position
        for (int i = loc.getRow() - 2; i <= loc.getRow() + 2; ++ i)
        {
            for (int j = loc.getCol() - 2; j <= loc.getCol() + 2; ++ j)
            {
                Location loc2 = new Location(i, j);
                if (getGrid().isValid(loc2))
                {
                    Actor actor = getGrid().get(loc2);
                    if (actor instanceof Critter && actor != this) {
                        actors.add(actor);
                    }
                }
            }
        }
        return new ArrayList<>(actors);
    }

    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        // if there are c or more critters, the BlusterCritter’s color darkens
        if (n >= courage)
        {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        }
        // if there are fewer than c critters, the BlusterCritter’s color gets brighter
        else
        {
            Color c = getColor();
            int red = (int) ((c.getRed() / (1 - BRIGHTING_FACTOR)) < RGBLIMIT ? (c.getRed() / (1 - BRIGHTING_FACTOR)) : RGBLIMIT);
            int green = (int) ((c.getGreen() / (1 - BRIGHTING_FACTOR)) < RGBLIMIT ? (c.getGreen() / (1 - BRIGHTING_FACTOR)) : RGBLIMIT);
            int blue = (int) ((c.getBlue() / (1 - BRIGHTING_FACTOR)) < RGBLIMIT ? (c.getBlue() / (1 - BRIGHTING_FACTOR)) : RGBLIMIT);
            setColor(new Color(red, green, blue));
        }
        return;
    }
}
