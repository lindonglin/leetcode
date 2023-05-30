package me.donglin.leetcode.hard;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * 829. 连续整数求和
 * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。
 *
 * 示例 1:
 * 输入: n = 5
 * 输出: 2
 * 解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
 *
 * 示例 2:
 * 输入: n = 9
 * 输出: 3
 * 解释: 9 = 4 + 5 = 2 + 3 + 4
 *
 * 示例 3:
 * 输入: n = 15
 * 输出: 4
 * 解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 * 提示:
 * 1 <= n <= 10^9
 *
 * @author donglin
 * @since 2022-06-03
 */
public class ConsecutiveNumbersSum extends BaseTest {

    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        int bound = 2 * n;
        for (int k = 1; k * (k + 1) <= bound; k++) {
            if (isKConsecutive(n, k)) {
                ans++;
            }
        }
        return ans;
    }

    public boolean isKConsecutive(int n, int k) {
        if (k % 2 == 1) {
            return n % k == 0;
        } else {
            return n % k != 0 && 2 * n % k == 0;
        }
    }



    /**
     * 数学暴力解法：
     * 假设有2k个连续数相加和为n，且第一个数为a+1，即
     *    n = (a+1) + (a+2) + ... + (a+2k)
     * => n = 2k*a + (1+2k)*k = 2k(a+k) + k
     * => n % 2k = k
     *
     * 假设有2k+1个连续数相加和为n，且第一个数为a+1，即
     *    n = (a+1) + (a+2) + ... + (a+2k) + (a+2k+1)
     * => n = (2k+1)*a + (k+1)*(2k+1) = (2k+1)*(k+1+a)
     * => n % (2k+1) = 0
     *
     * 由此，设i为连续数字个数，从1遍历到n，满足上面条件的即可加+
     */
    public int consecutiveNumbersSumByMath(int n) {
        int result = 0;
        int bound = 2 * n;
        for (int i = 1; i * (i+1) <= bound; i += 2) {
            if (n % i == 0) {
                result++;
            }
        }
        for (int i = 2; i * (i+1) <= bound; i += 2) {
            if (n % i == (i >> 1)) {
               result++;
            }
        }
        return result;
    }

    @Override
    public void case1() {
        Assert.assertEquals(2, consecutiveNumbersSum(5));
        Assert.assertEquals(2, consecutiveNumbersSumByMath(5));
    }

    @Override
    public void case2() {
        Assert.assertEquals(3, consecutiveNumbersSum(9));
        Assert.assertEquals(3, consecutiveNumbersSumByMath(9));
    }

    @Override
    public void case3() {
        Assert.assertEquals(4, consecutiveNumbersSum(15));
        Assert.assertEquals(4, consecutiveNumbersSumByMath(15));
    }

    @Test
    public void case4() {
        long t1 = System.currentTimeMillis();
        Assert.assertEquals(32, consecutiveNumbersSum(642518052));
        System.out.println(System.currentTimeMillis() - t1);
        long t2 = System.currentTimeMillis();
        Assert.assertEquals(32, consecutiveNumbersSumByMath(642518052));
        System.out.println(System.currentTimeMillis() - t2);

    }

}

