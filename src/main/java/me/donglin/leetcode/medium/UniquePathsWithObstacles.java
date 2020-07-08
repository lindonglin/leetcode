package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 63. 不同路径 II
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * @author donglin
 * @since 2020/07/06
 */
public class UniquePathsWithObstacles {

    @Test
    public void case1() {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        Assert.assertEquals(2, uniquePathsWithObstacles(obstacleGrid));
    }


    @Test
    public void case2() {
        int[][] obstacleGrid = {{0}};
        Assert.assertEquals(1, uniquePathsWithObstacles(obstacleGrid));
    }

    @Test
    public void case3() {
        int[][] obstacleGrid = {{0, 0}, {0, 0}};
        Assert.assertEquals(2, uniquePathsWithObstacles(obstacleGrid));
    }

    @Test
    public void case4() {
        int[][] obstacleGrid = {{0, 0}, {1, 0}};
        Assert.assertEquals(1, uniquePathsWithObstacles(obstacleGrid));
    }

    @Test
    public void case5() {
        int[][] obstacleGrid = {{0, 1}, {1, 0}};
        Assert.assertEquals(0, uniquePathsWithObstacles(obstacleGrid));
    }

    @Test
    public void case6() {
        int[][] obstacleGrid = {{1}};
        Assert.assertEquals(0, uniquePathsWithObstacles(obstacleGrid));
    }

    @Test
    public void case7() {
        int[][] obstacleGrid = {{0, 1}};
        Assert.assertEquals(0, uniquePathsWithObstacles(obstacleGrid));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] pathCounts = new int[n][m];
        return uniquePathsWithObstacles(0, 0, n - 1, m - 1, obstacleGrid, pathCounts);
    }

    private int uniquePathsWithObstacles(int row, int col, int n, int m, int[][] obstacleGrid, int[][] pathCounts) {
       if (row > n || col > m) {
            return 0;
        }
        if (pathCounts[row][col] > 0) {
            return pathCounts[row][col];
        }
        if (obstacleGrid[row][col] == 1 || pathCounts[row][col] == -1) {
            pathCounts[row][col] = -1;
            return 0;
        }
        if (row == n && col == m) {
            return obstacleGrid[n][m] == 0 ? 1 : 0;
        }
        int pathCount = uniquePathsWithObstacles(row, col + 1, n, m, obstacleGrid, pathCounts)
                + uniquePathsWithObstacles(row + 1, col, n, m, obstacleGrid, pathCounts);
        pathCounts[row][col] = pathCount > 0 ? pathCount : -1;
        return pathCount;
    }
}
