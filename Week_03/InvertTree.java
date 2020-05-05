//226. 翻转二叉树
//翻转一棵二叉树。
//
//示例：
//
//输入：
//
//     4
//   /   \
//  2     7
// / \   / \
//1   3 6   9
//输出：
//
//     4
//   /   \
//  7     2
// / \   / \
//9   6 3   1
//备注:
//这个问题是受到 Max Howell 的 原问题 启发的 ：
//
//谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
//https://leetcode-cn.com/problems/invert-binary-tree/
package Week_03;

import java.util.LinkedList;
import java.util.List;

public class InvertTree {

    //    递归,深度优先
    public TreeNode invertTree(TreeNode root) {
        helper(root);
        return root;
    }

    private void helper(TreeNode node) {
        if (node == null) return;
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        helper(node.left);
        helper(node.right);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //    队列，广度优先，将每一层的数据依次放入队列中，依次调用调换左右
    public TreeNode invertTree2(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        if(root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if(node.left != null) queue.addLast(node.left);
            if(node.right != null) queue.addLast(node.right);
        }
        return root;
    }

}
