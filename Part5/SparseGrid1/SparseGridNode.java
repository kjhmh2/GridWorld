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

public class SparseGridNode
{
    private Object occupant;
    private int col;
    private SparseGridNode next;
    
    // constructor
    public SparseGridNode(Object object, int colNum, SparseGridNode nextNode)
    {
        occupant = object;
        col = colNum;
        next = nextNode;
    }

    // getter
    public Object getOccupant()
    {
        return occupant;
    }

    // setter
    public void setOccupant(Object object)
    {
        occupant = object;
    }

    // getter
    public int getCol()
    {
        return col;
    }

    // setter
    public void setCol(int colNum)
    {
        col = colNum;
    }

    // getter
    public SparseGridNode getNext()
    {
        return next;
    }

    // setter
    public void setNext(SparseGridNode nextNode)
    {
        next = nextNode;
    }
}
