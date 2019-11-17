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
 * A <code>DancingBug</code> traces out "dance" of a given array. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int index;
    private int times;
    private int[] store;

    /**
     * Constructs a dancing bug that traces a square of a given array
     * @param length the side length
     */
    public DancingBug(int[] array)
    {
        index = 0;
        times = 0;
        store = new int[array.length];
        System.arraycopy(array, 0, store, 0, array.length);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (times < store[index]) 
        {
            turn();
            times ++;
            return;
        }
        if (canMove())
        {
            move();
            times = 0;
            index ++;
            // reset the index from zero
            if (index == store.length) {
                index = 0;
            }
        }
        else {
            turn();
        }
    }
}
