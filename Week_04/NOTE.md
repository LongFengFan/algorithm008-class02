学习笔记



# 贪心算法

- 贪心算法是一种在每一步选择中都采取在当前状态下最好或最优（即最有 

  利）的选择，从而希望导致结果是全局最好或最优的算法。  

- 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不 

  能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行 

  选择，有回退功能。

- 最常见的贪心是从前往后，也有特殊的情况，比如从后往前。

# 二分查找

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

- 最常见的是