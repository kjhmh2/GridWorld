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
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

/**
 * A <code>NewUnboundedGrid</code> is an implementation of an unbounded grid 
 in which all valid locations have non-negative row and column values. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
@SuppressWarnings("all")
public class NewUnboundedGrid<E> extends AbstractGrid<E>
{
    private static final int LIMIT = 16;
    private Object[][] store;
    private int row;

    /**
     * Constructs an UnboundedGrid.
     */
    public NewUnboundedGrid()
    {
        row = LIMIT;
        store = new Object[row][row];
    }

    public NewUnboundedGrid(int rowNum)
    {
        row = rowNum;
        store = new Object[row][row];
    }

    /**
     * Get rows
     */
    public int getNumRows()
    {
        return -1;
    }

    /**
     * Get cols
     */
    public int getNumCols()
    {
        return -1;
    }

    /**
     * Whether the location is valid
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    /**
     * Get occupied locations
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> temp = new ArrayList<Location>();
        for (int i = 0; i < row; ++ i)
        {
            for (int j = 0; j < row; ++ j) 
            {
                Location loc = new Location(i, j);
                if (get(loc) != null) {
                    temp.add(loc);
                }
            }
        }
        return temp;
    } 

    /**
     * Get object in location
     */
    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc.getRow() >= row || loc.getCol() >= row) {
            return null;
        }
        return (E)store[loc.getRow()][loc.getCol()]; 
    }

    /**
     * Put object at location
     */
    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        if (loc.getRow() >= row || loc.getCol() >= row)
        {
            int newRow = row;
            // extend the array
            while (loc.getRow() >= newRow || loc.getCol() >= newRow) 
            {
                newRow *= 2;
            }
            Object [][] newStore = new Object[newRow][newRow];
            // copy
            for (int i = 0; i < row; ++ i)
            {
                for (int j = 0; j < row; ++ j)
                {
                    newStore[i][j] = store[i][j];
                }
            }
            // set
            row = newRow;
            store = newStore;
        }
        E temp = get(loc);
        store[loc.getRow()][loc.getCol()] = obj;  
        return temp;
    }

    /**
     * Remove object from location
     */
    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc.getRow() >= row || loc.getCol() >= row) {
            return null;
        }
        E temp = get(loc);
        store[loc.getRow()][loc.getCol()] = null;
        return temp;
    }
}
