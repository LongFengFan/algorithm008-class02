//70. 爬楼梯
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
//每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
//注意：给定 n 是一个正整数。
//
//示例 1：
//
//输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//示例 2：
//
//输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
package Week_01;


import java.util.HashMap;

public class 爬楼梯 {
    //    暴力递归
//    时间复杂度：O(2^n)O(2
//n
// )，树形递归的大小为 2^n2
//n
//  空间复杂度：O(n)O(n)，递归树的深度可以达到 nn
    public int climbStairs(int n) {
        if (n < 3) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    //    由于递归树每次产生2倍的计算，而越到后面重复计算越多，因此我们可以记忆化递归
//    时间复杂度：O(n)O(n)，树形递归的大小可以达到 nn。
//空间复杂度：O(n)O(n)，递归树的深度可以达到 nn。
    public int climbStairs2(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            map.put(i, i);
        }
        return memClimb(n, map);
    }

    private int memClimb(int n, HashMap<Integer, Integer> map) {
        if (map.containsKey(n)) return map.get(n);
        if (!map.containsKey(n - 1)) {
            map.put(n - 1, memClimb(n - 1, map));
        }
        if (!map.containsKey(n - 2)) {
            map.put(n - 1, memClimb(n - 2, map));
        }
        return map.get(n - 1) + map.get(n - 2);
    }

    //    斐波那契数算法
//    时间复杂度：O(n)O(n)，单循环到 nn，需要计算第 nn 个斐波那契数。
//空间复杂度：O(1)O(1)，使用常量级空间。
    public int climbStairs3(int n) {
        if(n < 3) return n;
        int i = 1,j = 2,k = 3;
        for (int m = 3; m < n; m++) {
            k = i + j;
            i = j;
            j = m;
        }
        return k;
    }
//    官方动态规划
//    和上面一个方法比起来不同的是用了数组
//    时间复杂度：O(n)O(n)，单循环到 nn 。
//空间复杂度：O(n)O(n)，dpdp 数组用了 nn 的空间。
    public int climbStairs4(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


}
