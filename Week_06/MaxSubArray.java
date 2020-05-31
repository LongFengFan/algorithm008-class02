//53. 最大子序和
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
//示例:
//
//输入: [-2,1,-3,4,-1,2,1,-5,4],
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
//进阶:
//
//如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
//
//通过次数247,744提交次数483,182
package Week_06;

import java.util.Arrays;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
//        分治（重复子问题）
//        假如序列最后一个值为当前值 max[i] = Math.max(max[i - 1] , 0) + nums[i]

//        状态数组定义 dp[i]

//        dp方程  dp[i] = Math.max(dp[i - 1] , 0) + nums[i]
//        这儿dp可以直接复用nums，为了代码清晰，重新开了一个数组dp
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}
