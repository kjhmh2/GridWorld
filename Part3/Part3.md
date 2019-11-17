# Part3

海明皓 17343037

### Set 3

Assume the following statements when answering the following questions.

```java
Location loc1 = new Location(4, 3);
Location loc2 = new Location(3, 4);
```

1. How would you access the row value for loc1?

   ​	使用`getRow`方法，即是：

   ```java
   int row1 = loc1.getRow();
   ```

   ​	在Location文件中可找到定义如下所示：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:106~113
      
   /**
    * Gets the row coordinate.
    * @return the row of this location
    */
   public int getRow()
   {
       return row;
   }
   ```

2. What is the value of b after the following statement is executed?     

   ```java
    boolean b = loc1.equals(loc2);
   ```

   ​	b的值为`false`，因为loc1和loc2并不在同一位置，调用的函数如下所示：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:197~212
   
   /**
       * Indicates whether some other <code>Location</code> object is "equal to"
       * this one.
       * @param other the other location to test
       * @return <code>true</code> if <code>other</code> is a
       * <code>Location</code> with the same row and column as this location;
       * <code>false</code> otherwise
       */
   public boolean equals(Object other)
   {
       if (!(other instanceof Location))
           return false;
   
       Location otherLoc = (Location) other;
       return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
   }
   ```

3. What is the value of loc3 after the following statement is executed?     

   ```
    Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
   ```

   ​	`loc3`的`row`值为4，`col`的值也为4，因为取了`loc2`的南方向的临近点，所以只需要在`loc2`的基础上增加一个单位的row即可。

   ​	调用的函数如下所示：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:124~169
   
   /**
       * Gets the adjacent location in any one of the eight compass directions.
       * @param direction the direction in which to find a neighbor location
       * @return the adjacent location in the direction that is closest to
       * <tt>direction</tt>
       */
   public Location getAdjacentLocation(int direction)
   {
       // reduce mod 360 and round to closest multiple of 45
       int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
       if (adjustedDirection < 0)
           adjustedDirection += FULL_CIRCLE;
   
       adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
       int dc = 0;
       int dr = 0;
       if (adjustedDirection == EAST)
           dc = 1;
       else if (adjustedDirection == SOUTHEAST)
       {
           dc = 1;
           dr = 1;
       }
       else if (adjustedDirection == SOUTH)
           dr = 1;
       else if (adjustedDirection == SOUTHWEST)
       {
           dc = -1;
           dr = 1;
       }
       else if (adjustedDirection == WEST)
           dc = -1;
       else if (adjustedDirection == NORTHWEST)
       {
           dc = -1;
           dr = -1;
       }
       else if (adjustedDirection == NORTH)
           dr = -1;
       else if (adjustedDirection == NORTHEAST)
       {
           dc = 1;
           dr = -1;
       }
       return new Location(getRow() + dr, getCol() + dc);
   }
   ```

4. What is the value of dir after the following statement is executed?     

   ```
    int dir = loc1.getDirectionToward(new Location(6, 5));
   ```

   ​	dir的值为135，因为对于`loc1`来说，`(6,5)`在其东南方向，东南方向对应的角度为135度。

   ​	调用的函数如下所示：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:171~195
   
   /**
       * Returns the direction from this location toward another location. The
       * direction is rounded to the nearest compass direction.
       * @param target a location that is different from this location
       * @return the closest compass direction from this location toward
       * <code>target</code>
       */
   public int getDirectionToward(Location target)
   {
       int dx = target.getCol() - getCol();
       int dy = target.getRow() - getRow();
       // y axis points opposite to mathematical orientation
       int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));
   
       // mathematical angle is counterclockwise from x-axis,
       // compass angle is clockwise from y-axis
       int compassAngle = RIGHT - angle;
       // prepare for truncating division by 45 degrees
       compassAngle += HALF_RIGHT / 2;
       // wrap negative angles
       if (compassAngle < 0)
           compassAngle += FULL_CIRCLE;
       // round to nearest multiple of 45
       return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
   }
   ```

5. How does the getAdjacentLocation method know which adjacent location to return?

   ​	首先，`getAdjacentLocation`函数将`direction`转换到360度的范围内，并取一个45的倍数，让其最接近`direction`的值：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:132~137
   
   // reduce mod 360 and round to closest multiple of 45
   int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
   if (adjustedDirection < 0)
       adjustedDirection += FULL_CIRCLE;
   
   adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
   ```

   ​	然后，通过`adjustedDirection`的值，逐一判断对应方向应该在row和col上增加或减少多少：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:138~167
   int dc = 0;
   int dr = 0;
   if (adjustedDirection == EAST)
       dc = 1;
   else if (adjustedDirection == SOUTHEAST)
   {
       dc = 1;
       dr = 1;
   }
   else if (adjustedDirection == SOUTH)
       dr = 1;
   else if (adjustedDirection == SOUTHWEST)
   {
       dc = -1;
       dr = 1;
   }
   else if (adjustedDirection == WEST)
       dc = -1;
   else if (adjustedDirection == NORTHWEST)
   {
       dc = -1;
       dr = -1;
   }
   else if (adjustedDirection == NORTH)
       dr = -1;
   else if (adjustedDirection == NORTHEAST)
   {
       dc = 1;
       dr = -1;
   }
   ```

   ​	通过调用`getRow()`和`getCol()`方法，获取点的坐标：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:168
   
   return new Location(getRow() + dr, getCol() + dc);
   ```

### Set 4

1. How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

   ​	因为`getOccupiedLocation()`返回一个`ArrayList`，所以我们可以通过`getOccupiedLocations().size()`来得到网格中的物体数量。

   ​	`getOccupiedLocation()`的接口如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Grid.java
   //@line:81~85
   /**
   * Gets the locations in this grid that contain objects.
   * @return an array list of all occupied locations in this grid
   */
   ArrayList<Location> getOccupiedLocations();
   ```

   ​	在BoundedGrid中的实现如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:66~83
   public ArrayList<Location> getOccupiedLocations()
   {
       ArrayList<Location> theLocations = new ArrayList<Location>();
   
       // Look at all grid locations.
       for (int r = 0; r < getNumRows(); r++)
       {
           for (int c = 0; c < getNumCols(); c++)
           {
               // If there's an object at this location, put it in the array.
               Location loc = new Location(r, c);
               if (get(loc) != null)
                   theLocations.add(loc);
           }
       }
   
       return theLocations;
   }
   ```

   ​	要获取空余位置的数量，我们只需要用所有数量减去占有的数量即可，也就是

   使用`getNumRows()*getNumCols()`，然后减去刚才的占有数量即可。

   ​	函数定义如下所示：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:48~58
   
   public int getNumRows()
   {
       return occupantArray.length;
   }
   
   public int getNumCols()
   {
       // Note: according to the constructor precondition, numRows() > 0, so
       // theGrid[0] is non-null.
       return occupantArray[0].length;
   }
   ```

2. How can you check if location (10,10) is in a grid?

   ​	我们使用`isValid`函数来检测这个位置是否在网格中：

   ```java
   boolean judge = isValid(new Location(10,10));
   ```

   ​	函数的定义如下所示：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:60~64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
               && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ```

3. Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

   ​	因为Grid是接口，接口只是定义函数，具体的实现在其他类中；AbstractGrid实现了Grid，代码如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
   //@line:21~26
   /**
    * <code>AbstractGrid</code> contains the methods that are common to grid
    * implementations. <br />
    * The implementation of this class is testable on the AP CS AB exam.
    */
   public abstract class AbstractGrid<E> implements Grid<E>
   {
   	//...
   }
   ```

   ​	当然，UnboundedGrid和BoundedGrid通过继承虚基类AbstractGrid也实现了这些函数。

4. All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

   ​	不认为返回一个数组是更好的设计，ArrayList的优点在不需要预先设定它的大小，它可以自动增加或减少，而数组显然没有这个优点，所以可能会浪费空间。

### Set 5

1. Name three properties of every actor.

   ​	每个Actor都具有所在网格、位置、方向、颜色属性。

   ​	如下所示：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Actor.java
   //@line:31~34
   private Grid<Actor> grid;
   private Location location;
   private int direction;
   private Color color;
   ```

2. When an actor is constructed, what is its direction and color?

   ​	当actor被创建的时候，其方向默认朝向正北，颜色为蓝色。

   ​	代码如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Actor.java
   //@line:39~45
   public Actor()
   {
       color = Color.BLUE;
       direction = Location.NORTH;
       grid = null;
       location = null;
   }
   ```

3. Why do you think that the Actor class was created as a class instead of an interface?

   ​	因为actor类声明函数之后有函数的实现，比如`getColor`函数：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Actor.java
   //@line:39~45
   /**
    * Gets the color of this actor.
    * @return the color of this actor
    */
   public Color getColor()
   {
   	return color;
   }
   ```

4. Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

   ​	不能将自己重复加入两次，代码中有加入判断条件，如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Actor.java
   //@line:115~127
   public void putSelfInGrid(Grid<Actor> gr, Location loc)
   {
       if (grid != null)
           throw new IllegalStateException(
                   "This actor is already contained in a grid.");
   
       Actor actor = gr.get(loc);
       if (actor != null)
           actor.removeSelfFromGrid();
       gr.put(loc, this);
       grid = gr;
       location = loc;
   }
   ```

   ​	可见，如果grid非空，代表已经加入了grid，这时就会抛出异常。

   ​	不能将自己删除两次，代码中也有加入判断条件，如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Actor.java
   //@line:133~146
   public void removeSelfFromGrid()
   {
       if (grid == null)
           throw new IllegalStateException(
                   "This actor is not contained in a grid.");
       if (grid.get(location) != this)
           throw new IllegalStateException(
                   "The grid contains a different actor at location "
                           + location + ".");
   
       grid.remove(location);
       grid = null;
       location = null;
   }
   ```

   ​	可见，如果已经被删除了，gird为空，想要再次删除一次的话，会直接抛出异常。

   ​	可以将自己加入、删除、再次加入，符合代码正常运行逻辑。

5. How can an actor turn 90 degrees to the right?

   ​	可以通过调用`setDirection`函数来让actor右转90度：setDirection(getDirection() + 90)，代码如下:

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Actor.java
   //@line:74~85
   /**
       * Sets the current direction of this actor.
       * @param newDirection the new direction. The direction of this actor is set
       * to the angle between 0 and 359 degrees that is equivalent to
       * <code>newDirection</code>.
       */
   public void setDirection(int newDirection)
   {
       direction = newDirection % Location.FULL_CIRCLE;
       if (direction < 0)
           direction += Location.FULL_CIRCLE;
   }
   ```

### Set 6

1. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

   ​	使用`isValid`函数，加入判断语句查看是否是有效的网格位置：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   //@line:98~99
   if (!gr.isValid(next))
       return false;
   ```

2. Which statement(s) in the canMove method determines that a bug will not walk into a rock?

   ​	通过判断其邻居的类型，如果为空或者为花朵才可以进行移动：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   //@line:100~101
   Actor neighbor = gr.get(next);
   return (neighbor == null) || (neighbor instanceof Flower);
   ```

3. Which methods of the Grid interface are invoked by the canMove method and why?

   ​	调用了`isValid`和`get`方法，`isValid`用以判断是否是有效网格点，`get`用以获取指定位置的Actor：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   //@line:98~101
   if (!gr.isValid(next))
       return false;
   Actor neighbor = gr.get(next);
   return (neighbor == null) || (neighbor instanceof Flower);
   ```

4. Which method of the Location class is invoked by the canMove method and why?

   ​	调用了`getAdjacentLocation`这种方法，`getAdjacentLocation`用以获取当前位置朝向的临近位置坐标：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   //@line:77
   Location next = loc.getAdjacentLocation(getDirection());
   ```

5. Which methods inherited from the Actor class are invoked in the canMove method?

   ​	调用了`getGrid`和`getLocation`以及`getDirection`三种方法：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   //@line:73~77
   Grid<Actor> gr = getGrid();
   if (gr == null)
   	return;
   Location loc = getLocation();
   Location next = loc.getAdjacentLocation(getDirection());
   ```

6. What happens in the move method when the location immediately in front of the bug is out of the grid?

   ​	调用`removeSelfFromGrid`方法，将虫子从网格中删除，代码如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   //@line:78~81
   if (gr.isValid(next))
   	moveTo(next);
   else
   	removeSelfFromGrid();
   ```

7. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

   ​	loc变量是有意义的，因为保存了虫子移动之前的位置，如果没有这么变量那么久无法获取移动之前的位置了。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   //@line:71~84
   public void move()
   {
   	// ...
       Location loc = getLocation();
       // ...
       flower.putSelfInGrid(gr, loc);
   }
   ```

8. Why do you think the flowers that are dropped by a bug have the same color as the bug?

   ​	因为在创建花的时候，调用了bug的`getColor`方法，所以花和虫子的颜色是一样的：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   //@line:82
   Flower flower = new Flower(getColor());
   ```

9. When a bug removes itself from the grid, will it place a flower into its previous location?

   ​	会的，因为在调用了函数`removeSelfFromGrid`之后，整个`move`函数并没有终止，所以后续创建花的操作还是会进行：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   //@line:71~82
   public void move()
   {
       // ...
       if (gr.isValid(next))
           moveTo(next);
       else
           removeSelfFromGrid();
       Flower flower = new Flower(getColor());
       flower.putSelfInGrid(gr, loc);
   }
   ```

10. Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

    ​	是第83行语句，这里的loc代表虫子之前的位置：

    ```java
    flower.putSelfInGrid(gr, loc);
    ```

    ​	整个调用过程：

    ```java
    //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
    //@line:71~84
    public void move()
    {
    	// ...
        Location loc = getLocation();
        // ...
        flower.putSelfInGrid(gr, loc);
    }
    ```

11. If a bug needs to turn 180 degrees, how many times should it call the turn method?

    ​	一次`turn`方法是转向45度，要转向180度需要调用四次`turn`方法

    ```java
    //@file:GridWorldCode\framework\info\gridworld\actor\Bug.java
    //@line:59~65
    /**
     * Turns the bug 45 degrees to the right without changing its  location.
     */
    public void turn()
    {
    	setDirection(getDirection() + Location.HALF_RIGHT);
    }
    ```

    