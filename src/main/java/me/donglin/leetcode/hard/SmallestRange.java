package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 632. 最小区间
 * 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * 示例 1:
 *
 * 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出: [20,24]
 * 解释:
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 *
 * 注意:
 * 1. 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
 * 2. 1 <= k <= 3500
 * 3. -10^5 <= 元素的值 <= 10^5
 * 4. 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
 *
 * @author donglin
 * @since 2020-08-01
 */
public class SmallestRange {

    @Test
    public void case1() {
        List<List<Integer>> nums = Arrays.asList(Arrays.asList(4, 10, 15, 24, 26), Arrays.asList(0, 9, 12, 20), Arrays.asList(5, 18, 22, 30));
        int[] expected = {20, 24};
        Assert.assertArrayEquals(expected, smallestRange(nums));
    }


    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        // 当前最大值
        int max = Integer.MIN_VALUE;
        int start = -100000;
        int end = 100000;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));

        // 先将k的数组的第一个元素加入队列
        for (int i = 0; i < n; i++) {
            int value = nums.get(i).get(0);
            pq.offer(new Node(i, 0, value));
            max = Math.max(max, value);
        }

        while (pq.size() == n) {
            Node node = pq.poll();
            // 更新区间长度
            assert node != null;
            if (max - node.value < end - start) {
                start = node.value;
                end = max;
            }

            // 将取走数所在的数组的下一个数加入到队列中，保持队列包含了这k个数组的一个数
            if (node.j + 1 < nums.get(node.i).size()) {
                int nextValue = nums.get(node.i).get(node.j + 1);
                pq.offer(new Node(node.i, node.j + 1, nextValue));
                max = Math.max(max, nextValue);
            }
        }
        return new int[]{start, end};

    }

    private static class Node {
        /**
         * 第i个数组
         */
        private final int i;
        /**
         * 数组i的第j个元素
         */
        private final int j;
        /**
         * 第i个数组的第j个元素的值
         */
        private final int value;

        public Node(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }

}
