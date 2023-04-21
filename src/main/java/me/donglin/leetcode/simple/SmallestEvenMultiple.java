package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;

/**
 * 2413. 最小偶倍数
 * @author lindonglin
 * @since 2023/4/21
 */
public class SmallestEvenMultiple extends BaseTest {

    public int smallestEvenMultiple(int n) {
        return (n & 1) == 1 ? n << 1 : n;
    }

    @Override
    public void case1() {
        Assert.assertEquals(10, smallestEvenMultiple(5));
        Assert.assertEquals(6, smallestEvenMultiple(6));
    }
}
