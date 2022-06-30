package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1175. 质数排列
 * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；
 * 你需要返回可能的方案总数。
 *
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 *
 * 由于答案可能会很大，所以请你返回答案 模 mod (10^9 + 7) 之后的结果即可。
 *
 * 示例 1：
 * 输入：n = 5
 * 输出：12
 * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
 *
 * 示例 2：
 * 输入：n = 100
 * 输出：682289015
 *
 * 提示：
 * 1 <= n <= 100
 *
 * @author donglin
 * @since 2022-06-30
 */
public class NumPrimeArrangements {

    private static final int[] PRIME_NUMBER = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
            41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    private static final int MOD = 1000000007;

    public int numPrimeArrangements(int n) {
        if (n == 1) {
            return 1;
        }
        int size = PRIME_NUMBER.length;
        for (int i = 0; i < PRIME_NUMBER.length; i++) {
            if (n < PRIME_NUMBER[i]) {
                size = i;
                break;
            }
            if (n == PRIME_NUMBER[i]) {
                size = i + 1;
                break;
            }
        }
        return (int) ((arrangement(size) * arrangement(n - size)) % MOD);
    }

    private long arrangement(int size) {
        long count = 1;
        for (int i = 2; i <= size; i++) {
            count = (count * i) % MOD;
        }
        return count;
    }

    @Test
    public void case1() {
        Assert.assertEquals(12, numPrimeArrangements(5));
    }

    @Test
    public void case2() {
        Assert.assertEquals(682289015, numPrimeArrangements(100));
    }

    @Test
    public void case3() {
        Assert.assertEquals(4, numPrimeArrangements(4));
    }

}
