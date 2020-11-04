package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * @author donglin
 * @since 2020-11-04
 */
public class Insert {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> list = new ArrayList<>();
        int i = 0;
        Integer left = null, right = null;
        boolean addNew = false;
        do {
            if (newInterval[1] < intervals[i][0]) {
                list.add(newInterval);
                addNew = true;
                break;
            }
            if (newInterval[0] > intervals[i][1]) {
                list.add(intervals[i++]);
            } else {
                left = Math.min(newInterval[0], intervals[i][0]);
                right = Math.max(newInterval[1], intervals[i][1]);
                break;
            }
        } while (i < intervals.length);

        if (left != null) {
            for (; i < intervals.length; i++) {
                if (right < intervals[i][0]) {
                    break;
                }
                right = Math.max(right, intervals[i][1]);
            }
            list.add(new int[]{left, right});
        } else if (!addNew) {
            list.add(newInterval);
        }
        for (; i < intervals.length; i++) {
            list.add(intervals[i]);
        }

        int[][] result = new int[list.size()][2];
        for (int k = 0, size = list.size(); k < size; k++) {
            result[k] = list.get(k);
        }
        return result;
    }

    @Test
    public void case1() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] expected = {{1, 5}, {6, 9}};
        Assert.assertArrayEquals(expected, insert(intervals, newInterval));
    }

    @Test
    public void case2() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        int[][] expected = {{1, 2}, {3, 10}, {12, 16}};
        Assert.assertArrayEquals(expected, insert(intervals, newInterval));
    }

    @Test
    public void case3() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {10, 11};
        int[][] expected = {{1, 3}, {6, 9}, {10, 11}};
        Assert.assertArrayEquals(expected, insert(intervals, newInterval));
    }

    @Test
    public void case4() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {8, 11};
        int[][] expected = {{1, 3}, {6, 11}};
        Assert.assertArrayEquals(expected, insert(intervals, newInterval));
    }
}
