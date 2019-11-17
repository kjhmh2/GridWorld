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

import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces out a "z" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private boolean firstEast;
    private boolean isOver;
    /**
     * The compass direction for east.
     */
    public static final int EAST = 90;
    /**
     * The compass direction for southwest.
     */
    public static final int SOUTHWEST = 225;

    /**
     * Constructs a z bug that traces a square of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        sideLength = length;
        // face east
        setDirection(EAST);
        // whether need to stop
        isOver = false;
        firstEast = true;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (isOver) {
            return;
        }
        if (steps < sideLength)
        {
            if (canMove()) 
            {
                move();
                steps ++;
            }
            else 
            {
                isOver = true;
                return;
            }
        }
        else
        {
            if (getDirection() == EAST) 
            {
                // first east
                if (firstEast) 
                {
                    setDirection(SOUTHWEST);
                    firstEast = false;
                } 
                // second east
                else {
                    isOver = true;
                }
            }
            else if (getDirection() == SOUTHWEST) {
                setDirection(EAST);
            }
            steps = 0;
        }
    }
}
