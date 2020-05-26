//297. 二叉树的序列化与反序列化
//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
//
//请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
//
//示例:
//
//你可以将以下二叉树：
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//序列化为 "[1,2,3,null,null,4,5]"
//提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
//
//说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
package Week_03;

import java.util.LinkedList;

public class SerializeTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
//        层序遍历
        if (root == null) return "";
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> treeNodeList = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            treeNodeList.addLast(poll);
            if(poll == null) continue;
            if (poll.left != null) {
                queue.addLast(poll.left);
            } else {
                queue.addLast(null);
            }
            if (poll.right != null) {
                queue.addLast(poll.right);
            } else {
                queue.addLast(null);
            }
        }
        while (treeNodeList.getLast() == null){
            treeNodeList.removeLast();
        }
        StringBuilder sb = new StringBuilder();
        for (TreeNode treeNode : treeNodeList) {
            sb.append(",");
            if(treeNode == null){
                sb.append("null");
            }else {
                sb.append(treeNode.val);
            }
        }
        return sb.toString().substring(1);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
       return null;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left =  new TreeNode(2);
        root.right =  new TreeNode(3);
        root.right.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        String serialize = new SerializeTree().serialize(root);
        System.out.println(serialize);
    }

    // Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
}
