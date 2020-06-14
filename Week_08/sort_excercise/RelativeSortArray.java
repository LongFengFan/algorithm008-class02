//1122. 数组的相对排序
//给你两个数组，arr1 和 arr2，
//
//arr2 中的元素各不相同
//arr2 中的每个元素都出现在 arr1 中
//对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
//
//
//
//示例：
//
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
//
//
//提示：
//
//arr1.length, arr2.length <= 1000
//0 <= arr1[i], arr2[i] <= 1000
//arr2 中的元素 arr2[i] 各不相同
//arr2 中的每个元素 arr2[i] 都出现在 arr1 中
package Week_08.sort_excercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RelativeSortArray {
//    常规解法，嵌套遍历得到前一段的值，剩下的一段用高级排序
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int k = 0;
        for (int i = 0; i < arr2.length; i++) {
            int current = arr2[i];
            for (int j = k; j < arr1.length; j++) {
                if (arr1[j] == current) {
                    swap(arr1, k, j);
                    k++;
                }
            }
        }
//堆排或者快排后面一段
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = k; i < arr1.length; i++) {
            heap.add(arr1[i]);
        }
        while (!heap.isEmpty()){
            arr1[k++] = heap.poll();
        }
        return arr1;
    }

//    计数排序，垃圾版
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i : arr2) {
            map.put(i,0);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : arr1) {
            if(map.containsKey(i)){
                map.put(i,map.get(i) + 1);
            }else {
                list.add(i);
            }
        }
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer k = entry.getKey();
            Integer v = entry.getValue();
            while (v != 0) {
                arr1[i] = k;
                i++;
                v--;
            }
        }
        list.sort(Comparator.naturalOrder());
        for (int j = 0; j < list.size(); j++) {
            arr1[i + j] = list.get(j);
        }
        return arr1;
    }

//计数排序，利用是整数的特性，直接用数组当做hash
    public int[] relativeSortArray3(int[] arr1, int[] arr2) {
        int max = Arrays.stream(arr1).max().getAsInt();
//        题目指定了0-1001，可以直接设定，也可以先计算最大值。发现先计算时间还耗费得更多
//        int[] arr = new int[1001];
        int[] arr = new int[max + 1];
        int[] res = new int[arr1.length];
        int index = 0;
        for (int item : arr1) arr[item]++;//统计arr1
        for (int item : arr2) {
            while(arr[item]-- > 0) {
                res[index] = item;
                ++index;
            }
        }
        for(int i = 0; i < arr.length; ++i) {
            while (arr[i]-- > 0){
                res[index] = i;
                ++index;
            }
        }
        return res;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}
