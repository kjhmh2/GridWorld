# Part2

海明皓 17343037

1. What is the role of the instance variable sideLength?

   `sideLength`定义了虫子在方格中最大可移动的距离，代码如下：

   ```java
   // @file:GridWorldCode\projects\boxBug\BoxBug.java
   // @line: 28
   // 定义
   private int sideLength;
   
   // @line:34~38
   // 初始化设置length
   public BoxBug(int length)
   {
       steps = 0;
       sideLength = length;
   }
   
   // @line: 45~49
   // 判断
   if (steps < sideLength && canMove())
   {
       move();
       steps++;
   }
   ```

2. What is the role of the instance variable steps?

   `steps`记录了虫子已经走过的距离，用以和`sideLength`相比较来判断是否继续前进，代码如下：

   ```java
   // @file:GridWorldCode\projects\boxBug\BoxBug.java
   // @line: 27
   // 定义
   private int steps;
   
   // @line: 45~55
   // 判断
   if (steps < sideLength && canMove())
   {
       move();
       steps++;
   }
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ```

3. Why is the turn method called twice when steps becomes equal to sideLength?

   我们先找到`trun()`方法的实现：

   ```java
   // @file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 59~65
   /**
       * Turns the bug 45 degrees to the right without changing its location.
       */
   public void turn()
   {
   	setDirection(getDirection() + Location.HALF_RIGHT);
   }
   ```

   这里只是转向了45度，从BoxBug的行为我们可以看出，走的是一个正方形的路线，所以我们需要转向90度，也就需要我们调用两次`turn`方法。

4. Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

   我们找到BoxBug类的定义如下：

   ```java
   // @file:GridWorldCode\projects\boxBug\BoxBug.java
   // @line: 25
   public class BoxBug extends Bug
   {
   	// ...
   }
   ```

   很明显，其实Bug的一个子类，在父类Bug中我们可以找到`Move`方法的实现：

   ```java
   // @file:GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 67~84
   /**
       * Moves the bug forward, putting a flower into the location it previously
       * occupied.
       */
   public void move()
   {
       Grid<Actor> gr = getGrid();
       if (gr == null)
       return;
       Location loc = getLocation();
       Location next = loc.getAdjacentLocation(getDirection());
       if (gr.isValid(next))
       moveTo(next);
       else
       removeSelfFromGrid();
       Flower flower = new Flower(getColor());
       flower.putSelfInGrid(gr, loc);
   }
   ```

   Bug类已经实现了`Move`，所以作为子类的BoxBug继承了并可以调用该方法。

5. After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

   会一直是一样的，这是因为BoxBug类只有在构造的时候会让用户输入`sideLength`是多少并进行初始化：

   ```java
   // @file:GridWorldCode\projects\boxBug\BoxBug.java
   // @line:34~38
   // 初始化设置length
   public BoxBug(int length)
   {
       steps = 0;
       sideLength = length;
   }
   ```

   `sideLength`又是私有变量：

   ```java
   // @file:GridWorldCode\projects\boxBug\BoxBug.java
   // @line: 28
   // 定义
   private int sideLength;
   ```

   且没有提供任何可以进行改变的函数，例如`setSideLength`这样的函数，所以会一直不变。

6. Can the path a BoxBug travels ever change? Why or why not?

   会改变，因为每次行进之前都需要判断，也就是`canMove`函数：

   ```java
   // @file:GridWorldCode\projects\boxBug\BoxBug.java
   // @line: 45~49
   // 判断
   if (steps < sideLength && canMove())
   {
       move();
       steps++;
   }
   ```

   假如前方有岩石这样的障碍物，虫子就不会再前进，会调转方向并将`step`设置成0：

   ```java
   // @file:GridWorldCode\projects\boxBug\BoxBug.java
   // @line: 50~55
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ```

7. When will the value of steps be zero?

   进行初始化的时候，会设置`step`的值为0：

   ```java
   // @file:GridWorldCode\projects\boxBug\BoxBug.java
   // @line:34~38
   // 初始化设置step
   public BoxBug(int length)
   {
       steps = 0;
       sideLength = length;
   }
   ```

   当虫子遇见了墙壁或者岩石等必须要转向的时候，`steps`也会变成0：

   ```java
   // @file:GridWorldCode\projects\boxBug\BoxBug.java
   // @line: 50~55
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ```

   