package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * @author donglin
 * @since 2020/07/23
 */
public class MinPathSum {

    @Test
    public void case1() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        Assert.assertEquals(7, minPathSum(grid));
    }

    @Test
    public void case2() {
        int[][] grid = {{2, 1}};
        Assert.assertEquals(3, minPathSum(grid));
    }

    @Test
    public void case3() {
        int[][] grid = {{2}, {1}};
        Assert.assertEquals(3, minPathSum(grid));
    }

    @Test
    public void case4() {
        int[][] grid = {{0}};
        Assert.assertEquals(0, minPathSum(grid));
    }

    public int minPathSum(int[][] grid) {
        int rowSize = grid.length - 1;
        int colSize = rowSize >= 0 ? grid[0].length - 1: 0;
        Integer[][] minPathSums = new Integer[rowSize + 1][colSize + 1];
        minPathSums[rowSize][colSize] = grid[rowSize][colSize];
        return minPathSum(0, 0, rowSize, colSize, grid, minPathSums);
    }

    private int minPathSum(int row, int col, int n, int m, int[][] grid, Integer[][] minPathSums) {
        if (row > n || col > m) {
            return Integer.MAX_VALUE;
        }
        if (minPathSums[row][col] !=  null) {
            return minPathSums[row][col];
        }
        int toRight = minPathSum(row, col + 1, n, m, grid, minPathSums);
        int toDown = minPathSum(row + 1, col, n, m, grid, minPathSums);
        minPathSums[row][col] = grid[row][col] + Math.min(toRight, toDown);
        return minPathSums[row][col];
    }
}
