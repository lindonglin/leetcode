package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 719. 找出第 K 小的数对距离
 * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
 *
 * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。
 * 返回 所有数对距离中 第 k 小的数对距离。
 *
 * 示例 1：
 * 输入：nums = [1,3,1], k = 1
 * 输出：0
 * 解释：数对和对应的距离如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 距离第 1 小的数对是 (1,1) ，距离为 0 。
 *
 * 示例 2：
 * 输入：nums = [1,1,1], k = 2
 * 输出：0
 *
 * 示例 3：
 * 输入：nums = [1,6,1], k = 3
 * 输出：5
 *
 * 提示：
 * n == nums.length
 * 2 <= n <= 10^4
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= n * (n - 1) / 2
 *
 * @author donglin
 * @since 2022-06-15
 */
public class SmallestDistancePair {

    public int smallestDistancePair1(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0, right = nums[n - 1];
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int i = 0, j = 0; j < n; j++) {
                while (nums[j] - nums[i] > mid) {
                    i++;
                }
                count += j - i;
            }
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    @SuppressWarnings("ConstantConditions")
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (i1, i2) -> i2 - i1);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dict = Math.abs(nums[i] - nums[j]);
                if (queue.size() < k) {
                    queue.add(dict);
                } else if (queue.peek() > dict) {
                    queue.poll();
                    queue.add(dict);
                }
            }
        }
        return queue.peek();
    }

    @Test
    public void case1() {
        Assert.assertEquals(0, smallestDistancePair(new int[]{1, 3, 1}, 1));
        Assert.assertEquals(0, smallestDistancePair1(new int[]{1, 3, 1}, 1));
    }

    @Test
    public void case2() {
        Assert.assertEquals(0, smallestDistancePair(new int[]{1, 1, 1}, 2));
        Assert.assertEquals(0, smallestDistancePair1(new int[]{1, 1, 1}, 2));
    }

    @Test
    public void case3() {
        Assert.assertEquals(5, smallestDistancePair(new int[]{1, 6, 1}, 3));
        Assert.assertEquals(5, smallestDistancePair1(new int[]{1, 6, 1}, 3));
    }

    @Test
    public void case4() {
        Assert.assertEquals(2, smallestDistancePair(new int[]{9, 10, 7, 10, 6, 1, 5, 4, 9, 8}, 18));
        Assert.assertEquals(2, smallestDistancePair1(new int[]{9, 10, 7, 10, 6, 1, 5, 4, 9, 8}, 18));
    }

}
