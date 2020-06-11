//146. LRU缓存机制
//运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
//
//获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
//写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
//
//
//
//进阶:
//
//你是否可以在 O(1) 时间复杂度内完成这两种操作？
//
//
//
//示例:
//
//LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得关键字 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得关键字 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
package Week_08;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private Map<Integer, Node> map;

    private Node head = new Node();

    private Node tail = new Node();
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            moveToHead(node);
            return node.val;
        }
        return -1;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
//            移动至头部
            Node node = map.get(key);
            node.val = value;
            moveToHead(node);
        } else {
//            添加至头部
            Node node = new Node(key, value);
            map.put(key, node);
            if (map.size() > capacity) {
//                删除尾部元素
                Node toBeDelete = tail.prev;
                removeNode(toBeDelete);
                map.remove(toBeDelete.key);
            }
            addToHead(node);
        }
    }

    private void addToHead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

     class Node {
        Node prev;
        Node next;
        int key;
        int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public Node() {
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
