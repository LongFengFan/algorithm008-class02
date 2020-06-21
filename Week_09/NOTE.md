学习笔记

# 高级动态规划

高级动态规划，状态数组一般会上升刀二维

大致的模板为

```java
function DP(): 
 dp = [][] # ⼆维情况 
 for i = 0 .. M { 
 for j = 0 .. N { 
 dp[i][j] = _Function(dp[i’][j’]…) 
 } 
 } 
 return dp[M][N];
```

关键是找到其重复子问题，而且状态方程不一定是一个，可能要根据条件变化。

比如打家劫舍这道题:

状态转移方程因为有偷和不偷的区别，用升了一个维度维0和1区别

因而状态方程也就要考虑不同时候

dp\[i][0] = max(dp\[i - 1][0], dp\[i - 1][1]);

dp\[i][1] = dp\[i - 1][0] + nums\[i];

不同路径2状态转移方程及代码：

```
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        高
        int n = obstacleGrid.length;
//        长
        int m = obstacleGrid[0].length;
        if (obstacleGrid[n - 1][m - 1] == 1) return 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                    continue;
                }
                if (i == n - 1 && j == m - 1) {
                    obstacleGrid[i][j] = 1;
                    continue;
                }
                if (i == n - 1) {
                    obstacleGrid[i][j] = obstacleGrid[i][j + 1];
                    continue;
                }
                if (j == m - 1) {
                    obstacleGrid[i][j] = obstacleGrid[i + 1][j];
                    continue;
                }
                obstacleGrid[i][j] = obstacleGrid[i + 1][j] + obstacleGrid[i][j + 1];
            }
        }
        return obstacleGrid[0][0];
    }
```

