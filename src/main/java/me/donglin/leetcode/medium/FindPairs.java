package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 532. 数组中的 k-diff 数对
 * 给定一个整数数组和一个整数 k，你需要在数组里找到 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：
 *
 * 0 <= i < j < nums.length
 * |nums[i] - nums[j]| == k
 * 注意，|val| 表示 val 的绝对值。
 *
 * 示例 1：
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 *
 * 示例 2：
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 *
 * 示例 3：
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1)。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^7 <= nums[i] <= 10^7
 * 0 <= k <= 10^7
 *
 * @author donglin
 * @since 2022-06-16
 */
public class FindPairs {

    public int findPairs(int[] nums, int k) {
        int res = 0;
        int n = nums.length;
        Arrays.sort(nums);
        if (k == 0) {
            int pre = Integer.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                if (nums[i] == nums[i - 1] && nums[i] != pre) {
                    res++;
                    pre = nums[i];
                }
            }
            return res;
        }
        for (int i = 0, m = n - 1; i < m; i++) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (nums[j] == nums[j - 1]) {
                    continue;
                }
                int dist = nums[j] - nums[i];
                if (dist == k) {
                    res++;
                } else if (dist > k) {
                    break;
                }
            }
        }
        return res;
    }

    @Test
    public void case1() {
        Assert.assertEquals(2, findPairs(new int[]{3, 1, 4, 1, 5}, 2));
    }

    @Test
    public void case2() {
        Assert.assertEquals(4, findPairs(new int[]{1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void case3() {
        Assert.assertEquals(1, findPairs(new int[]{1, 3, 1, 5, 4}, 0));
    }

    @Test
    public void case4() {
        Assert.assertEquals(2, findPairs(new int[]{1, 2, 4, 4, 3, 3, 0, 9, 2, 3}, 3));
    }

}
