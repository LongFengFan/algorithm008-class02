//198. 打家劫舍
//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
//
//给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
//
//示例 1:
//
//输入: [1,2,3,1]
//输出: 4
//解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。
//示例 2:
//
//输入: [2,7,9,3,1]
//输出: 12
//解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
package Week_06;

import java.util.Arrays;

public class Rob {
//    二刷
    //    0 / 1 不偷/ 偷
//    如果最开始想不到一维，定义为二维
//    dp[i][0] = Math.max(dp[i -1][0], dp[i -1][1])
//    dp[i][1] = Math.max(dp[i -1][1],dp[i -1][0] + nums[i])
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[][] dp = new int[nums.length][2];
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i -1][0], dp[i -1][1]);
            dp[i][1] = Math.max(dp[i -1][0],dp[i -1][0] + nums[i]);
        }
        return Math.max(dp[nums.length - 1][1],dp[nums.length - 1][0]);
    }

    //    一维
//    当前状态等于 Math.max(前一个状态加上当前不偷， 前两个状态+ 当前偷）
//     dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int rob = new Rob().rob(nums);
        System.out.println(rob);
    }
}
