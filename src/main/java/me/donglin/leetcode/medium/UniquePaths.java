package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 *
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 *
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 ^ 109
 *
 * @author donglin
 * @since 2020-12-09
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        return combination(m - 1, n + m - 2);
    }

    /**
     * 数学上的组合数 C(a, b)
     */
    private int combination(int a, int b) {
        long mul = 1;
        for (int i = 1, k =  b - a + 1; i <= a; i++, k++) {
            mul *= k;
            mul /= i;
        }
        return (int) mul;
    }

    @Test
    public void case1() {
        Assert.assertEquals(28, uniquePaths(3, 7));
    }

    @Test
    public void case2() {
        Assert.assertEquals(3, uniquePaths(3, 2));
    }

    @Test
    public void case3() {
        Assert.assertEquals(28, uniquePaths(7, 3));
    }

    @Test
    public void case4() {
        Assert.assertEquals(6, uniquePaths(3, 3));
    }

    @Test
    public void case5() {
        Assert.assertEquals(1, uniquePaths(1, 1));
    }

    @Test
    public void case6() {
        Assert.assertEquals(48620, uniquePaths(10, 10));
    }

    @Test
    public void case7() {
        Assert.assertEquals(193536720, uniquePaths(23, 12));
    }

}
