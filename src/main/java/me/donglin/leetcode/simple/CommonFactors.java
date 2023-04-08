package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;

/**
 * 2427. 公因子的数目
 * @author donglin
 * @since 2023-04-05
 */
public class CommonFactors extends BaseTest {

    public int commonFactors(int a, int b) {
        int c = gcd(a, b), ans = 0;
        for (int x = 1; x * x <= c; ++x) {
            if (c % x == 0) {
                ++ans;
                if (x * x != c) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            a %= b;
            a ^= b;
            b ^= a;
            a ^= b;
        }
        return a;
    }

    @Override
    public void case1() {
        Assert.assertEquals(4, commonFactors(12, 6));
    }
}
