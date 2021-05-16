package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 421. 数组中两个数的最大异或值
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 *
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 *
 * 示例 1：
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：0
 *
 * 示例 3：
 * 输入：nums = [2,4]
 * 输出：6
 *
 * 示例 4：
 * 输入：nums = [8,10,2]
 * 输出：10
 *
 * 示例 5：
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 *
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 0 <= nums[i] <= 2^31 - 1
 *
 * @author donglin
 * @since 2021-05-16
 */
public class FindMaximumXOR {

    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int max = 0;
        for (int num : nums) {
            trie.add(num);
            max = Math.max(max, findMaximumXOR(num, trie));
        }
        return max;
    }

    public int findMaximumXOR(int num, Trie trie) {
        int result = 0;
        for (int i = 30; i >= 0; i--) {
            int flag = (num >> i) & 1;
            if (flag == 0) {
                if (trie.next[1] != null) {
                    trie = trie.next[1];
                    result |= (1 << i);
                } else {
                    trie = trie.next[0];
                }
            } else {
                if (trie.next[0] != null) {
                    trie = trie.next[0];
                    result |= (1 << i);
                } else {
                    trie = trie.next[1];
                }
            }
        }
        return result;
    }

    private static class Trie {

        private final Trie[] next = new Trie[2];

        public void add(int num) {
            Trie trie = this;
            for (int i = 30; i >= 0; i--) {
                int flag = (num >> i) & 1;
                if (trie.next[flag] == null) {
                    trie.next[flag] = new Trie();
                }
                trie = trie.next[flag];
            }
        }
    }

    @Test
    public void case1() {
        int[] nums = {3, 10, 5, 25, 2, 8};
        Assert.assertEquals(28, findMaximumXOR(nums));
    }

    @Test
    public void case2() {
        int[] nums = {0};
        Assert.assertEquals(0, findMaximumXOR(nums));
    }

    @Test
    public void case3() {
        int[] nums = {2, 4};
        Assert.assertEquals(6, findMaximumXOR(nums));
    }

    @Test
    public void case4() {
        int[] nums = {8, 10, 2};
        Assert.assertEquals(10, findMaximumXOR(nums));
    }

    @Test
    public void case5() {
        int[] nums = {14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};
        Assert.assertEquals(127, findMaximumXOR(nums));
    }

}
