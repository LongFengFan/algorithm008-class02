//120. 三角形最小路径和
//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
//
//相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
//
//
//
//例如，给定三角形：
//
//[
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
//自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
//
//
//
//说明：
//
//如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
package Week_06;

import java.util.ArrayList;
import java.util.List;

public class MinimumTotal {
    //    动态规划 直接利用triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

    //    动态规划 假如不能改变原有triangle
    public int minimumTotal2(List<List<Integer>> triangle) {
//        先对数组最后一层转为数组
        Integer[] arr = triangle.get(triangle.size() - 1).toArray(new Integer[0]);
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                arr[j] = triangle.get(i).get(j) + Math.min(arr[j], arr[j + 1]);
            }
        }
        return arr[0];
    }

    //    动态规划 假如不能改变原有triangle
    public int minimumTotal3(List<List<Integer>> triangle) {
//        先对数组最后一层转为数组可以直接在循环里做  triangle.size() - 1的时候赋值
        int[] arr = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                arr[j] = triangle.get(i).get(j) + Math.min(arr[j], arr[j + 1]);
            }
        }
        return arr[0];
    }

    //    深度搜索 递归 时间复杂度O(2^n)
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]  找出递归公式 从上往下 看 f[i][j] = min(f[i + 1][j] + f[i + 1][j + 1]) + triangle[i][j]
    public int minimumTotal4(List<List<Integer>> triangle) {
        int maxLevel = triangle.size();
        return dfs(0, maxLevel, 0, triangle);
    }

    private int dfs(int level, int maxLevel, int j, List<List<Integer>> triangle) {
        if (level == maxLevel - 1) {
            return triangle.get(level).get(j);
        }
        int left = dfs(level + 1, maxLevel, j, triangle) + triangle.get(level).get(j);
        int right = dfs(level + 1, maxLevel, j + 1, triangle) + triangle.get(level).get(j);
        return Math.min(left, right) + triangle.get(level).get(j);
    }

    //        深度搜索 递归 记忆化
    public int minimumTotal5(List<List<Integer>> triangle) {
        int maxLevel = triangle.size();
        Integer[][] memo = new Integer[maxLevel][triangle.get(maxLevel - 1).size()];
        return dfs(0, maxLevel, 0, triangle, memo);
    }

    private Integer dfs(int level, int maxLevel, int j, List<List<Integer>> triangle, Integer[][] memo) {
        if (memo[level][j] != null) {
            return memo[level][j];
        }
        if (level == maxLevel - 1) {
            memo[level][j] = triangle.get(level).get(j);
            return memo[level][j];
        }
        int left = dfs(level + 1, maxLevel, j, triangle, memo) + triangle.get(level).get(j);
        int right = dfs(level + 1, maxLevel, j + 1, triangle, memo) + triangle.get(level).get(j);
        return memo[level][j] = Math.min(left, right);
    }
}
