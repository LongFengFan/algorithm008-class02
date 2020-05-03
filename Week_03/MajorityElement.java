//169. 多数元素
//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
//
//你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//
//
//
//示例 1:
//
//输入: [3,2,3]
//输出: 3
//示例 2:
//
//输入: [2,2,1,1,1,2,2]
//输出: 2
package Week_03;


import java.util.Arrays;
import java.util.HashMap;

public class MajorityElement {

    //    1,暴力法，利用map
    public int majorityElement(int[] nums) {
        Integer maxCount = 0;
        int maxNumber = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer before = map.get(num);
            map.put(num, before == null ? 1 : before + 1);
            Integer after = map.get(num);
            if (after > maxCount) {
                maxCount = after;
                maxNumber = num;
                if (maxCount > nums.length / 2) return maxNumber;
            }
        }
//        题目假设总是存在多数元素，这儿就不判断了。
        return maxNumber;
    }

    //    排序
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        int before = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == before) {
                count++;
                if (count > nums.length / 2) return nums[i];
            } else {
                before = nums[i];
            }
        }
        return nums[0];
    }

    //    排序2,排序后不用再遍历，下标为n/2 的一定是众数，因为众数个数> n/2，一定包括下标n/2
//    可以用奇数个数和偶数个数查看
    public int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //    分治递归
//    众数a个数大于n/2 那么将nums分为两半后，必定有一半的众数为a,我们挑选出这正确的一半。
    public int majorityElement4(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }
    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }


}
