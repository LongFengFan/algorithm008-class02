//98. 验证二叉搜索树
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
//假设一个二叉搜索树具有如下特征：
//
//节点的左子树只包含小于当前节点的数。
//节点的右子树只包含大于当前节点的数。
//所有左子树和右子树自身必须也是二叉搜索树。
//示例 1:
//
//输入:
//    2
//   / \
//  1   3
//输出: true
//示例 2:
//
//输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
package Week_03;


import java.util.ArrayList;
import java.util.List;

public class IsValidBST {
    //    递归方法
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }


    private boolean helper(TreeNode node, Integer lower, Integer higher) {
        if (node == null) return true;
        int val = node.val;
        if(lower != null && val <= lower) return false;
        if(higher != null && val >= higher) return false;
        if(!helper(node.left,lower,val)) return false;
        if(!(helper(node.right,val,higher))) return false;
        return true;
    }

    //    搜索二叉树中序遍历为升序
    public boolean isValidBST2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper2(root, list);
        if(list.size() < 1){
            return true;
        }
        int v = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i) <= v) return false;
            v = list.get(i);
        }
        return true;
    }

    void helper2(TreeNode node, List<Integer> list) {
        if (node != null) {
            if (node.left != null) {
                helper2(node.left, list);
            }
            list.add(node.val);
            if (node.right != null) {
                helper2(node.right, list);
            }
        }
    }


}
