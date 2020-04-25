//239. 滑动窗口最大值
//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
//
//返回滑动窗口中的最大值。
//
//
//
//进阶：
//
//你能在线性时间复杂度内解决此题吗？
//
//
//
//示例:
//
//输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7]
//解释:
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
//提示：
//
//1 <= nums.length <= 10^5
//-10^4 <= nums[i] <= 10^4
//1 <= k <= nums.length
package Week_02;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow {
    // 仿照官方题解双端队列，主要的点是在队列中如和最快找到最大值，在这儿我们队列中存的是索引
    // 也可以存值，不过代码有少许不一样，且存索引更容易判断
    public int[] maxSlidingWindow(int[] nums, int k) {
//        也可以用其他双端队列如：ArrayDqueue
        LinkedList<Integer> dequeue = new LinkedList<>();
        int num = nums.length - k + 1;
        int idx = 0;
        int[] kArr = new int[num];
        // 最大值索引
        int maxIndex = 0;
// 循环数组O(N)
        for (int i = 0; i < nums.length; i++) {
//            第1步
            // 当最大值索引已经离当前索引超过k了，说明最大值索引不在当前窗口了。
            // 需要先移除
            if (i - maxIndex == k && !dequeue.isEmpty()) {
                dequeue.removeFirst();
            }
//            第2步
            // 进入一个数就将小于该数的数删除，保证队列有序，最大值在队列头部
            while (!dequeue.isEmpty() && nums[i] > nums[dequeue.getLast()]) {
                dequeue.removeLast();
            }
//            第3步
            // 将当前数加入队列
            dequeue.addLast(i);
//            第4步
            // 将当前窗口的最大值（即队列头部值的索引在数组中的值）赋给kArr
            // 将当前窗口的最大值索引（即队列头部值）更新maxIndex
            if (i >= k - 1) {
                kArr[idx++] = nums[dequeue.getFirst()];
                maxIndex = dequeue.getFirst();
            }
        }
        return kArr;
    }

//    类似第一种解法，不过这儿是存值，有些地方处理稍有不同
    public int[] maxSlidingWindow2(int[] nums, int k) {
        //    双端队列
        Deque<Integer> max = new ArrayDeque<>();
        int n = nums.length;
        if (n == 0) {
            return nums;
        }
        int result[] = new int[n - k + 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                if (max.peekFirst() == nums[i - k]) {
                    max.removeFirst();
                }
            }
            while (!max.isEmpty() && nums[i] > max.peekLast()) {
                max.removeLast();
            }

            max.addLast(nums[i]);
            if (i >= k - 1) {
                result[index++] = max.peekFirst();
            }
        }
        return result;
    }

//    暴力求解
    public int[] maxSlidingWindow3(int[] nums, int k) {
        // 暴力求解
        int num = nums.length - k + 1;

        int[] kArr = new int[num];
        int idx = 0;
        for (int i = 0; i < num; i++) {
            int max = nums[i];
            for (int j = i + 1; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            kArr[idx++] = max;
        }
        return kArr;
    }
}
