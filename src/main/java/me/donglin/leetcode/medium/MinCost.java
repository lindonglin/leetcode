package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;


/**
 * 剑指 Offer II 091. 粉刷房子
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
 * 每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 *
 * 请计算出粉刷完所有房子最少的花费成本。
 *
 * 示例 1：
 * 输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 *      最少花费: 2 + 5 + 3 = 10。
 *
 * 示例 2：
 * 输入: costs = [[7,6,2]]
 * 输出: 2
 *
 * 提示:
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 *
 * @author donglin
 * @since 2022-06-25
 */
public class MinCost {

    public int minCost(int[][] costs) {
        int dp0 = costs[0][0], dp1 = costs[0][1], dp2 = costs[0][2];
        for (int i = 1, n = costs.length; i < n; i++) {
            int tmp0  = costs[i][0] + Math.min(dp1, dp2);
            int tmp1 = costs[i][1] + Math.min(dp0, dp2);
            int tmp2 = costs[i][2] + Math.min(dp0, dp1);
            dp0 = tmp0;
            dp1 = tmp1;
            dp2 = tmp2;
        }
        return Math.min(dp0, Math.min(dp1, dp2));
    }

    @Test
    public void case1() {
        int[][] costs = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        Assert.assertEquals(10, minCost(costs));
    }

    @Test
    public void case2() {
        int[][] costs = {{7, 6, 2}};
        Assert.assertEquals(2, minCost(costs));
    }


    @Test
    public void case3() {
        int[][] costs = {{3, 5, 3}, {6, 17, 6}, {7, 13, 18}, {9, 10, 18}};
        Assert.assertEquals(26, minCost(costs));
    }
}
