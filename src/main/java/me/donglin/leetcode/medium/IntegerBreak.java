package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;


/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * @author donglin
 * @since 2020/07/30
 */
public class IntegerBreak {


    @Test
    public void case1() {
        Assert.assertEquals(1, integerBreak(2));
    }

    @Test
    public void case2() {
        Assert.assertEquals(36, integerBreak(10));
    }

    @Test
    public void case3() {
        Assert.assertEquals(9, integerBreak(6));
    }

    @Test
    public void case4() {
        Assert.assertEquals(2, integerBreak(3));
    }


    /**
     * 拆成尽可能多的3即可，剩余的当成2
     */
    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int quotient = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) {
            return (int) Math.pow(3, quotient - 1) * 4;
        } else {
            return (int) Math.pow(3, quotient) * 2;
        }
    }
}
