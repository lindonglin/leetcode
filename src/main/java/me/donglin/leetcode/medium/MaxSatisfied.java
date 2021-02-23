package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1052. 爱生气的书店老板
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。
 * 每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 *
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 *
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 *
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *
 * 示例：
 *
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 * 提示：
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 *
 * @author donglin
 * @since 2021-02-23
 */
public class MaxSatisfied {

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int satisfied = 0;
        int maxSatisfied = 0;
        int addSatisfied = 0;
        int maxAddSatisfied = 0;
        for (int i = 0; i < X; i++) {
            satisfied += customers[i] * (1 - grumpy[i]);
            maxSatisfied += customers[i];
        }
        addSatisfied = maxSatisfied - satisfied;
        maxAddSatisfied = addSatisfied;
        for (int i = X; i < customers.length; i++) {
            satisfied += customers[i] * (1 - grumpy[i]);
            addSatisfied += customers[i] * grumpy[i];
            addSatisfied -= customers[i - X] * grumpy[i - X];
            maxAddSatisfied = Math.max(maxAddSatisfied, addSatisfied);
        }
        return satisfied + maxAddSatisfied;
    }

    @Test
    public void case1() {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int x = 3;
        Assert.assertEquals(16, maxSatisfied(customers, grumpy, x));
    }

    @Test
    public void case2() {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 1, 1};
        int x = 3;
        Assert.assertEquals(16, maxSatisfied(customers, grumpy, x));
    }


    @Test
    public void case3() {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 0, 0, 0};
        int x = 3;
        Assert.assertEquals(18, maxSatisfied(customers, grumpy, x));
    }

    @Test
    public void case4() {
        int[] customers = {1};
        int[] grumpy = {0};
        int x = 1;
        Assert.assertEquals(1, maxSatisfied(customers, grumpy, x));
    }

}
