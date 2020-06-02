//63. 不同路径 II
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
//
//机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
//
//现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
//
//
//
//网格中的障碍物和空位置分别用 1 和 0 来表示。
//
//说明：m 和 n 的值均不超过 100。
//
//示例 1:
//
//输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//输出: 2
//解释:
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
package Week_06;

//UniquePaths 进阶版
//直接动态规划
public class UniquePathsWithObstacles {
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

    public static void main(String[] args) {
        int[][] a = {{0, 0}, {1, 1}, {0, 0}};
        int i = new UniquePathsWithObstacles().uniquePathsWithObstacles(a);
        System.out.println(i);
    }
}
