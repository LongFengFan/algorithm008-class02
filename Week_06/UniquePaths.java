//62. 不同路径
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
//
//机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
//
//问总共有多少条不同的路径？
//
//
//
//例如，上图是一个7 x 3 的网格。有多少可能的路径？
//
//
//
//示例 1:
//
//输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
//示例 2:
//
//输入: m = 7, n = 3
//输出: 28
//
//
//提示：
//
//1 <= m, n <= 100
//题目数据保证答案小于等于 2 * 10 ^ 9
package Week_06;

public class UniquePaths {
    //    1. 傻递归 超时
    public int uniquePaths(int m, int n) {
        return dfs(m - 1, n - 1, 0, 0);
    }

    private int dfs(int m, int n, int i, int j) {

        if (i == m || j == n) {
            return 1;
        }
        int k1 = 0, k2 = 0;
        if (i < m) {
            k1 = dfs(m, n, i + 1, j);
        }
        if (j < n) {
            k2 = dfs(m, n, i, j + 1);
        }
        return k1 + k2;
    }

    //    2. 记忆化
    public int uniquePaths2(int m, int n) {
        int[][] memo = new int[m][n];
        return dfs(m - 1, n - 1, 0, 0, memo);
    }

    private int dfs(int m, int n, int i, int j, int[][] memo) {
        if (i == m || j == n) {
            return 1;
        }
        int k1 = 0, k2 = 0;
        if (i < m) {
            if (memo[i + 1][j] == 0) {
                memo[i + 1][j] = dfs(m, n, i + 1, j, memo);
            }
            k1 = memo[i + 1][j];
        }
        if (j < n) {
            if (memo[i][j + 1] == 0) {
                memo[i][j + 1] = dfs(m, n, i, j + 1, memo);
                ;
            }
            k2 = memo[i][j + 1];
        }
        return k1 + k2;
    }

//   动态规划 动态递推
//        递推公式
//        f[i][0] = 1; f[0][j] = 1
//        f[i][j] = f[i -1][j] + f[i][j - 1]
    public int uniquePaths3(int m, int n) {
        int[][] a = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    a[j][i] = 1;
                    continue;
                }
                a[j][i] = a[j - 1][i] + a[j][i - 1];
            }
        }
        return a[m - 1][n - 1];
    }

    public int uniquePaths4(int m, int n) {
//        可以再转化为一维数组，由最底层一层一层网上递推。
        int[] a = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    a[j] = 1;
                    continue;
                }
                a[j] = a[j] + a[j - 1];
            }
        }
        return a[m - 1];

    }

    public static void main(String[] args) {
        int i = new UniquePaths().uniquePaths(3, 2);
        System.out.println(i);
    }
}
