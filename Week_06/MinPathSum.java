//64. 最小路径和
//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
//说明：每次只能向下或者向右移动一步。
//
//示例:
//
//输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
package Week_06;

import java.util.Arrays;

public class MinPathSum {
//        分治（重复子问题）
//          f[i][j] = Math.min(f[i][j - 1], f[i - 1][j] ) + nums[i][j]
//     注意边界

//        状态数组定义 dp[i][j]

//        dp方程

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int k = grid[0].length;
        int[][] dp = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                    continue;
                }
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[n - 1][k - 1];
    }


    //    自创用构造横竖size + 1大小的数组来简化判断，不过构造的时候也会有边界条件还要累加，不好。
    public int minPathSum2(int[][] grid) {
        int n = grid.length + 1;
        int k = grid[0].length + 1;
        int[][] dp = new int[n][k];
        int max = 0;
        for (int i = 0; i < n - 2; i++) {
            dp[i + 2][0] = max += grid[i][0];
        }
        int max2 = 0;
        for (int i = 0; i < k - 2; i++) {
            dp[0][i + 2] = max2 += grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i - 1][j - 1];
            }
        }
        return dp[n - 1][k - 1];
    }

    //    优化dp状态数组为一维数组，因为实际上只需要知道上面一层的数，下面一层可以从左到右推导出来，每一层不断复用这个dp数组
    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = grid[i][j];
                } else if (i == 0) {
                    dp[j] = dp[j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[j] = dp[j] + grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }


    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int i = new MinPathSum().minPathSum(nums);
        System.out.println(i);
    }
}
