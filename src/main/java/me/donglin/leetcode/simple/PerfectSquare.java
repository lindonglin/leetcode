package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 367. 有效的完全平方数
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 *
 * 示例 1：
 *
 * 输入：num = 16
 * 输出：true
 *
 * 示例 2：
 * 输入：num = 14
 * 输出：false
 *
 * 提示：
 * 1 <= num <= 2^31 - 1
 *
 * @author donglin
 * @since 2021-11-04
 */
public class PerfectSquare {

    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            long mul = (long) mid * mid;
            if (mul == num) {
                return true;
            }
            if (mul > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    @Test
    public void case1() {
        Assert.assertTrue(isPerfectSquare(1));
        Assert.assertTrue(isPerfectSquare(16));
        Assert.assertTrue(isPerfectSquare(25));
        Assert.assertFalse(isPerfectSquare(14));
        Assert.assertFalse(isPerfectSquare(101));
        Assert.assertFalse(isPerfectSquare(Integer.MAX_VALUE));
    }

    @Test
    public void case2() {
        Assert.assertTrue(isPerfectSquare(808201));
    }
}
