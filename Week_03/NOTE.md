学习笔记



# 算法数据结构好的文章

- [数据结构算法高效学习方法](https://gitee.com/BruceCat/fucking-algorithm/blob/master/README.md#目录)

- [labuladong的算法小抄](https://labuladong.gitbook.io/algo/)

# 递归

## 递归模板

```java
recurse(int level,int maxLevel,int param){
// teminator
    if(level < maxLevel){
        //process result
        return;
    }
    //process current logic
    //dirll down
    recurse(level + 1,maxLevel,newParam);
    
    //reverse state
    
}
```



# 树的递归

- 前序遍历：根左右

```java
//模板：
public List<Integer> preOrder(TreeNode root) {
    doLogic(root);
    preOrder(root.left);
    preOrder(root.right);
}
//  模板是简化版，如果要判断空那些就要加上条件
```

- 中序遍历：左根右，中序遍历在搜索二叉树BST中是升序遍历

```java
public List<Integer> preOrder(TreeNode root) {
    preOrder(root.left);
    doLogic(root);
    preOrder(root.right);
}
```



- 后序遍历：右根左

```java
public List<Integer> preOrder(TreeNode root) {
    preOrder(root.right);
    doLogic(root);
    preOrder(root.left);
}
```



# 回溯

- 回溯其实可以简单理解为具有reverse state 操作的递归，回溯递归中，不断下探到底后，回溯到上一层，需要恢复当前层的某些状态。

> ```
> 回溯框架讲解：https://leetcode-cn.com/problems/n-queens/solution/hui-su-suan-fa-xiang-jie-by-labuladong/
> 这篇文章讲解的是如何理解回溯，给出的模板如下，和老师的模板类似，可以应用到不同形式的题目，经典的题目有
> - 全排列
> - N皇后
> ```

```java
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return
    
    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```

# 分治

- 分治也是可以理解为一种特殊的递归，求一个问题，可以分解成**重复性**小的子问题，子问题求解完**merge**成大问题的解；
- 分治代码模板

```java
void divide_conquer(problem, param1, param2, ...): 
  # recursion terminator 
     //树的话就是到达叶子节点 
  if problem is None: 
	print_result 
	return 

  # prepare data 
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 

  # conquer subproblems 
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …

  # process and generate the final result 
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # revert the current level states
```

和递归的模板基本差不多，唯一可能有点不同的就是最后的子结果要进行一些组合

