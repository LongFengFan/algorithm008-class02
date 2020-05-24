//给定一个非负整数数组，你最初位于数组的第一个位置。
//
//数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
//判断你是否能够到达最后一个位置。
//
//示例 1:
//
//输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
//示例 2:
//
//输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/jump-game
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package Week_04;

import org.springframework.util.StopWatch;

import java.util.ArrayList;

public class CanJump {
    //    深度优先遍历
    public boolean canJump(int[] nums) {
        return recurse(0, nums);
    }

    boolean recurse(int level, int[] nums) {
        if (level >= nums.length - 1) {
            return true;
        }
        if (nums[level] > 0) {
            for (int i = level + 1; i <= level + nums[level]; i++) {
                boolean recurse = recurse(i, nums);
                if (recurse) {
                    return true;
                }
            }
        }
        return false;
    }

    //    贪心算法
    public boolean canJump2(int[] nums) {
//        边界情况
        if (nums.length <= 1) {
            return true;
        }
//        比较最大值
        int maxL = 0;
//        控制走到nums - 2位置，不走到最后一个位置。
        for (int i = 0; i < nums.length - 1; i++) {
            maxL = Math.max(maxL, i + nums[i]);
            if (maxL >= nums.length - 1) return true;
//            遇到0，且之前的最大值也只能到达该位置，无法往下走，返回false
            if (nums[i] == 0 && maxL - i == 0) {
                return false;
            }
        }
        return maxL >= nums.length - 1;
    }

    //    官方贪心
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
//            该条件很重要，确保遇到0，且之前的最大值也只能到达该位置，无法往下走
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
//        int[] array = {2, 3, 1, 1, 4, 5, 3, 2, 2, 1, 4, 6, 9, 2, 3, 1, 1, 4, 5, 3, 2, 2, 1, 4, 6, 9, 2, 3, 1, 1, 4, 5, 3, 2, 2, 1, 4, 6, 9, 2, 3, 1, 1, 4, 5, 3, 2, 2, 1, 4, 6, 9
//                , 2, 3, 1, 1, 4, 5, 3, 2, 2, 1, 4, 6, 9, 2, 3, 1, 1, 4, 5, 3, 2, 2, 1, 4, 6, 9, 2, 3, 1, 1, 4, 5, 3, 2, 2, 1, 4, 6, 9, 2, 3, 1, 1, 4, 5, 3, 2, 2, 1, 4, 6, 9};
//        CanJump canJump = new CanJump();
//
//        stopWatch.start("test canJump start...");
//        System.out.println(canJump.canJump(array));
//        stopWatch.stop();
//
//        stopWatch.start("test canJump2 start...");
//        System.out.println(canJump.canJump2(array));
//        stopWatch.stop();
//
//        stopWatch.start("test canJump3 start...");
//        System.out.println(canJump.canJump3(array));
//        stopWatch.stop();
//
//        System.out.println(stopWatch.prettyPrint());
        Thread.sleep(3000);

        stopWatch.start("1");
        ArrayList<Integer> users = new ArrayList(100000);
        for (int i = 0; i < 100000; ++i) {
            users.add(i);
        }
        stopWatch.stop();
        stopWatch.start("2");
        ArrayList<Integer> users2 = new ArrayList();
        for (int i = 0; i < 100000; ++i) {
            users.add(i);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());



    }
}
