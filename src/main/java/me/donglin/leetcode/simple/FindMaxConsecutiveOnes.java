package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 485. 最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * 示例 1:
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 *
 * 注意：
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 * @author donglin
 * @since 2021-02-15
 */
public class FindMaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int tmp = 0;
        for (int val : nums) {
            if (val == 0) {
                result = Math.max(result, tmp);
                tmp = 0;
            } else {
                tmp++;
            }
        }
        result = Math.max(result, tmp);
        return result;
    }

    @Test
    public void case1() {
        int[] nums = {1, 1, 0, 1, 1, 1};
        Assert.assertEquals(3, findMaxConsecutiveOnes(nums));
    }
}
