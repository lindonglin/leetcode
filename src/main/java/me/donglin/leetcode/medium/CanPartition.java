package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * @author donglin
 * @since 2020-10-11
 */
public class CanPartition {

    public boolean canPartition(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        int sum = 0;
        int maxVal = 0;
        for (int val : nums) {
            sum += val;
            maxVal = Math.max(maxVal, val);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxVal > target) {
            return false;
        }
        boolean[][] state = new boolean[nums.length][target + 1];
        state[0][0] = true;
        state[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                state[i][j] = state[i - 1][j];
            }
            for (int j = 0; j <= target - nums[i]; j++) {
                state[i][j + nums[i]] = state[i - 1][j];
            }
        }
        return state[nums.length - 1][target];
    }

    @Test
    public void case1() {
        int[] nums = {1, 5, 11, 5};
        Assert.assertTrue(canPartition(nums));
    }


    @Test
    public void case2() {
        int[] nums = {1, 2, 3, 5};
        Assert.assertFalse(canPartition(nums));
    }

    @Test
    public void case3() {
        int[] nums = {1, 2, 5};
        Assert.assertFalse(canPartition(nums));
    }
}
