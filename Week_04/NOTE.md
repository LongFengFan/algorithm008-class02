学习笔记
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

