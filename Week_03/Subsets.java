//78. 子集
//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
//说明：解集不能包含重复的子集。
//
//示例:
//
//输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//]
//通过次数84,065提交次数108,947
package Week_03;


import java.util.ArrayList;
import java.util.List;

public class Subsets {
    private List<List<Integer>> lists = new ArrayList<>();

    //    递归法，和找出所有的有效括号题类似，当时我们的第一步思路是：
//    可以每一次选左括号，也可以选右括号，将所有括号组合穷举出来
//    类推到本题，我们可以每一次不选该值，也可以选该值加入到list，则可以穷举到所有。
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> list = new ArrayList<>();

        helper(0, nums, list);
        return lists;
    }

    //    不用reverse state
    private void helper(int index, int[] nums, List<Integer> list) {
        //    terminator
        if (index == nums.length) {
//            因为list每次都是独立的新的，所以可以直接add
            lists.add(list);
            return;
        }

//        1、不选择该index 加入list
//        每次到下一层用新的list，并把之前的数据复制过去
        helper(index + 1, nums, new ArrayList<>(list));

//        2、选择该index 加入list
        list.add(nums[index]);
//        每次到下一层用新的list，并把之前的数据复制过去
        helper(index + 1, nums, new ArrayList<>(list));
    }
//    reverse state
//    当用了新的list到下一层就不用revers state

    //    最后需要用reverse state，其实大多数情况都不会用到
    private void helper2(int index, int[] nums, List<Integer> list) {
        //    terminator
        if (index == nums.length) {
//            因为list是同一个引用，到达teminator后必须复制为新的
//            所以加一个new ArrayList(List) 包装一下
            lists.add(new ArrayList<>(list));
            return;
        }

//        1、不选择该index 加入list
        helper(index + 1, nums, list);

//        2、选择该index 加入list
        list.add(nums[index]);
        helper(index + 1, nums, list);
        //    reverse state
//        确保当前层个数，不让进入下一层的数据在情况2增加一个元素后影响当前层，所以需要remove 1个尾部元素。
        list.remove(list.size() - 1);
    }

    //    迭代法
//    [1,2,3] -> [] -> [],[1] -> [],[1],[2],[1,2]
//    每次到当前数字就和前面所有的进行组合
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> onceLoopList = new ArrayList<>();
        lists.add(new ArrayList<>());
        for (int num : nums) {
            for (List<Integer> eachOne : lists) {
                ArrayList<Integer> newOne = new ArrayList<>(eachOne);
                newOne.add(num);
                onceLoopList.add(newOne);
            }
            lists.addAll(onceLoopList);
            onceLoopList.clear();
        }
        return lists;
    }

}
