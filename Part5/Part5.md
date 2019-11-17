# Part5

海明皓 17343037

### Set 10

The source code for the AbstractGrid class is in Appendix D.

1. Where is the isValid method specified? Which classes provide an implementation of this method?

   ​	`isValid `方法在Grid接口中被明确：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Grid.java
   //@line:43~50
   /**
       * Checks whether a location is valid in this grid. <br />
       * Precondition: <code>loc</code> is not <code>null</code>
       * @param loc the location to check
       * @return <code>true</code> if <code>loc</code> is valid in this grid,
       * <code>false</code> otherwise
       */
   boolean isValid(Location loc);
   ```

   ​	然后分别在BoundedGrid和UnboundedGrid类中分别进行了对应的实现：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:60~64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ```

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\UnboundedGrid.java
   //@line:53~56    
   public boolean isValid(Location loc)
   {
       return true;
   }
   ```

2. Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?

   ​	`getValidAdjacentLocations`方法直接调用了`isValid`方法来判断其位置是否合法，而其他函数没有直接调用`isValid`，而是通过调用`getValidAdjacentLocatons`来间接调用`isValid`来判断位置，此时`getValidAdjacentLocatons`传递的参数本来就已经合法了。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
   //@line:36~49
   public ArrayList<Location> getValidAdjacentLocations(Location loc)
   {
       // ...
       for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
       {
           // ...
           // Here
           if (isValid(neighborLoc))
               locs.add(neighborLoc);
           // ...
       }
       return locs;
   }
   ```

3. Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

   ​	`getNeighbors`方法调用了Grid接口中的`getOccupiedAdjacentLocations`以及`get`这两个方法。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
   //@line:28~34
   public ArrayList<E> getNeighbors(Location loc)
   {
       ArrayList<E> neighbors = new ArrayList<E>();
       for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
           neighbors.add(get(neighborLoc));
       return neighbors;
   }
   ```

   `getOccupiedAdjacentLocations`的实现在AbstractGrid类，而`get`的实现在BoundedGrid和UnboundedGrid类。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
   //@line:62~71
   public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
   {
       ArrayList<Location> locs = new ArrayList<Location>();
       for (Location neighborLoc : getValidAdjacentLocations(loc))
       {
           if (get(neighborLoc) != null)
               locs.add(neighborLoc);
       }
       return locs;
   }
   ```

4. Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

   ​	`get`的实现在UnboundedGrid类，如果位置为空时，则返回null；而如果位置有对象时，返回该对象，`getValidAdjacentLocations`调用该方法仅仅用来判断该位置是否有对象，若无则为空余位置。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
   //@line:51~60
   public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
   {
       ArrayList<Location> locs = new ArrayList<Location>();
       for (Location neighborLoc : getValidAdjacentLocations(loc))
       {
           if (get(neighborLoc) == null)
               locs.add(neighborLoc);
       }
       return locs;
   }
   ```

5. What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

   ​	若替换，那么`getValidAdjacentLocations`会仅仅返回东南西北这四个方向的合法空闲位置，而非附近的八领域。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
   //@line:36~49
   public ArrayList<Location> getValidAdjacentLocations(Location loc)
   {
       ArrayList<Location> locs = new ArrayList<Location>();
       int d = Location.NORTH;
       for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
       {
           Location neighborLoc = loc.getAdjacentLocation(d);
           if (isValid(neighborLoc))
               locs.add(neighborLoc);
           d = d + Location.HALF_RIGHT;
       }
       return locs;
   }
   ```

### Set 11

The source code for the BoundedGrid class is in Appendix D.

1. What ensures that a grid has at least one valid location?

   ​	构造函数；其要求rows与cols不能小于等于0，代表网格个数必须大于等于1：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:39~46
   public BoundedGrid(int rows, int cols)
   {
       if (rows <= 0)
           throw new IllegalArgumentException("rows <= 0");
       if (cols <= 0)
           throw new IllegalArgumentException("cols <= 0");
       occupantArray = new Object[rows][cols];
   }
   ```

2. How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

   ​	`getNumCols`方法中通过返回occupantArray[0].length来得到列。根据构造函数的定义，保证了行数一定大于0。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:53~58
   public int getNumCols()
   {
       // Note: according to the constructor precondition, numRows() > 0, so
       // theGrid[0] is non-null.
       return occupantArray[0].length;
   }
   ```

3. What are the requirements for a Location to be valid in a BoundedGrid?

   ​	通过`isValid`方法可知，位置的行列必须大于等于0且不能超过构造函数所构造的最大行列数：

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:60~64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ```

In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.

1. What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

   ​	`getOccupiedLocations`返回了一个Location类型的ArrayList，时间复杂度为O(r * c)。

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

2. What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

   ​	`get`函数的返回值是模板对象E，需要的参数为一个Location变量，时间复杂度为O(1)。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:85~91
   public E get(Location loc)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
       return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
   }
   ```

3. What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

   ​	当位置Location不合法或传入的obj为空时，该函数会抛出异常，时间复杂度为O(1)。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:93~105
   public E put(Location loc, E obj)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
       if (obj == null)
           throw new NullPointerException("obj == null");
   
       // Add the object to the grid.
       E oldOccupant = get(loc);
       occupantArray[loc.getRow()][loc.getCol()] = obj;
       return oldOccupant;
   }
   ```

4. What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

   ​	`remove`方法的返回类型为E；当从一个空余位置移动一个物体时，函数不会抛出异常，只是返回值为null；时间复杂度为O(1)。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
   //@line:107~117
   public E remove(Location loc)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
   
       // Remove the object from the grid.
       E r = get(loc);
       occupantArray[loc.getRow()][loc.getCol()] = null;
       return r;
   }
   ```

5. Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

   ​	从时间复杂度上来看，`remove`, `get`, `put`的时间复杂度都为O(1)，是最有效的实现，无需再改动，`getOccupiedLocations`时间复杂度为O(r*c)，这跟其所用的数据结构有关，如果换用其它数据结构储存对象，比如TreeMap、HashMap等，就不必用两次循环查找，可降低时间复杂度。

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

### Set 12

The source code for the UnboundedGrid class is in Appendix D.

1. Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

   ​	要在UnboundedGrid类中使用HashMap，Location类中实现了`hashCode`函数来生成唯一的hash对应码。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:214~221
   /**
    * Generates a hash code.
    * @return a hash code for this location
   */
   public int hashCode()
   {
       return getRow() * 3737 + getCol();
   }
   ```

   ​	还实现`equals`方法用以查找hash表对应的元素：

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

   ​	如果要使用TreeMap就需要实现接口Comparable，其需要实例化一个比较函数`compareTo`。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:223~246
   /**
    * Compares this location to <code>other</code> for ordering. Returns a
    * negative integer, zero, or a positive integer as this location is less
    * than, equal to, or greater than <code>other</code>. Locations are
    * ordered in row-major order. <br />
    * (Precondition: <code>other</code> is a <code>Location</code> object.)
    * @param other the other location to test
    * @return a negative integer if this location is less than
    * <code>other</code>, zero if the two locations are equal, or a positive
    * integer if this location is greater than <code>other</code>
   */
   public int compareTo(Object other)
   {
       Location otherLoc = (Location) other;
       if (getRow() < otherLoc.getRow())
           return -1;
       if (getRow() > otherLoc.getRow())
           return 1;
       if (getCol() < otherLoc.getCol())
           return -1;
       if (getCol() > otherLoc.getCol())
           return 1;
       return 0;
   }
   ```

   ​	给出的Location类实现了这三个函数，满足要求。

2. Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

   ​	在无边界网格中，无法判断位置是否合法。由于实现利用了HashMap，当位置为null时，返回值也会是null，仅表示该位置没有对象，而不是位置不合法，和无边界网格的定义不符合，所以必须要在这三个函数判断位置是否为null。

   ​	而在有边界网格中，若传入位置为null时，会在`isValid`方法中返回false，也就不会访问到这个位置，所以在有边界网格不需要在这三个函数判断位置是否为null。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\UnboundedGrid.java
   //@line:66~87
   public E get(Location loc)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       return occupantMap.get(loc);
   }
   
   public E put(Location loc, E obj)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       if (obj == null)
           throw new NullPointerException("obj == null");
       return occupantMap.put(loc, obj);
   }
   
   public E remove(Location loc)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       return occupantMap.remove(loc);
   }
   ```

3. What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

   ​	平均复杂度为O(1)；如果利用TreeMap来实现无边界网格，需要在一颗树中查找数据，所以平均复杂度变为O(log n)，n为树的节点数目，即网格中被占据的元素个数。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\UnboundedGrid.java
   //@line:66~87
   public E get(Location loc)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       return occupantMap.get(loc);
   }
   
   public E put(Location loc, E obj)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       if (obj == null)
           throw new NullPointerException("obj == null");
       return occupantMap.put(loc, obj);
   }
   
   public E remove(Location loc)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       return occupantMap.remove(loc);
   }
   ```

4. How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

   ​	如果利用TreeMap实现无边界网格，那么储存元素的方式以及储存元素的顺序会改变；TreeMap利用的是Location中的`compareTo`实现平衡二叉树，元素排列是由大到小，HashMap利用的哈希函数，即Location中`equal`以及`hashCode`两个函数，按照映射关系存储元素。

   ```java
   //@file:GridWorldCode\framework\info\gridworld\grid\Location.java
   //@line:223~246
   /**
    * Compares this location to <code>other</code> for ordering. Returns a
    * negative integer, zero, or a positive integer as this location is less
    * than, equal to, or greater than <code>other</code>. Locations are
    * ordered in row-major order. <br />
    * (Precondition: <code>other</code> is a <code>Location</code> object.)
    * @param other the other location to test
    * @return a negative integer if this location is less than
    * <code>other</code>, zero if the two locations are equal, or a positive
    * integer if this location is greater than <code>other</code>
   */
   public int compareTo(Object other)
   {
       Location otherLoc = (Location) other;
       if (getRow() < otherLoc.getRow())
           return -1;
       if (getRow() > otherLoc.getRow())
           return 1;
       if (getCol() < otherLoc.getCol())
           return -1;
       if (getCol() > otherLoc.getCol())
           return 1;
       return 0;
   }
   ```

5. Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

   ​	map也可以使用在有边界网格中，但如果元素特别多，用二维数组的效果会更好，因为当使用hashmap查找元素索引时，如果元素特别多，那么这个查找可能会重复多次，会非常浪费时间。

   