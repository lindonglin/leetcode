package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 926. 将字符串翻转到单调递增
 * 如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。
 * 给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。
 * 返回使 s 单调递增的最小翻转次数。
 *
 * 示例 1：
 * 输入：s = "00110"
 * 输出：1
 * 解释：翻转最后一位得到 00111.
 *
 * 示例 2：
 * 输入：s = "010110"
 * 输出：2
 * 解释：翻转得到 011111，或者是 000111。
 *
 * 示例 3：
 * 输入：s = "00011000"
 * 输出：2
 * 解释：翻转得到 00000000。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 为 '0' 或 '1'
 *
 * @author donglin
 * @since 2022-06-11
 */
public class MinFlipsMonoIncr {

    /**
     * 由 minFlipsMonoIncr2 优化而来的
     */
    public int minFlipsMonoIncr(String s) {
        char[] chars = s.toCharArray();
        int dp0 = 0, dp1 = 0;
        for (char c : chars) {
            if (c == '0') {
                dp1 = Math.min(dp0, dp1) + 1;
            } else {
                dp1 = Math.min(dp0, dp1);
                dp0 = dp0 + 1;
            }
        }
        return Math.min(dp0, dp1);
    }


    public int minFlipsMonoIncr2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][2];
        if (chars[0] == '0') {
            dp[0][0] = 0;
            dp[0][1] = 1;
        } else {
            dp[0][0] = 1;
            dp[0][1] = 0;
        }
        for (int i = 1; i < n; i++) {
            char c = chars[i];
            if (c == '0') {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]);
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    @Test
    public void case1() {
        Assert.assertEquals(1, minFlipsMonoIncr("00110"));
        Assert.assertEquals(1, minFlipsMonoIncr2("00110"));
    }

    @Test
    public void case2() {
        Assert.assertEquals(2, minFlipsMonoIncr("010110"));
        Assert.assertEquals(2, minFlipsMonoIncr2("010110"));
    }

    @Test
    public void case3() {
        Assert.assertEquals(2, minFlipsMonoIncr("00011000"));
        Assert.assertEquals(2, minFlipsMonoIncr2("00011000"));
    }
}
