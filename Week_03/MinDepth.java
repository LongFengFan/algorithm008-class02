//111. 二叉树的最小深度
//给定一个二叉树，找出其最小深度。
//
//最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
//说明: 叶子节点是指没有子节点的节点。
//
//示例:
//
//给定二叉树 [3,9,20,null,null,15,7],
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//返回它的最小深度  2.
package Week_03;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//精选题解 -> https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/li-jie-zhe-dao-ti-de-jie-shu-tiao-jian-by-user7208/
//这道题的关键是搞清楚递归结束条件
//叶子节点的定义是左孩子和右孩子都为 null 时叫做叶子节点
//当 root 节点左右孩子都为空时，返回 1
//当 root 节点左右孩子有一个为空时，返回不为空的孩子节点的深度
//当 root 节点左右孩子都不为空时，返回左右孩子较小深度的节点值

public class MinDepth {
    public int minDepth(TreeNode root) {
//       1，递归，深度优先
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int minDepth = Integer.MAX_VALUE;
        if (root.left != null) minDepth = Math.min(minDepth(root.left), minDepth);
        if (root.right != null) minDepth = Math.min(minDepth(root.right), minDepth);
        return minDepth + 1;

    }

    //    精简版
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int m1 = minDepth2(root.left);
        int m2 = minDepth2(root.right);
        //1.如果左孩子和右孩子有为空的情况，直接返回m1+m2+1
        //2.如果都不为空，返回较小深度+1
        return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1, m2) + 1;
    }

    //    官方题解 深度优先搜索迭代，利用栈将递归转换为迭代
    public int minDepth3(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        } else {
            stack.add(new Pair(root, 1));
        }

        int min_depth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pollLast();
            root = current.getKey();
            int current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                min_depth = Math.min(min_depth, current_depth);
            }
            if (root.left != null) {
                stack.add(new Pair(root.left, current_depth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return min_depth;
    }

//    官方题解：方法二：广度优先搜索迭代
//深度优先搜索方法的缺陷是所有节点都必须访问到，以保证能够找到最小深度。因此复杂度是 O(N) 。
//    一个优化的方法是利用广度优先搜索，我们按照树的层去迭代，第一个访问到的叶子就是最小深度的节点，这样就不用遍历所有的节点了。
    public int minDepth4(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        else {
            queue.add(new Pair<>(root, 1));
        }

        int current_depth = 0;
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.poll();
            root = current.getKey();
            current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                break;
            }
            if (root.left != null) {
                queue.add(new Pair<>(root.left, current_depth + 1));
            }
            if (root.right != null) {
                queue.add(new Pair<>(root.right, current_depth + 1));
            }
        }
        return current_depth;
    }
}
