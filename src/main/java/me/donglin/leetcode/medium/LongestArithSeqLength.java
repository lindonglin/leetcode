package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 1027. 最长等差数列
 * @author donglin
 * @since 2023-04-22
 */
public class LongestArithSeqLength extends BaseTest {

    public int longestArithSeqLength(int[] nums) {
        int minv = Arrays.stream(nums).min().getAsInt();
        int maxv = Arrays.stream(nums).max().getAsInt();
        int diff = maxv - minv;
        int ans = 1;
        for (int d = -diff; d <= diff; ++d) {
            int[] f = new int[maxv + 1];
            Arrays.fill(f, -1);
            for (int num : nums) {
                int prev = num - d;
                if (prev >= minv && prev <= maxv && f[prev] != -1) {
                    f[num] = Math.max(f[num], f[prev] + 1);
                    ans = Math.max(ans, f[num]);
                }
                f[num] = Math.max(f[num], 1);
            }
        }
        return ans;
    }

    @Override
    public void case1() {
        int[] nums = {3, 6, 9, 12};
        Assert.assertEquals(4, longestArithSeqLength(nums));
    }

    @Test
    public void case2() {
        int[] nums = {9, 4, 7, 2, 10};
        Assert.assertEquals(3, longestArithSeqLength(nums));
    }
}
