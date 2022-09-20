package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 698. 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *
 * 示例 2:
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 *
 * 提示：
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 *
 * @author donglin
 * @since 2022-09-20
 */
public class CanPartitionKSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int all = 0;
        for (int v : nums) {
            all += v;
        }
        if (all % k != 0) {
            return false;
        }
        int per = all / k;
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[n - 1] > per) {
            return false;
        }
        boolean[] dp = new boolean[1 << n];
        int[] curSum = new int[1 << n];
        dp[0] = true;
        for (int i = 0; i < 1 << n; i++) {
            if (!dp[i]) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (curSum[i] + nums[j] > per) {
                    break;
                }
                if (((i >> j) & 1) == 0) {
                    int next = i | (1 << j);
                    if (!dp[next]) {
                        curSum[next] = (curSum[i] + nums[j]) % per;
                        dp[next] = true;
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    @Test
    public void case1() {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        Assert.assertTrue(canPartitionKSubsets(nums, 4));
    }

    @Test
    public void case2() {
        int[] nums = {1, 2, 3, 4};
        Assert.assertFalse(canPartitionKSubsets(nums, 3));
    }

    @Test
    public void case3() {
        int[] nums = {1, 1, 1, 1, 2, 2, 2, 2};
        Assert.assertTrue(canPartitionKSubsets(nums, 4));
    }

}
