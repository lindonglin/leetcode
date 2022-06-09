package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 *
 * @author donglin
 * @since 2022-06-09
 */
public class Reverse {

    public int reverse(int x) {
        int res = 0;
        int n = Math.abs(x);
        while (n > 0) {
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            res = res * 10 + n % 10;
            n /= 10;
        }
        return x < 0 ? -res : res;
    }

    @Test
    public void case1() {
        Assert.assertEquals(321, reverse(123));
    }

    @Test
    public void case2() {
        Assert.assertEquals(-123, reverse(-321));
    }

    @Test
    public void case3() {
        Assert.assertEquals(21, reverse(120));
    }

    @Test
    public void case4() {
        Assert.assertEquals(0, reverse(0));
        Assert.assertEquals(0, reverse(1534236469));
    }


    @Test
    public void case5() {
        Assert.assertEquals(0, reverse(1534236469));
    }
}
