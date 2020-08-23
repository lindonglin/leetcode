package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 201. 数字范围按位与
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，
 * 返回此范围内所有数字的按位与（包含 m, n 两端点）。
 *
 * 示例 1:
 * 输入: [5,7]
 * 输出: 4
 *
 * 示例 2:
 * 输入: [0,1]
 * 输出: 0
 *
 * @author donglin
 * @since 2020-08-23
 */
public class RangeBitwiseAnd {

    @Test
    public void case1() {
        Assert.assertEquals(4, rangeBitwiseAnd(5, 7));
    }

    @Test
    public void case2() {
        Assert.assertEquals(0, rangeBitwiseAnd(0, 1));
    }

    @Test
    public void case3() {
        Assert.assertEquals(0, rangeBitwiseAnd(1, 33));
    }

    @Test
    public void case4() {
        Assert.assertEquals(2, rangeBitwiseAnd(2, 3));
    }

    @Test
    public void case5() {
        Assert.assertEquals(0, rangeBitwiseAnd(1, 2));
    }

    @Test
    public void case6() {
        Assert.assertEquals(4, rangeBitwiseAnd(4, 5));
    }

    @Test
    public void case7() {
        Assert.assertEquals(0, rangeBitwiseAnd(3, 4));
    }

    public int rangeBitwiseAnd(int m, int n) {
        int d = n - m;
        int b = 0;
        while (d > 0) {
            b++;
            d >>= 1;
        }
        return (Integer.MAX_VALUE << b) & m & n;
    }
}
