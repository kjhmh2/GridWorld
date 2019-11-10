# Part1

17343037 海明皓

### Set1

1. Does the bug always move to a new location? Explain.

   否，如果虫子周围八领域都是墙或者岩石或其他虫子等障碍物，会一直在原地转圈而不会移动。

2. In which direction does the bug move?

   如果虫子的前方是空的区域，则会直接向前走；如果虫子正对的方向有墙或者岩石或者其他虫子等障碍物，虫子会向右转45度，直到前方出现空区域位置。

3. What does the bug do if it does not move?

   虫子会一直向右转45度。

4. What does a bug leave behind when it moves?

   会留下一朵花，随着时间颜色逐渐变淡。

5. What happens when the bug is at an edge of the grid? (Consider whether the bug is facing the edge as well as whether the bug is facing some other direction when answering this question.)

   如果虫子面向墙壁，则虫子会向右转45度直到前方出现空地；如果虫子面向另外的方向且该方向前方是空地，虫子会向前走。

6. What happens when a bug has a rock in the location immediately in front of it?

   虫子会向右转45度，直到前方出现空地，然后就会往那个方向向前走。

7. Does a flower move?

   否，花并不会移动。

8. What behavior does a flower have?

   随着每一次的步进，花的颜色会从红色变成黑色。

9. Does a rock move or have any other behavior?

   岩石不会移动，没有其他的行为。

10. Can more than one actor (bug, flower, rock) be in the same location in the grid at the same time?

    否，在一个网格中，只有一个物体能够占据。

### Set2

By clicking on a cell containing a bug, flower, or rock, do the following.

1. Test the setDirection method with the following inputs and complete the table, giving the compass direction each input represents.

   | Degress | Compass Direction |
   | :-----: | :---------------: |
   |    0    |       North       |
   |   45    |     Northeast     |
   |   90    |       East        |
   |   135   |     Southeast     |
   |   180   |       South       |
   |   225   |     Southwest     |
   |   270   |       West        |
   |   315   |     Northwest     |
   |   360   |       North       |

2. Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?

   如果我们使用`moveTo`方法，我们可以让虫子移动到网格中的任何地方，并保持虫子朝向不变；如果该位置已经有物体，比如说有岩石、花、另一只虫子，会代替原来的物体；如果该位置不在网格中，`java.lang.illegalArgumentException`错误会被扔出

3. Change the color of a bug, a flower, and a rock. Which method did you use?

   可以使用物体的`setColor`方法

4. Move a rock on top of a bug and then move the rock again. What happened to the bug?

   当把岩石移到虫子的位置上，虫子会消失，只能看到岩石；再次移开岩石也不会出现虫子，虫子被岩石销毁了。