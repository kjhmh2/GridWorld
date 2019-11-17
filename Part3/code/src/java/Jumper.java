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

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can jump. It “jumps” over rocks and flowers. It does not leave anything behind it when it jumps. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor
{
    /**
     * Constructs a Red Jumper.
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a Jumper of a given color.
     * @param jumperColor the color for this Jumper
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * Jumps if it can jump, turns otherwise.
     */
    public void act()
    {
        if (canJump()) {
            jump();
        } else {
            turn();
        }
    }

    /**
     * Turns the Jumper 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * It “jumps” over rocks and flowers. It does not leave anything behind it when it jumps.
     */
    public void jump()
    {
        // get grid
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        // get location
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next2 = next.getAdjacentLocation(getDirection());
        // move
        if (gr.isValid(next2)) {
            moveTo(next2);
        } else {
            removeSelfFromGrid();
        }
    }

    /**
     * Tests whether this Jumper can move forward into a location that is empty or
     * contains a flower.
     * @return true if this Jumper can move.
     */
    public boolean canJump()
    {
        // get grid
        Grid<Actor> gr = getGrid();
        // get location
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next2 = next.getAdjacentLocation(getDirection()); 
        // if the jumper faces other Bugs or the edge, it cannot jump
        if (gr == null || !gr.isValid(next) || !gr.isValid(next2)) {
            return false;
        }
        Actor neighbor = gr.get(next);
        if (!(neighbor instanceof Flower || neighbor instanceof Rock || neighbor == null)) {
            return false;
        }
        neighbor = gr.get(next2);
        return (neighbor == null) || (neighbor instanceof Flower);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}

