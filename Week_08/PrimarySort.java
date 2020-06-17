package Week_08;

import java.util.Arrays;
import java.util.Collections;

//初级排序算法 O(n2)
public class PrimarySort {
    //    选择排序
    public void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
//            每次遍历出从当前位置开始的最小值
            int minIndex = i;
            int min = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
//            替换最小值到当前位置
            nums[minIndex] = nums[i];
            nums[i] = min;
        }
    }

    //    插入排序
    public void insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int prev = i - 1;
            while (prev >= 0 && nums[prev] > current) {
                nums[prev + 1] = nums[prev];
                prev--;
            }
            nums[prev + 1] = current;
        }
    }

    //    冒泡排序，实际中基本不用
//   嵌套循环，每次循环都是比较相邻的元素，逆序的化就交换顺序
//    一次完全循环后，最大值就在最后面
//    二次循环后，次最大值在倒数第二。。。。
    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if(nums[j] > nums[j + 1]){
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }

//    总结
//    基本可以认为选择排序和冒泡排序是差不多的，都是一次循环后把最大或者最小值找到
    public static void main(String[] args) {
        int[] nums = {1, 4, 5, 2, 6, 3};
        new PrimarySort().bubbleSort(nums);
        String s = Arrays.toString(nums);
        System.out.println(s);
    }
}
