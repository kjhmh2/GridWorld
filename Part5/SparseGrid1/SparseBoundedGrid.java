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

import info.gridworld.grid.AbstractGrid; 
import info.gridworld.grid.Location;  
import java.util.ArrayList;

/**
 * A <code>SparseBoundedGrid</code> is a very large bounded grid that contains very few objects. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
@SuppressWarnings("all")
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private int row;
    private int col;
    private SparseGridNode[] store;

    /**
     * Constructs a SparseBoundedGrid.
     */
    public SparseBoundedGrid(int rowNum, int colNum)
    {
        if (rowNum <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (colNum <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        row = colNum;
        col = rowNum;
        store = new SparseGridNode[rowNum];
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
        // Look at all grid locations.
        for (int i = 0; i < getNumRows(); ++ i)
        {
            SparseGridNode node = store[i];
            while (node != null)
            {
                Location loc = new Location(i, node.getCol());
                temp.add(loc);
                node = node.getNext();
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
        SparseGridNode node = store[loc.getRow()];
        while (node != null)
        {
            if (node.getCol() == loc.getCol()) {
                return (E)node.getOccupant();
            }
            node = node.getNext();
        }
        return null;
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
        E oldOccupant = remove(loc);
        SparseGridNode node = store[loc.getRow()];
        store[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), node);
        return oldOccupant;
    }

    /**
     * Remove object from location
     */
    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        E temp = get(loc);
        if (temp == null) {
            return null;
        }

        SparseGridNode node = store[loc.getRow()];
        if (node != null)
        {
            // is first one
            if (loc.getCol() == node.getCol()) {
                store[loc.getRow()] = node.getNext();
            }
            else
            {
                SparseGridNode nodeNext = node.getNext();
                // finding
                while (nodeNext != null)
                {
                    if (nodeNext.getCol() != loc.getCol())
                    {
                        nodeNext = nodeNext.getNext();
                        node = node.getNext();
                    }
                }
                // set next
                if (nodeNext != null)
                {
                    SparseGridNode nodeNextNext = nodeNext.getNext();
                    node.setNext(nodeNextNext);
                }
            }
        }
        return temp;
    }
}
