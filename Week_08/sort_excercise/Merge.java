//56. 合并区间
//给出一个区间的集合，请合并所有重叠的区间。
//
//示例 1:
//
//输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//示例 2:
//
//输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
package Week_08.sort_excercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge {
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> list = new ArrayList<>();
//        List<int[]> ints = Arrays.asList(intervals);
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if(intervals[i][1] < intervals[i + 1][0]){
                continue;
            }
            if(intervals[i][1] <= intervals[i + 1][1]){
                intervals[i + 1][0] = intervals[i][0];
            }else {
                intervals[i + 1] = intervals[i];
            }
            intervals[i] = null;
        }

        int k = 0;
        for (int[] interval : intervals) {
            if(interval != null){
                list.add(interval);
            }
        }
        int[][] newIntervals = new int[list.size()][2];
        list.toArray(newIntervals);
        return newIntervals;
    }

    public static void main(String[] args) {
        int[][] intervals = {{2, 6}, {8, 10},{1, 3},  {15, 18}};
        new Merge().merge(intervals);
    }
}
