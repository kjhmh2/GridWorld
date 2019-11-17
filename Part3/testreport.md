# Test Report

海明皓 17343037

### 转向测试

​	这部分主要是测试方向是否设置正确，我们设置了初始位置为(2,2)，初始方向为正东方向，然后依次调用`turn`方法来判断方向是否正确：

```java
public void testTurn() 
{
    ActorWorld world = new ActorWorld();
    Jumper jumper = new Jumper();
    world.add(new Location(2, 2), jumper);
    // set east
    jumper.setDirection(Location.EAST);
    assertEquals(Location.EAST, jumper.getDirection());
    jumper.turn();
    jumper.turn();
    // whether it is south
    assertEquals(Location.SOUTH, jumper.getDirection());
    jumper.turn();
    jumper.turn();
    // whether it is west
    assertEquals(Location.WEST, jumper.getDirection());
    jumper.turn();
    jumper.turn();
    // whether it is north
    assertEquals(Location.NORTH, jumper.getDirection());
}
```

### 移动测试

​	这部分主要是测试Jumper是否能够正确运行移动逻辑，主要包括以下情形：

- 没有障碍，跳两格

  ```java
  // test1
  jumper.act();
  assertEquals("(2, 4)", jumper.getLocation().toString());
  ```

- 前一方格有岩石，将跳过岩石

  ```java
  // test2
  world.add(new Location(ROCKXPOS, ROCKYPOS), new Rock());
  jumper.act();
  assertEquals("(2, 6)", jumper.getLocation().toString());
  ```

- 前一方格有Jumper，将转向45度

  ```java
  // test3
  Jumper jumper2 = new Jumper();
  world.add(new Location(JUMPERXPOS, JUMPERYPOS), jumper2);
  jumper.act();
  assertEquals(jumper.getDirection(), Location.SOUTHEAST);
  ```

- 前一方格有花朵，将跳过花朵

  ```java
  // test4
  jumper.moveTo(new Location(INITXPOS, INITYPOS));
  jumper.setDirection(Location.EAST);
  world.add(new Location(FLOWERXPOS, FLOWERYPOS), new Flower());
  jumper.act();
  assertEquals("(2, 4)", jumper.getLocation().toString());
  ```

- 前二方格有岩石，将转向45度

  ```java
  // test5
  world.add(new Location(ROCKXPOS2, ROCKYPOS2), new Rock());
  jumper.act();
  assertEquals(jumper.getDirection(), Location.SOUTHEAST);
  ```

- 前一方格越界，将转向45度

  ```java
  // test6
  jumper.moveTo(new Location(INITXPOS2, INITYPOS2));
  jumper.setDirection(Location.NORTH);
  jumper.act();
  assertEquals(jumper.getDirection(), Location.NORTHEAST);
  ```

- 前二方格越界，将转向45度

  ```java
  // test7
  jumper.moveTo(new Location(INITXPOS3, INITYPOS3));
  jumper.setDirection(Location.NORTH);
  jumper.act();
  assertEquals(jumper.getDirection(), Location.NORTHEAST);
  ```

### 测试结果

​	可全部通过测试：

![1](img/1.png)