package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * @author donglin
 * @since 2020-07-10
 */
public class MaxProfit {

    @Test
    public void case1() {
        int[] prices = {1,2,3,0,2};
        Assert.assertEquals(3, maxProfit(prices));
    }

    private int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        // 若当前手上持有股票的最大收益
        int p0 = -prices[0];
        // 若当前手上不持有股票，并且处于冷冻期中的累计最大收益
        int p1 = 0;
        // 手上不持有股票，并且不在冷冻期中的累计最大收益
        int p2 = 0;
        for (int i = 1; i < n; ++i) {
            int np0 = Math.max(p0, p2 - prices[i]);
            int np1 = p0 + prices[i];
            int np2 = Math.max(p1, p2);
            p0 = np0;
            p1 = np1;
            p2 = np2;
        }
        return Math.max(p1, p2);
    }
}
