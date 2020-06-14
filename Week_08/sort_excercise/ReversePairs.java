//493. 翻转对
//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//
//你需要返回给定数组中的重要翻转对的数量。
//
//示例 1:
//
//输入: [1,3,2,3,1]
//输出: 2
//示例 2:
//
//输入: [2,4,3,5,1]
//输出: 3
//注意:
//
//给定数组的长度不会超过50000。
//输入数组中的所有数字都在32位整数的表示范围内。
package Week_08.sort_excercise;

import java.util.Arrays;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int lCount = mergeSort(nums, left, mid);
        int rCount = mergeSort(nums, mid + 1, right);
        int merge = merge(nums, left, mid, right);
        return lCount + rCount + merge;
    }

    //    如果要进行排序的归并的话，while中就可以加上一个条件尽早结束，超时，取巧的归并Arrays.sort时间复杂度为logN
    private int merge(int[] nums, int left, int mid, int right) {
        int k = 0;
// 计算
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
//            因为是顺序的如果第一个j不成立，那么后面的j都不成立
            while (j <= right && nums[i] / 2.0 > nums[j]) {
                j++;
            }
//            因为是顺序的，当下一轮i过来的时候如果j已经大于了right，那么直接就计算这个i的逆序对为j - (mid + 1);
            k += j - (mid + 1);
        }
        // 归并
        Arrays.sort(nums, left, right + 1);
        return k;
    }

    //    如果不想归并也是可以的，只是要把每次的j都要从mid + 1开始判断，且要把j++判断完，因为不是顺序的，这样会超时
    private int merge2(int[] nums, int left, int mid, int right) {
        int k = 0;
// 计算
        for (int i = left; i <= mid; i++) {
            int j = mid + 1;
            while (j <= right) {
                if (nums[i] / 2.0 > nums[j]) {
                    k++;
                }
                j++;

            }
        }
        // 不归并归并
//        Arrays.sort(nums, left, right + 1);
        return k;
    }

    // 正宗的归并O(N),和原始的归并排序相同，借助辅助空间
    private int merge3(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
//        计算逆序对的值
        int k = 0;
// 计算逆序对时while使用
        int i = left;
        int j = mid + 1;
//归并while使用
        int t = 0;
        int p = mid + 1;
        for (; i <= mid; i++) {
//            因为是顺序的如果第一个j不成立，那么后面的j都不成立
            while (j <= right && nums[i] / 2.0 > nums[j]) {
                j++;
            }
//            因为是顺序的，当下一轮i过来的时候如果j已经大于了right，那么直接就计算这个i的逆序对为j - (mid + 1);
            k += j - (mid + 1);

//            归并1
//            先把右半部分小于当前nums[i]的拿过来
            while (p <= right && nums[i] >= nums[p]) {
                tmp[t++] = nums[p++];
            }
//            加上当前nums[i]
            tmp[t++] = nums[i];
        }
        // 归并剩余的右边部分的是否有漏网之鱼
        while (p <= right) {
            tmp[t++] = nums[p++];
        }
        System.arraycopy(tmp, 0, nums, left, tmp.length);
        return k;
    }

}
