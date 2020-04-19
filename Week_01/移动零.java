//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针
package Week_01;

import java.util.stream.Stream;

//leetcode submit region begin(Prohibit modification and deletion)
class 移动零 {
    //    老师代码，双指针简洁形式
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    //    双指针
    public void moveZeroes2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }

        for (; j < nums.length; j++) {
            nums[j] = 0;
        }
    }

    public static void main(String[] args) {
        int[] ints = Stream.of(0, 1, 0, 3, 12).mapToInt(Integer::intValue).toArray();
        移动零 solution = new 移动零();
        solution.moveZeroes2(ints);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

