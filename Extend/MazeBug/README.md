# MazeBug

海明皓 17343037

## 实验简介

​	无环路迷宫在数据结构上表现为一棵树，采用深度优先搜索算法就可以走出迷宫。本实验要求在改进的Grid World软件装置中实现深度优先搜索算法，从而使虫子走出迷宫。

## 算法实现

​	要实现深度搜索的算法，我们必须要有一个数据结构来存储走过的路径，在虫子走到死胡同的时候需要进行回溯，然后从分叉路口选择另外一条路径走。在基础的代码中，已经给我们提供了一个数据结构：

```java
private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
```

​	这是一个保存ArraryList的栈，在我们判断可以继续往前走的时候，我们需要将当前的位置入栈：

```java
ArrayList<Location> parent = crossLocation.pop();
parent.add(getLocation());
crossLocation.push(parent);
ArrayList<Location> present = new ArrayList<Location>();
present.add(getLocation());
crossLocation.push(present);
```

​	这样，我们使用了一个parent和一个present，使得每个ArraryList都保存了当前的位置和下一步的位置，在进行回溯的时候，我们首先将栈顶元素弹出，再取栈顶的ArraryList的第一个元素，就可以获取之前的位置：

```java
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

        // ...

        // set location
        last = getLocation();
        next = parentLocation;

        // move
        move();
        stepCount ++;
    }
}
```

​	本实验还要求我们增加方向的概率估计，我们的思路是使用一个数组来保存虫子走过四个方向的次数，在选择方向的时候，次数越大的方向选择的概率越高：

```java
// Randomly choose
int sum = 0;
for (int i = 0; i < 4; ++ i)
{
    sum += probability[i];
}
int random = (int) (Math.random() * sum);
```

​	注意在回溯的过程中，需要减少相对应的次数，是按照前进方向的反方向来减少次数的：

```java
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
```