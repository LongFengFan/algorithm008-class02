//347. 前 K 个高频元素
//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
//
//
//
//示例 1:
//
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
//示例 2:
//
//输入: nums = [1], k = 1
//输出: [1]
//
//
//提示：
//
//你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
//你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
//题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
//你可以按任意顺序返回答案。
package Week_02;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {
    // 利用小顶堆，不过输出的顺序并不是从最大开始输出，
    // 不过时间复杂度比大顶堆好，
    // 因为不用全部加入堆中
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        int[] kArr = new int[k];
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 利用小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(k, Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(heap.size() < k){
                heap.add(entry);
            }else {
                // 利用小顶堆最小值在堆顶，只有比最小值大则poll最小值，加入当前值，这样不用全部加入堆。
                if(entry.getValue() > heap.peek().getValue()){
                    heap.poll();
                    heap.add(entry);
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : heap) {
            kArr[idx++] = entry.getKey();
        }
        return kArr;
    }

    // 利用大顶堆，和小顶堆不同的是需要全部加入堆中
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        int[] kArr = new int[k];
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
//        大顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(k, ((o1, o2) -> o2.getValue() - o1.getValue()));
        heap.addAll(map.entrySet());
        while (idx < k) {
            kArr[idx++] = heap.poll().getKey();
        }
        return kArr;
    }
}
