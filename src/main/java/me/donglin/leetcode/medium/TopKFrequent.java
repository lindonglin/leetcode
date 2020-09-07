package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 提示：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 *
 * @author donglin
 * @since 2020-09-07
 */
public class TopKFrequent {

    @Test
    public void case1() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] expected = {1, 2};
        Assert.assertArrayEquals(expected, topKFrequent(nums, k));
    }

    @Test
    public void case2() {
        int[] nums = {1};
        int k = 1;
        int[] expected = {1};
        Assert.assertArrayEquals(expected, topKFrequent(nums, k));
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Pair> map = new HashMap<>();
        for (int v : nums) {
            if (!map.containsKey(v)) {
                map.put(v, new Pair(v));
            }
            map.get(v).count++;
        }
        Pair[] pairs = map.values().toArray(new Pair[0]);
        Arrays.sort(pairs, Comparator.comparingInt(Pair::getCount).reversed());
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pairs[i].value;
        }
        return result;
    }

    private static class Pair {
        private final int value;
        private int count;

        public Pair(int value) {
            this.value = value;
        }

        public int getCount() {
            return count;
        }
    }
}
