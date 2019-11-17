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
 * A <code>SparseBoundedGrid</code> using a HashMap or TreeMap to implement 
 the SparseBoundedGrid. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
@SuppressWarnings("PMD")
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private int col;
    private int row;
    private Map<Location, E> store;

    /**
     * Constructs a SparseBoundedGrid.
     */
    public SparseBoundedGrid(int rowNum, int colNum)
    {
        col = rowNum;
        row = colNum;
        store = new HashMap<Location, E>();
        if (rowNum <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (colNum <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
    }

    /**
     * Get rows
     */
    public int getNumRows()
    {
        return row;
    }

    /**
     * Get cols
     */
    public int getNumCols()
    {
        return col;
    }

    /**
     * Whether the location is valid
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getRow() < getNumRows()
                && loc.getCol() >= 0 && loc.getCol() < getNumCols();
    }

    /**
     * Get occupied locations
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> temp = new ArrayList<Location>();
        for (Location loc : store.keySet()) 
        {
            temp.add(loc);
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
        return store.get(loc);
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
        return store.put(loc, obj);
    }

    /**
     * Remove object from location
     */
    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        return store.remove(loc);
    }
}
