//24. 两两交换链表中的节点
//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
//你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//
//示例:
//
//给定 1->2->3->4, 你应该返回 2->1->4->3.
package Week_01.linkedList;

public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        int size = 0;
        ListNode first = head.next;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        while (head != null) {
            size++;
            if (size % 2 == 0){
                prev.next = head.next.next != null ? head.next.next : head.next;
                head.next = prev;
            }
            prev = head;
            head = prev.next;
        }
        return first;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
