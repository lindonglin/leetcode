package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;


/**
 * 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 *
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2:
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 *
 * 示例 3:
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *
 * 注意:
 * 你可以假设：
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 *
 * @author donglin
 * @version 1.0
 * @since 2021/05/14
 */
public class Change2 {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int x = coin; x < amount + 1; x++) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];
    }

    @Test
    public void case1() {
        int amount = 5;
        int[] coins = {1, 2, 5};
        Assert.assertEquals(4, change(amount, coins));
    }

    @Test
    public void case2() {
        int amount = 3;
        int[] coins = {2};
        Assert.assertEquals(0, change(amount, coins));
    }

    @Test
    public void case3() {
        int amount = 500;
        int[] coins = {1, 2, 5};
        Assert.assertEquals(12701, change(amount, coins));
    }

}
