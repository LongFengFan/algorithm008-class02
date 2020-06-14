package Week_08;

import java.util.Arrays;
import java.util.PriorityQueue;

//高级排序算法 O
public class SeniorSort {

    //    用到了分治的思想 O(NlogN)
//    数组取标杆 pivot(可以是任意数组中的值)，将小元素放 pivot左边，大元素放右侧，然后依次
//对右边和右边的子数组继续快排；以达到整个序列有序。
    public void quickSort(int[] nums) {
        int begin = 0, end = nums.length - 1;
        quickSort(nums, begin, end);
    }

    private void quickSort(int[] nums, int begin, int end) {
        if (begin >= end) return;
        int pivot = partition(nums, begin, end);
        quickSort(nums, begin, pivot - 1);
        quickSort(nums, pivot + 1, end);
    }

    private int partition(int[] nums, int begin, int end) {
//        标杆位置
//        选择最后一个值为标杆
        int pivot = end;
//       begin开始 +  小于标杆的个数 即最后counter会到达真实的pivot位置
        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, counter);
                counter++;
            }
        }
//        到此时已经把counter前的数都小于nums[pivot]了
//        这时候把标杆挪到counter位置，并返回
        swap(nums, pivot, counter);
        return counter;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }


    //    归并排序 思想和快排有些相反
//    也是用到分治的思想
//	 1. 把长度为n的输入序列分成两个长度为n/2的子序列；
//	 2. 对这两个子序列分别采用归并排序；
//	 3. 将两个排序好的子序列合并成一个最终的排序序列。
//    快排是直接在一个整体上左右分好序，再左右分，从大到小； 归并是由左右俩小的各自排好序，再合成一个大的。
    public void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right + left) >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
//       申请额外数组
        int[] tmp = new int[right - left + 1];
//        i 左边开始下标 j 右边开始下标 k,插入额外数组下标
        int i = left, j = mid + 1, k = 0;
//        合并两个有序数组 或者链表的模板，while 三段式
        while (i <= mid && j <= right){
            tmp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid){
            tmp[k++] = nums[i++];
        }
        while (j <= right){
            tmp[k++] = nums[j++];
        }
//        合并到额外数组后，赋值给原数组
//        for (int p = 0; p < tmp.length; p++) {
//            nums[left + p] = tmp[p];
//        }
//        也可以用
        System.arraycopy(tmp,0,nums,left,tmp.length);
    }

//堆排序
//    堆插入 O(logN)，取最大/小值 O(1)
//    1. 数组元素依次建立小顶堆
// 2. 依次取堆顶元素，并删除
    public void heapSort(int[] nums) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
        }
        int k = 0;
        while (!heap.isEmpty()){
            nums[k++] = heap.poll();
        }
    }

    //    总结
//    基本可以认为选择排序和冒泡排序是差不多的，都是一次循环后把最大或者最小值找到
    public static void main(String[] args) {
        int[] nums = {1, 4, 5, 2, 6, 3};
        new SeniorSort().heapSort(nums);
        String s = Arrays.toString(nums);
        System.out.println(s);
    }
}
