package me.donglin.leetcode;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机
 * @author donglin
 * @since 2023-07-18
 */
public class StockMaxProfit {


    /**
     * <span>simple?</span>
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/">121. 买卖股票的最佳时机</a>
     * <span>交易一次</span>
     */
    public int maxProfit1(int[] prices) {
        // 假设你手头的初始金额，其他数值也是可以的
        int initAmount = 0;
        // 买入股票后卖出股票前手头的最大金额，显然在股票最低价买入时达到最大
        int maxAmountAfterBuy = Integer.MIN_VALUE;
        // 卖出股票后手头的最大金额，显然 maxAmountAfterSell - initAmount就是最大利润
        int maxAmountAfterSell = 0;
        for (int price : prices) {
            // 简单计算 maxAmountAfterBuy
            maxAmountAfterBuy = Math.max(initAmount - price, maxAmountAfterBuy);
            // 若当前卖出股票，说明之前以及买入股票了，因此卖出后手头的余额 amountAfterSell = 之前买入后剩余的金额 amountAfterBuy + price
            // 此时price已经确定，因此当amountAfterBuy最大时(即maxAmountAfterBuy)，amountAfterSell 达到最大
            // 再跟前面的 maxAmountAfterSell 比较即可
            maxAmountAfterSell = Math.max(maxAmountAfterBuy + price, maxAmountAfterSell);
        }
        return maxAmountAfterSell - initAmount;
    }

    /**
     * <span>medium?</span>
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/">122. 买卖股票的最佳时机 II</a>
     * <span>不限交易次数</span>
     */
    public int maxProfit2(int[] prices) {
//        int maxProfit = 0;
//        for (int i = 0, size = prices.length - 1; i < size; i++) {
//            if (prices[i] < prices[i+1]) {
//                maxProfit += prices[i+1] - prices[i];
//            }
//        }
//        return maxProfit;
        // 上面注释掉的是比较常规的解法，也就是求增量即可
        // 下面的是通过maxProfit1演化而来的
        int initAmount = 0;
        int maxAmountAfterBuy = Integer.MIN_VALUE;
        int maxAmountAfterSell = 0;
        for (int price : prices) {
            // 因为不限交易次数了，而不是只能交易一次，因此买股票之前的手头余额不在是固定initAmount，而是上次卖出后的余额
            maxAmountAfterBuy = Math.max(maxAmountAfterSell - price, maxAmountAfterBuy);
            maxAmountAfterSell = Math.max(maxAmountAfterBuy + price, maxAmountAfterSell);
        }
        return maxAmountAfterSell - initAmount;
    }


    /**
     * <span>hard</span>
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/">123. 买卖股票的最佳时机 III</a>
     * <span>交易二次</span>
     */
    public int maxProfit3(int[] prices) {
        // 此解法通过maxProfit1演化而来
        int initAmount = 0;
        int[] maxAmountAfterBuy = new int[2];
        int[] maxAmountAfterSell = new int[2];
        Arrays.fill(maxAmountAfterBuy, Integer.MIN_VALUE);
        for (int price : prices) {
            maxAmountAfterBuy[0] = Math.max(initAmount - price, maxAmountAfterBuy[0]);
            maxAmountAfterSell[0] = Math.max(maxAmountAfterBuy[0] + price, maxAmountAfterSell[0]);
            maxAmountAfterBuy[1] = Math.max(maxAmountAfterSell[0] - price, maxAmountAfterBuy[1]);
            maxAmountAfterSell[1] = Math.max(maxAmountAfterBuy[1] + price, maxAmountAfterSell[1]);
        }
        return maxAmountAfterSell[1] - initAmount;
    }

    /**
     * <span>hard</span>
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/">188. 买卖股票的最佳时机 IV</a>
     * <span>限制交易次数</span>
     */
    public int maxProfit4(int[] prices, int k) {
        // 此解法通过maxProfit3演化而来
        int initAmount = 0;
        int[] maxAmountAfterBuy = new int[k];
        int[] maxAmountAfterSell = new int[k];
        Arrays.fill(maxAmountAfterBuy, Integer.MIN_VALUE);
        for (int price : prices) {
            maxAmountAfterBuy[0] = Math.max(initAmount - price, maxAmountAfterBuy[0]);
            maxAmountAfterSell[0] = Math.max(maxAmountAfterBuy[0] + price, maxAmountAfterSell[0]);
            for (int i = 1; i < k; i++) {
                maxAmountAfterBuy[i] = Math.max(maxAmountAfterSell[i-1] - price, maxAmountAfterBuy[i]);
                maxAmountAfterSell[i] = Math.max(maxAmountAfterBuy[i] + price, maxAmountAfterSell[i]);
            }
        }
        return maxAmountAfterSell[k-1] - initAmount;
    }

    /**
     * <span>hard</span>
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/">309. 最佳买卖股票时机含冷冻期</a>
     * <span>不限交易次数</span>
     */
    public int maxProfit5(int[] prices, int k) {
        // todo
        return 0;
    }

    /**
     * <span>hard</span>
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/">714. 买卖股票的最佳时机含手续费</a>
     * <span>不限交易次数</span>
     */
    public int maxProfit6(int[] prices, int fee) {
        // 此解法下面的是通过maxProfit2演化而来的
        int initAmount = 0;
        int maxAmountAfterBuy = Integer.MIN_VALUE;
        int maxAmountAfterSell = 0;
        for (int price : prices) {
            // 需要减去手续费
            maxAmountAfterBuy = Math.max(maxAmountAfterSell - price - fee, maxAmountAfterBuy);
            maxAmountAfterSell = Math.max(maxAmountAfterBuy + price, maxAmountAfterSell);
        }
        return maxAmountAfterSell - initAmount;
    }

    /**
     * 通解
     * @param prices 股票价格走势
     * @return 最大利润
     */
    public int maxProfitCommon(int[] prices) {
        // 假设你手头的初始金额，其他数值也是可以的
        int initAmount = 0;
        // 买入股票后卖出股票前手头的最大金额，显然在股票最低价买入时达到最大
        int maxAmountAfterBuy = Integer.MIN_VALUE;
        // 卖出股票后手头的最大金额，显然 maxAmountAfterSell - initAmount就是最大利润
        int maxAmountAfterSell = 0;
        for (int price : prices) {
            // 简单计算 maxAmountAfterBuy
            maxAmountAfterBuy = Math.max(initAmount - price, maxAmountAfterBuy);
            // 若当前卖出股票，说明之前以及买入股票了，因此卖出后手头的余额 amountAfterSell = 之前买入后剩余的金额 amountAfterBuy + price
            // 此时price已经确定，因此当amountAfterBuy最大时(即maxAmountAfterBuy)，amountAfterSell 达到最大
            // 再跟前面的 maxAmountAfterSell 比较即可
            maxAmountAfterSell = Math.max(maxAmountAfterBuy + price, maxAmountAfterSell);
        }
        return maxAmountAfterSell - initAmount;
    }
}
