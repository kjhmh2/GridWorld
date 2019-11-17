# Design Report

海明皓 17343037

a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

​	如果前面第一个格子为空，但第二个格子是花的情况下，jumper可以继续前进，覆盖并移除花朵；如果前面第一个格子是空的，但是第二个格子是岩石的情况下，jumper不可以继续前进，必须顺时针旋转45度再次判断能否继续前进。

b. What will a jumper do if the location two cells in front of the jumper is out of the grid?

​	如果jumper前面的第二个格子超出了网格范围，那么jumper不会向前跳，顺时针旋转45度再次判断能否继续前进。

c. What will a jumper do if it is facing an edge of the grid?

​	如果jumper面向墙壁，那么jumper不会向前跳，顺时针旋转45度再次判断能否继续前进。

d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

​	如果jumper前面的第二个格子是另外一个actor，那么jumper不会向前跳，顺时针旋转45度再次判断能否继续前进。

e. What will a jumper do if it encounters another jumper in its path?

​	如果jumper在其路径上遇见了另外一个jumper，那么jumper不会向前跳，顺时针旋转45度再次判断能否继续前进。

f. Are there any other tests the jumper needs to make?

​	需要测试jumper移动后，是否没有留下花朵；需要测试jumper能否正确转向。