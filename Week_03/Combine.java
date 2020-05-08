//77. 组合
//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
//
//示例:
//
//输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
package Week_03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Combine {
    public List<List<Integer>> combine(int n, int k) {
        HashSet<List<Integer>> sets = new HashSet<>();
        List<Integer> box = IntStream.range(1, n + 1).boxed().collect(Collectors.toList());
        helper(sets, box, k, new ArrayList<>(k));
        return new ArrayList<>(sets);
    }

    private void helper(HashSet<List<Integer>> sets, List<Integer> box, int k, List<Integer> list) {
//        terminator
        Integer j = 0;
        if (k == 0) {
            list.sort(Comparator.naturalOrder());
            sets.add(new ArrayList<>(list));
            return;
        }
        for (Integer i : box) {
//            process
            j = i;
            list.add(i);
            List<Integer> newBox = new LinkedList<>(box);
            newBox.remove(i);
//            recurse
            helper(sets, newBox, k - 1, list);

//            reverse state
            list.remove(i);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> combine = new Combine().combine(11, 10);
        System.out.println(combine);
    }
}
