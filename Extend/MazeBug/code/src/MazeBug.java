import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
@SuppressWarnings("all")
public class MazeBug extends Bug 
{
    private Location next;
    private Location last;
    private boolean isEnd = false;
    private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    private Integer stepCount = 0;
    private boolean hasShown = false;//final message has been shown
    
    // 0 => North
    // 1 => East
    // 2 => South
    // 3 => West
    private int []probability = new int[4];

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length the side length
     */
    public MazeBug() 
    {
        setColor(Color.GREEN);
        last = new Location(0, 0);
        next = null;
        crossLocation = new Stack<ArrayList<Location>>();
        for (int i = 0; i < 4; ++ i)
        {
            probability[i] = 1;
        }
    }

    /**
     * Moves to the next location of the square.
     */

    public void act()
    {
        // init the list
        if (stepCount == 0)
        {
            ArrayList<Location> parent = new ArrayList<Location>();
            parent.add(getLocation());
            crossLocation.push(parent);
        }
        boolean willMove = canMove();
        // end of finding
        if (isEnd)
        {
            if (!hasShown)
            {
                String meg = "Total: " + stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, meg);
                hasShown = true;
            }
        }
        // can move
        else if (willMove)
        {
            last = getLocation();
            move();
            stepCount ++;
            ArrayList<Location> parent = crossLocation.pop();
            parent.add(getLocation());
            crossLocation.push(parent);
            ArrayList<Location> present = new ArrayList<Location>();
            present.add(getLocation());
            crossLocation.push(present);
        }
        // backtrack
        else
        {
            if (!crossLocation.empty())
            {
                // pop the present
                crossLocation.pop();
                if (!crossLocation.empty())
                {
                    Grid<Actor> grid = getGrid();
                    if (grid == null) {
                        return;
                    }
                    // get list
                    ArrayList<Location> parent = crossLocation.peek();
                    Location parentLocation = parent.get(0);

                    // set direction
                    int direction = getLocation().getDirectionToward(parentLocation);
                    setDirection(direction);
                    
                    // reduce probability
                    int temp = direction / 90;
                    reduceProbability(temp);
                    
                    // set location
                    last = getLocation();
                    next = parentLocation;
                    
                    // move
                    move();
                    stepCount ++;
                }
            }
        }
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc)
    {
        Grid<Actor> grid = getGrid();
        if (grid == null) {
            return null;
        }
        ArrayList<Location> store = new ArrayList<Location>();
        for (int i = 0; i < 4; ++ i)
        {
            Location nextLocation = loc.getAdjacentLocation(getDirection() + i * 90);
            if (grid.isValid(nextLocation))
            {
                Actor actor = grid.get(nextLocation);
                // reach the end
                if (actor instanceof Rock && actor.getColor().equals(Color.RED))
                {
                    isEnd = true;
                    store.add(nextLocation);
                    Location current = getLocation();
                    int direction = getLocation().getDirectionToward(nextLocation);
                    setDirection(direction);
                    moveTo(nextLocation);
                    Flower flower = new Flower(getColor());
                    flower.putSelfInGrid(grid, current);
                    setColor(Color.RED);
                    break;
                }
                if (actor == null) {
                    store.add(nextLocation);
                }
            }
        }
        return store;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove()
    {
        Grid<Actor> grid = getGrid();
        if (grid == null) {
            return false;
        }
        ArrayList<Location> store = getValid(getLocation());
        if (store.size() == 0) {
            return false;
        }
        ArrayList<Location> temp = new ArrayList<Location>();
        for (int i = 0; i < store.size(); ++ i)
        {
            // reach the end
            if (grid.get(store.get(i)) instanceof Rock)
            {
                next = store.get(i);
                return true;
            }
            temp.add(store.get(i));
        }
        next = chooseLocation(temp);
        return true;
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move()
    {
        Grid<Actor> grid = getGrid();
        if (grid == null) {
            return;
        }
        last = getLocation();
        if (grid.isValid(next))
        {
            int direction = getLocation().getDirectionToward(next);
            setDirection(direction);
            probability[direction / 90] ++;
            moveTo(next);
        }
        else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(grid, last);
    }

    /**
     * In backtrack, reduce the opposite position's probability
     *
     * @param direction the direction
     */
    private void reduceProbability(int direction)
    {
        switch(direction)
        {
            // north
            case 0:
                probability[2] --;
                break;
            
            // east
            case 1:
                probability[3] --;
                break;

            // south
            case 2:
                probability[0] --;
                break;

            // west
            default:
                probability[1] --;
                break;
        }
    }

    /**
     * find the best location with the probability
     *
     * @param store the valid locations
     * @return best location
     */
    private Location chooseLocation(ArrayList<Location> store)
    {
        Location resLoc = null;
        if (store.size() == 1)
        {
            int direction = getLocation().getDirectionToward(store.get(0));
            probability[direction / 90] ++;
            resLoc = store.get(0);
            return resLoc;
        }
        boolean[] haveDirection = new boolean[4];
        Location[] storeDirection = new Location[4];
        for (int i = 0; i < store.size(); ++ i)
        {
            int direction = getLocation().getDirectionToward(store.get(i));
            switch (direction / 90)
            {
                case 0:
                    haveDirection[0] = true;
                    storeDirection[0] = store.get(i);
                    break;
                
                case 1:
                    haveDirection[1] = true;
                    storeDirection[1] = store.get(i);
                    break;

                case 2: 
                    haveDirection[2] = true;
                    storeDirection[2] = store.get(i);
                    break;

                default: 
                    haveDirection[3] = true;
                    storeDirection[3] = store.get(i);
                    break;
            }
        }
        resLoc = randomChoose(haveDirection, storeDirection);
        int max = 0;
        int index = 0;
        if (resLoc == null) 
        {
            for (int i = 0; i < store.size(); ++ i)
            {
                int direction = getLocation().getDirectionToward(store.get(i));
                if (probability[direction / 90] > max)
                {
                    max = probability[direction / 90];
                    index = direction / 90;
                    resLoc = store.get(i);
                }
            }
            probability[index] ++;
        }
        return resLoc;
    }

    /**
     * choose the location with the probability
     *
     * @param haveDirection whether have the direction
     * @param storeDirection store the direction
     * @return the selected location
     */
    private Location randomChoose(boolean[] haveDirection, Location[] storeDirection)
    {
        // Randomly choose
        int sum = 0;
        for (int i = 0; i < 4; ++ i)
        {
            sum += probability[i];
        }
        int random = (int) (Math.random() * sum);
        Location resLoc = null;
        if (random >= 0 && random < probability[0] && haveDirection[0])
        {
            resLoc = storeDirection[0];
            probability[0] ++;
        }
        else if (random >= probability[0] && random < (probability[0] + probability[1]) && haveDirection[1])
        {
            resLoc = storeDirection[1];
            probability[1] ++;
        }
        else if (random >= (probability[0] + probability[1]) && random < (probability[0] + probability[1] + probability[2]) && haveDirection[2])
        {
            resLoc = storeDirection[2];
            probability[2] ++;
        }
        else if (random >= (probability[0] + probability[1] + probability[2]) && random < sum && haveDirection[3])
        {
            resLoc = storeDirection[3];
            probability[3] ++;
        }
        return resLoc;
    }
}