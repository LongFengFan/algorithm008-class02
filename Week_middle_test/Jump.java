package Week_middle_test;

public class Jump {
    //    1 dfs
    public int jump(int[] nums) {
        int min = Integer.MAX_VALUE;
        min = dfs(nums, 0, nums.length - 1, min, 0);
        return min;
    }

    private int dfs(int[] nums, int i, int length, int min, int count) {
        if (i >= length) {
            return Math.min(min, count);
        }
        for (int j = 1; j <= nums[i]; j++) {
            min = dfs(nums, i + j, length, min, count + 1);
        }
        return min;
    }

    //    贪心
    public int jump2(int[] nums) {
        int i = 0;
        int count = 0;
        while (i < nums.length - 1) {
            count++;
            int maxJ = i + 1;
            int currentMaxValue = nums[maxJ];
            int currLength = i + nums[i];
            if (currLength >= nums.length - 1) return count;
            for (int j = 2; j <= nums[i]; j++) {
                if (currentMaxValue < nums[i + j] + j - 1) {
                    maxJ = i + j;
                    currentMaxValue = nums[i + j] + j - 1;
                }
            }
            i = maxJ;

        }
        return count;
    }

    public static void main(String[] args) {
        int[] an = new int[4];
        int[] nums = {2, 3, 1, 1, 4};
        int jump = new Jump().jump2(nums);
        System.out.println(jump);
    }
}
