//33. 搜索旋转排序数组
//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//
//( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
//
//搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
//
//你可以假设数组中不存在重复的元素。
//
//你的算法时间复杂度必须是 O(log n) 级别。
//
//示例 1:
//
//输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
//示例 2:
//
//输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1
package Week_04;


public class SearchInRotatedSortedArray {
    //    二分查找条件
//- 单调
//- 具有边界
//- 具有index
//    直接二分法
//    虽然不是完全单调，但是可以看成是单调的，只是条件要泛化，要比较mid left right、target之间关系
//    如果target 不在left mid之间 那么就在mid + 1 , right之间 -> left = mid + 1;
//    否则right = mid
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
//            1、左半截单调递增，nums[left] < nums[mid] ，target
            if (nums[left] <= nums[mid] && (target > nums[mid] || target < nums[left])) {
                left = mid + 1;
            }
//                2、左半截有旋转位
            else if (target < nums[left] && target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
//        最后会只保留一个数
        return left == right && target == nums[left] ? left : -1;
    }

    public static void main(String[] args) {
        int[] ints = {3, 5, 1};
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        int search = s.search2(ints, 1);
        System.out.println(search);
        System.out.println(s.getBreakPoint(ints));
    }


//    先二分找到旋转点，再分别看在左边还是右边用二分
    public int search2(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int breakPoint = getBreakPoint(nums);
        if (breakPoint == 0) return getTargetPoint(nums, target, 0, nums.length - 1);
        if (target >= nums[0] && target <= nums[breakPoint - 1]) {
            return getTargetPoint(nums, target, 0, breakPoint - 1);
        } else if (target >= nums[breakPoint] && target <= nums[nums.length - 1]) {
            return getTargetPoint(nums, target, breakPoint, nums.length - 1);
        } else {
            return -1;
        }
    }

    private int getTargetPoint(int[] nums, int target, int left, int right) {
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private int getBreakPoint(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] <= nums[right]) return 0;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

}
