学习笔记



# 贪心算法

- 贪心算法是一种在每一步选择中都采取在当前状态下最好或最优（即最有 

  利）的选择，从而希望导致结果是全局最好或最优的算法。  

- 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不 

  能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行 

  选择，有回退功能。

- 最常见的贪心是从前往后，也有特殊的情况，比如从后往前。

# 二分查找

## 条件

- 单调
- 具有边界
- 具有index 

当符合这三个条件的时候可以考虑使用二分查找

## 模板

```java
private int find(int target,int[] nums){
	int left = 0;
    int right = nums.length - 1;
    
    while(left <= right) {
        int mid = (right + left)/2;
        if(nums[mid] = target){
            return mid;
        }else if(nums[mid] < target){
			left = mid + 1;
        }else{
			right = mid - 1;
        }
    }
    return -1;
}
```



# 二分法查找旋转数组旋转点

```java
private int getBreakPoint(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] <= nums[right]) return 0;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            //利用右边部分，如果中点值小于右边界，属于升序段，不需要再判断
            if (nums[mid] < nums[right]) {
                right = mid;
            }else {
                //中点值大于等于右边界，旋转点肯定在右边。
                left = mid + 1;
            }
        }
        return left;
    }
```

