package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;
import org.junit.Test;



/**
 * 1027. 最长等差数列
 * @author donglin
 * @since 2023-04-22
 */
public class LongestArithSeqLength extends BaseTest {

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        // dp[i][j]表示以nums[i]为皆为j为公差的等差数列的长度
        int[][] dp = new int[n][1001];
        int offset = 500; // 消除负数
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = nums[j] - nums[i] + offset;
                dp[j][diff] = dp[i][diff] + 1;
                result = Math.max(result, dp[j][diff]);
            }
        }
        return result + 1;
    }


    @Override
    public void case1() {
        int[] nums = {3, 6, 9, 12};
        Assert.assertEquals(4, longestArithSeqLength(nums));
    }

    @Override
    public void case2() {
        int[] nums = {9, 4, 7, 2, 10};
        Assert.assertEquals(3, longestArithSeqLength(nums));
    }
}
