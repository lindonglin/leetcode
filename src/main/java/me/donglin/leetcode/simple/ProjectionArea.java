package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 883. 三维形体投影面积
 * 在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的 1 x 1 x 1 立方体。
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
 * 现在，我们查看这些立方体在 xy 、yz 和 zx 平面上的投影。
 * 投影 就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。
 * 返回 所有三个投影的总面积 。
 *
 *
 * 示例 1：
 * 输入：[[1,2],[3,4]]
 * 输出：17
 * 解释：这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
 *
 * 示例 2:
 * 输入：grid = [[2]]
 * 输出：5
 *
 * 示例 3：
 * 输入：[[1,0],[0,2]]
 * 输出：8
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] <= 50
 *
 * @author donglin
 * @since 2022-04-26
 */
public class ProjectionArea {

    public int projectionArea(int[][] grid) {
        int m = grid[0].length;
        int max = 0;
        int area = 0;
        for (int[] ints : grid) {
            for (int z : ints) {
                max = Math.max(max, z);
                if (z > 0) {
                    area++;
                }
            }
            area += max;
            max = 0;

        }
        for (int y = 0; y < m; y++) {
            for (int[] ints : grid) {
                max = Math.max(max, ints[y]);
            }
            area += max;
            max = 0;
        }
        return area;
    }

    @Test
    public void case1() {
        Assert.assertEquals(17, projectionArea(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    public void case2() {
        Assert.assertEquals(5, projectionArea(new int[][]{{2}}));

    }

    @Test
    public void case3() {
        Assert.assertEquals(8, projectionArea(new int[][]{{1, 0}, {0, 2}}));
    }

    @Test
    public void case4() {
        Assert.assertEquals(14, projectionArea(new int[][]{{1, 4}, {1, 1}}));
    }

}
