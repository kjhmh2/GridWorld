# Part4

海明皓 17343037

### Set 7

The source code for the Critter class is in the critters directory

1. What methods are implemented in Critter?

   ​	实现的方法包括`act`、`getActors()`、`processActors(ArrayList<Actor> actors)`、`getMoveLocation()`、`selectMoveLocation(ArrayList<Location> locs)`、`makeMove(Location loc)`方法，代码如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:31~132
   public class Critter extends Actor
   {
       public void act()
       {
           // ...
       }
   
       public ArrayList<Actor> getActors()
       {
           // ...
       }
       
       public void processActors(ArrayList<Actor> actors)
       {
           // ...
       }
   
       public ArrayList<Location> getMoveLocations()
       {
           // ...
       }
   
       public Location selectMoveLocation(ArrayList<Location> locs)
       {
           // ...
       }
   
       public void makeMove(Location loc)
       {
           // ...
       }
   }
   ```

2. What are the five basic actions common to all critters when they act?

   ​	`getActors()`、`processActors(ArrayList<Actor> actors)`、`getMoveLocation()`、`selectMoveLocation(ArrayList<Location> locs)`、`makeMove(Location loc)`方法，代码如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:31~132
   public class Critter extends Actor
   {
       public void act()
       {
           // ...
       }
   
       public ArrayList<Actor> getActors()
       {
           // ...
       }
       
       public void processActors(ArrayList<Actor> actors)
       {
           // ...
       }
   
       public ArrayList<Location> getMoveLocations()
       {
           // ...
       }
   
       public Location selectMoveLocation(ArrayList<Location> locs)
       {
           // ...
       }
   
       public void makeMove(Location loc)
       {
           // ...
       }
   }
   ```

3. Should subclasses of Critter override the getActors method? Explain.

   ​	主要看子类是要实现何种逻辑，如果不从附近的网格寻找Actor的话就可以重新修改这个函数。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:49~59
   /**
       * Gets the actors for processing. Implemented to return the actors that
       * occupy neighboring grid locations. Override this method in subclasses to
       * look elsewhere for actors to process.<br />
       * Postcondition: The state of all actors is unchanged.
       * @return a list of actors that this critter wishes to process.
       */
   public ArrayList<Actor> getActors()
   {
       return getGrid().getNeighbors(getLocation());
   }
   ```

4. Describe the way that a critter could process actors.

   ​	可以吃掉Actors、改变Actors的颜色、改变Actors的方向和位置等等。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:61~78
   /**
       * Processes the elements of <code>actors</code>. New actors may be added
       * to empty locations. Implemented to "eat" (i.e. remove) selected actors
       * that are not rocks or critters. Override this method in subclasses to
       * process actors in a different way. <br />
       * Postcondition: (1) The state of all actors in the grid other than this
       * critter and the elements of <code>actors</code> is unchanged. (2) The
       * location of this critter is unchanged.
       * @param actors the actors to be processed
       */
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

5. What three methods must be invoked to make a critter move? Explain each of these methods.

   ​	首先通过`getMoveLocation`方法来找到附近可以到达的点，排除一些点已经被石头或者其他Actor占有：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:80~91
   /**
       * Gets a list of possible locations for the next move. These locations must
       * be valid in the grid of this critter. Implemented to return the empty
       * neighboring locations. Override this method in subclasses to look
       * elsewhere for move locations.<br />
       * Postcondition: The state of all actors is unchanged.
       * @return a list of possible locations for the next move
       */
   public ArrayList<Location> getMoveLocations()
   {
       return getGrid().getEmptyAdjacentLocations(getLocation());
   }
   ```

   ​	然后通过`selectMoveLocation`方法从空余的点中随机找到一个点来进行移动，如果找不到就返回原来的位置：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:80~91
   /**
       * Selects the location for the next move. Implemented to randomly pick one
       * of the possible locations, or to return the current location if
       * <code>locs</code> has size 0. Override this method in subclasses that
       * have another mechanism for selecting the next move location. <br />
       * Postcondition: (1) The returned location is an element of
       * <code>locs</code>, this critter's current location, or
       * <code>null</code>. (2) The state of all actors is unchanged.
       * @param locs the possible locations for the next move
       * @return the location that was selected for the next move.
       */
   public Location selectMoveLocation(ArrayList<Location> locs)
   {
       int n = locs.size();
       if (n == 0)
           return getLocation();
       int r = (int) (Math.random() * n);
       return locs.get(r);
   }
   ```

   ​	最后使用`makeMove`方法用选择好的点来进行移动，如果传入的参数为空则删除这个Critter：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:113~131
   /**
       * Moves this critter to the given location <code>loc</code>, or removes
       * this critter from its grid if <code>loc</code> is <code>null</code>.
       * An actor may be added to the old location. If there is a different actor
       * at location <code>loc</code>, that actor is removed from the grid.
       * Override this method in subclasses that want to carry out other actions
       * (for example, turning this critter or adding an occupant in its previous
       * location). <br />
       * Postcondition: (1) <code>getLocation() == loc</code>. (2) The state of
       * all actors other than those at the old and new locations is unchanged.
       * @param loc the location to move to
       */
   public void makeMove(Location loc)
   {
       if (loc == null)
           removeSelfFromGrid();
       else
           moveTo(loc);
   }
   ```

6. Why is there no Critter constructor?

   ​	如果没有写构造函数，那么Java会为其自动创建一个缺省的默认构造函数；又因为其继承自Actor，所以不写构造函数就意味着会为其调用Actor的构造函数，生成一个蓝色的，朝向正北方向的Critter：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Actor.java
   //@line:36~45
   /**
       * Constructs a blue actor that is facing north.
       */
   public Actor()
   {
       color = Color.BLUE;
       direction = Location.NORTH;
       grid = null;
       location = null;
   }
   ```

### Set 8

The source code for the ChameleonCritter class is in the critters directory

1. Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

   ​	因为Critter类的act函数调用了`getActors`,`processActors`,`makeMove`等等函数，而在ChameleonCritter中重写了`processActors`方法，所以不需要改变act也会实现变色的功能。

   ```java
   //@file:GridWorldCode\projects\critters\ChameleonCritter.java
   //@line:32~45
   /**
       * Randomly selects a neighbor and changes this critter's color to be the
       * same as that neighbor's. If there are no neighbors, no action is taken.
       */
   public void processActors(ArrayList<Actor> actors)
   {
       int n = actors.size();
       if (n == 0)
           return;
       int r = (int) (Math.random() * n);
   
       Actor other = actors.get(r);
       setColor(other.getColor());
   }
   ```

2. Why does the makeMove method of ChameleonCritter call super.makeMove?

   ​	在ChameleonCritter类中要重写的`makeMove`方法，只是在父类的基础上添加了设置方向这一语句，之后的内容和父类完全一样，所以可以直接调用父类的`makeMove`方法，实现了代码重用。

   ```java
   //@file:GridWorldCode\projects\critters\ChameleonCritter.java
   //@line:47~54
   /**
       * Turns towards the new location as it moves.
       */
   public void makeMove(Location loc)
   {
       setDirection(getLocation().getDirectionToward(loc));
       super.makeMove(loc);
   }
   ```

3. How would you make the ChameleonCritter drop flowers in its old location when it moves?

   ​	我们先记录Critter未移动的位置，再它移动后，再在之前的位置上留下一朵颜色相同的花朵即可。

   ​	重新修改`makeMove`方法如下所示：

   ```java
   //@file:GridWorldCode\projects\critters\ChameleonCritter.java
   //@line:47~54
   /**
       * Turns towards the new location as it moves.
       */
   public void makeMove(Location loc)
   {
       Location flowerLoc = getLocation();
       setDirection(getLocation().getDirectionToward(loc));
       super.makeMove(loc);
       // after ChameleonCritter moves
       if(!flowerLoc.equals(loc))
       {
           Flower f = new Flower(getColor());
           f.putSelfInGrid(getGrid(), flowerLoc);
       }
   }
   ```

4. Why doesn’t ChameleonCritter override the getActors method?

   ​	因为ChameleonCritter中的`getActors`函数与父类的功能相同，不必再次重写，这样能充分发挥代码的重用性：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:49~59
   /**
       * Gets the actors for processing. Implemented to return the actors that
       * occupy neighboring grid locations. Override this method in subclasses to
       * look elsewhere for actors to process.<br />
       * Postcondition: The state of all actors is unchanged.
       * @return a list of actors that this critter wishes to process.
       */
   public ArrayList<Actor> getActors()
   {
       return getGrid().getNeighbors(getLocation());
   }
   ```

5. Which class contains the getLocation method?

   ​	在Actor类中，实现了`getLocation`方法：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Actor.java
   //@line:97~105
   /**
    * Gets the location of this actor.
    * @return the location of this actor, or <code>null</code> if this actor is
    * not contained in a grid
   */
   public Location getLocation()
   {
       return location;
   }
   ```

   ​	所以Actor以及其所有子类Critter、ChameleonCritter等都具有这个方法。

6. How can a Critter access its own grid?

   ​	通过`getGrid`方法，这个方法在Actor中已经实现了，可以直接用：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Actor.java
   //@line:87~95
   /**
       * Gets the grid in which this actor is located.
       * @return the grid of this actor, or <code>null</code> if this actor is
       * not contained in a grid
       */
   public Grid<Actor> getGrid()
   {
       return grid;
   }
   ```

### Set 9

The source code for the CrabCritter class is reproduced at the end of this part of GridWorld.

1. Why doesn’t CrabCritter override the processActors method?

   ​	因为CrabCritter 中的`processActors`函数与父类Critter的功能相同，不必再次重写，这样能充分发挥代码的重用性：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:61~78
   /**
       * Processes the elements of <code>actors</code>. New actors may be added
       * to empty locations. Implemented to "eat" (i.e. remove) selected actors
       * that are not rocks or critters. Override this method in subclasses to
       * process actors in a different way. <br />
       * Postcondition: (1) The state of all actors in the grid other than this
       * critter and the elements of <code>actors</code> is unchanged. (2) The
       * location of this critter is unchanged.
       * @param actors the actors to be processed
       */
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

2. Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

   ​	CrabCritter先调用`getActors`方法，得到前方，左前方，右前方三个位置的网格的信息，检查这些位置是否为空，若不为空就加入ArrayList中：

   ```java
   //@file:GridWorldCode\projects\critters\CrabCritter.java
   //@line:39~57
   /**
       * A crab gets the actors in the three locations immediately in front, to its
       * front-right and to its front-left
       * @return a list of actors occupying these locations
       */
   public ArrayList<Actor> getActors()
   {
       ArrayList<Actor> actors = new ArrayList<Actor>();
       int[] dirs =
           { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
       for (Location loc : getLocationsInDirections(dirs))
       {
           Actor a = getGrid().get(loc);
           if (a != null)
               actors.add(a);
       }
   
       return actors;
   }
   ```

   ​	然后传入processActors中执行，将ArrayList中的Actor元素从网格中删除：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:61~78
   /**
       * Processes the elements of <code>actors</code>. New actors may be added
       * to empty locations. Implemented to "eat" (i.e. remove) selected actors
       * that are not rocks or critters. Override this method in subclasses to
       * process actors in a different way. <br />
       * Postcondition: (1) The state of all actors in the grid other than this
       * critter and the elements of <code>actors</code> is unchanged. (2) The
       * location of this critter is unchanged.
       * @param actors the actors to be processed
       */
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

   ​	并不会全部吃完附近的actors，那些不在前方，左前方，右前方的actors不会被吃掉。

3. Why is the getLocationsInDirections method used in CrabCritter?

   ​	`getLocationsInDirections`方法用以得到CrabCritter所要吃掉东西的三个位置：

   ```java
   //@file:GridWorldCode\projects\critters\CrabCritter.java
   //@line:49~54
   for (Location loc : getLocationsInDirections(dirs))
   {
       Actor a = getGrid().get(loc);
       if (a != null)
           actors.add(a);
   }
   ```

   ​	`getLocationsInDirections`方法还用来判断位置是否合法，将合法的位置返回用与下一步的执行。

   ```java
   //@file:GridWorldCode\projects\critters\CrabCritter.java
   //@line:67~69
   for (Location loc : getLocationsInDirections(dirs))
   	if (getGrid().get(loc) == null)
   		locs.add(loc);
   ```

4. If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

   ​	由于CrabCritter位置(3,4)且方向为正南方，能吃掉的方向是其前方，左前方，右前方三个位置：

   ```java
   //@file:GridWorldCode\projects\critters\CrabCritter.java
   //@line:47
   int[] dirs = {Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT};
   ```

   ​	所以在坐标上的位置分别是(4,4)、(4,3)和(4,5)

5. What are the similarities and differences between the movements of a CrabCritter and a Critter?

   ​	相似之处：两者的移动都具有随机性、一次移动距离都是一格。

   ​	不同之处：当周围的八领域都没有物体阻挡时，CrabCritter只会随机向左或向右移动，而Critter可以随机移动到附近的八个点；当CrabCritter无法移动时，会随机向左或向右转90度，而Critter已经无法移动（附近八个点都有物体）。

   ​	Critter的移动实现如下：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:113~131
       * Moves this critter to the given location <code>loc</code>, or removes
       * this critter from its grid if <code>loc</code> is <code>null</code>.
       * An actor may be added to the old location. If there is a different actor
       * at location <code>loc</code>, that actor is removed from the grid.
       * Override this method in subclasses that want to carry out other actions
       * (for example, turning this critter or adding an occupant in its previous
       * location). <br />
       * Postcondition: (1) <code>getLocation() == loc</code>. (2) The state of
       * all actors other than those at the old and new locations is unchanged.
       * @param loc the location to move to
       */
   public void makeMove(Location loc)
   {
       if (loc == null)
           removeSelfFromGrid();
       else
           moveTo(loc);
   }
   ```

   ​	CrabCritter的移动实现如下：

   ```java
   //@file:GridWorldCode\projects\critters\CrabCritter.java
   //@line:74~91
   /**
       * If the crab critter doesn't move, it randomly turns left or right.
       */
   public void makeMove(Location loc)
   {
       if (loc.equals(getLocation()))
       {
           double r = Math.random();
           int angle;
           if (r < 0.5)
               angle = Location.LEFT;
           else
               angle = Location.RIGHT;
           setDirection(getDirection() + angle);
       }
       else
           super.makeMove(loc);
   }
   ```

6. How does a CrabCritter determine when it turns instead of moving?

   ​	判断出传入的参数和其本身的位置一样时，说明此时其左边和右边都有障碍物，则会进行转向：

   ```java
   //@file:GridWorldCode\projects\critters\CrabCritter.java
   //@line:81~87
   if (loc.equals(getLocation()))
   {
       double r = Math.random();
       int angle;
       if (r < 0.5)
       	angle = Location.LEFT;
       else
       	angle = Location.RIGHT;
       setDirection(getDirection() + angle);
   }
   ```

7. Why don’t the CrabCritter objects eat each other?

   ​	在Critter基类中的`processActors`方法已经设定，只能获取非岩石和Critter的对象，CrabCritter又是直接继承了Critter的`processActors`而未作修改，所以自然不会吃掉同类。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\actor\Critter.java
   //@line:61~78
   /**
       * Processes the elements of <code>actors</code>. New actors may be added
       * to empty locations. Implemented to "eat" (i.e. remove) selected actors
       * that are not rocks or critters. Override this method in subclasses to
       * process actors in a different way. <br />
       * Postcondition: (1) The state of all actors in the grid other than this
       * critter and the elements of <code>actors</code> is unchanged. (2) The
       * location of this critter is unchanged.
       * @param actors the actors to be processed
       */
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

   