//104. 二叉树的最大深度
//给定一个二叉树，找出其最大深度。
//
//二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
//
//说明: 叶子节点是指没有子节点的节点。
//
//示例：
//给定二叉树 [3,9,20,null,null,15,7]，
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//返回它的最大深度 3 。
package Week_03;

public class MaxDepth {
    public int maxDepth(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode node, int depth) {
//        前序遍历
        if (node != null) {
            depth = depth + 1;
            int tmp = depth;
            if (node.left != null) {
                int currentDepth = helper(node.left, depth);
                depth = Math.max(currentDepth, depth);
            }
            if (node.right != null) {
                int currentDepth = helper(node.right, tmp);
                depth = Math.max(currentDepth, depth);
            }
        }
        return depth;
    }


}
