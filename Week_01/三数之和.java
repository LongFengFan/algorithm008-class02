//15. 三数之和
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
// 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
//
//注意：答案中不可以包含重复的三元组。
//
//
//
//示例：
//
//给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
package Week_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class 三数之和 {
    //    可以转换为两数之和
// hash 存值，降低为O(n^2)的时间复杂度
    public List<List<Integer>> threeSum(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(-nums[i], i);
        }
        HashSet<List<Integer>> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int key = nums[i] + nums[j];
                Integer value = map.get(key);
                if (map.containsKey(key) && value != i && value != j) {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], -key);
                    list.sort(Comparator.naturalOrder());
                    set.add(list);
                }
            }
        }
        return new ArrayList<>(set);
    }

    //    双指针逼近，很多顺序的题可以用双指针
    public List<List<Integer>> threeSum2(int[] nums) {
//         因此需要先排序
//        默认api，升序
        Arrays.sort(nums);
//        注意：如果需要改变默认的排列方式，不能使用基本类型（int，char等）定义变量，而应该用对应的类
//        降序排列方法
        //        注意这里是Integer，不是int
//        Integer[] arr={9,8,7,6,5,4,3,2,1};
//        Arrays.sort(arr, Collections.reverseOrder());
        int n = nums.length - 1;
        int left = 0, middle = 1, right = nums[n];


    }
}
