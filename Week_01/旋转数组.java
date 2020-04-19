/*
给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释:
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
说明:

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
要求使用空间复杂度为 O(1) 的 原地 算法。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
package Week_01;


public class 旋转数组 {

    //使用额外数组
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] numsk = new int[k];
        int j = 0;
//        额外数组
        for (int i = nums.length - k; i < nums.length; i++) {
            numsk[j++] = nums[i];
        }
        j = nums.length - 1;
//        从nums.length - k - 1位置前统一移动k个位置
        for (int i = nums.length - k - 1; i >= 0; i--) {
            nums[j--] = nums[i];
        }
//        额外赋值
        for (int i = 0; i < numsk.length; i++) {
            nums[i] = numsk[i];
        }
    }

    //    暴力求解
    public void rotate2(int[] nums, int k) {
        if (nums.length < 2) return;
        int rotate = k % nums.length;
        int j = 0;
        for (int i = nums.length - rotate; i < nums.length; i++) {
            swap(nums, j, i);
            j++;
        }
    }

    private void swap(int[] nums, int j, int i) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
