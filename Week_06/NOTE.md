# 动态规划学习笔记

DP  dynamic programing(动态规划)  其实programing更多的是推导的意思，可以理解为动态递推

## 前置回顾

- 分治模板

```python
# Python
def divide_conquer(problem, param1, param2, ...):
   # recursion terminator
   if problem is None:
      print_result
      return
  # prepare data 分
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 

  # conquer subproblems 治（递归）
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …

  # process and generate the final result  合并
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # revert the current level states
```



动态规划、分治、递归并没有本质区别

总的来说感触是：

1. 找到最近最简方法，将其拆解成可重复解决的问题；
2. 数学归纳法思维（抵制人肉递归的诱惑；
3. 动态规划可以理解为动态递推，一般都可以由小的子问题(**下层**)递推得到大的问题(**上层结果**)；

关键点

1. 动态规划 和 递归或者分治 没有根本上的区别（关键看有无最优的子结构），如果没有最优子结构，每个子结构都是解，那可以理解为传统意义的分治。

2. **共性：找到重复子问题**

3. 差异性：最优子结构、中途可以**淘汰**次优解

实战例题

1. 斐波拉契数

   - 傻递归 O(2^n)

   ```java
   int fib(int n){
       return n <= 1 ? n : fib(n - 2) + fib(n - 1);
   }
   ```

   

   - 记忆化搜索  O(n)

   ```java
   int fib(int n, int[] memo){
       if(n <= 1){
           return n;
       }
       if(memo[n] == 0){
           memo[n] = fib(n - 2, memo) + fib(n - 1, memo);
       }
       return memo[n];
   }
   ```

   - 动态规划  与其用递归 + 缓存 的记忆化搜索，大部分情况下都可以转换为一个for 循环 递推公式

     即：bottom up，自底向上的思维模式，由下层推导出上层。**动态规划一般都会储存中间状态，比如下面的数组**；

   ```java
   #推导公式 F[n] = F[n-1] + F[n-2]
   int fib(int n)  {
   	 if(n <= 1){
          return n;
       }
       int[] a = new int[n];
       a[0] = 0, a[1] = 1; 
       for (int i = 2; i <= n; ++i) { 
        	a[i] = a[i-1] + a[i-2]; 
       }
       return a[n];
   }
   ```

   由于推导公式比较简单，可以直接简化为变量，节省空间复杂度。

   ```java
   int fib(int n){
       if(n <= 1){
          return n;
       }
       int i1 = 0;
       int i2 = 1;
       int i3
       for(int i = 2;i <= n; i++){
           i3 = i1 + i2;
           i1 = i2;
           i2 = i3;
       }
   }
   ```

   总结：

   > 按照三步走的方式：
   >
   > 1、分治，找出子问题，分析其推导公式，一般可以从前往后（从上到下）或者从后往前（从下到上）推导
   >
   > 2、定义状态空间，一般情况下就是数组，步骤1和2其实是关联的，状态数组用于状态移动时，数组当前位置可以由之前的位置推导出来。如果有边界条件或者其它分支，还需加上判断条件。数组可以是2维，有时可以简化为一维数组，有时可以再简化为几个变量进行递推。
   >
   > 3、定义dp方程，其实就和子问题推导公式差不多。

