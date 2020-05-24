package Week_middle_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) return Collections.emptyList();
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length - 1] < 0) return Collections.emptyList();
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        HashSet<List<Integer>> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(target - nums[i], i);
        }
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int key = nums[i] + nums[j] + nums[k];
                    Integer value = map.get(key);
                    if (map.containsKey(key) && value != i && value != j && value != k) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], nums[value]);
                        list.sort(Comparator.naturalOrder());
                        set.add(list);
                    }
                }
            }

        }
        return new ArrayList<>(set);
    }
}
