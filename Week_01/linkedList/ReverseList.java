package Week_01.linkedList;//206. 反转链表
//反转一个单链表。
//
//示例:
//
//输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL
//进阶:
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
//
//通过次数240,478提交次数348,229


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class ReverseList {
    //    迭代
//    借助栈
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        LinkedList<ListNode> deque = new LinkedList<>();
        deque.push(head);
        while (head.next != null) {
            deque.push(head.next);
            head = head.next;
        }
        head = deque.poll();
        ListNode tmp = head;
        while (!deque.isEmpty()) {
            head.next = deque.poll();
            head = head.next;
        }
        head.next = null;
        return tmp;
    }

    //    利用数组，和栈一样
    public ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        ArrayList<ListNode> listNodes = new ArrayList<>();
        listNodes.add(head);

        while (head.next != null) {
            listNodes.add(head.next);
            head = head.next;
        }
        head = listNodes.get(listNodes.size() - 1);
        ListNode node = head;
        for (int i = listNodes.size() - 2; i >= 0; i--) {
            node.next = listNodes.get(i);
        }
        node.next = null;
        return head;
    }

    //    直接迭代
//    假设存在链表 1 → 2 → 3 → Ø，我们想要把它改成 Ø ← 1 ← 2 ← 3。
//    需要事先存储其前一个元素。
    public ListNode reverseList3(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr;
            curr = curr.next;
            tmp.next = prev;
            prev = tmp;
        }
        return prev;
    }

    //    迭代转化递归
    public ListNode reverseList4(ListNode head) {
        return dfs(null, head);
    }

    private ListNode dfs(ListNode prev, ListNode curr) {
        if (curr == null) { return prev; }
        ListNode next = curr.next;
        curr.next = prev;
        return dfs(curr, next);
    }


    //    直接递归
//    可以对比ppt演示
//    https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
    public ListNode reverseList5(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curr = reverseList5(head.next);
        head.next.next = head;
        head.next = null;
        return curr;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}



