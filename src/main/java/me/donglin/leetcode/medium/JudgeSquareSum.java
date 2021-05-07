package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 *
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 *
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 *
 * 示例 3：
 * 输入：c = 4
 * 输出：true
 *
 * 示例 4：
 * 输入：c = 2
 * 输出：true
 *
 * 示例 5：
 * 输入：c = 1
 * 输出：true
 *
 * 提示：
 * 0 <= c <= 2^31 - 1
 *
 * @author donglin
 * @version 1.0
 * @since 2021/04/28
 */
public class JudgeSquareSum {

    public boolean judgeSquareSum(int c) {
        int i = 0;
        int k = 0;
        int n = c / 2;
        while (k <= n) {
            if (isSquare(c - k)) {
                return true;
            }
            i++;
            k = i * i;
        }
        return false;
    }

    private boolean isSquare(int c) {
        int a = (int) Math.sqrt(c);
        return c == Math.pow(a, 2) || c == Math.pow(a + 1, 2);
    }

    @Test
    public void case1() {
        Assert.assertTrue(judgeSquareSum(5));
        Assert.assertFalse(judgeSquareSum(3));
        Assert.assertTrue(judgeSquareSum(4));
        Assert.assertTrue(judgeSquareSum(2));
        Assert.assertTrue(judgeSquareSum(1));
        Assert.assertTrue(judgeSquareSum(1000));
    }


}
