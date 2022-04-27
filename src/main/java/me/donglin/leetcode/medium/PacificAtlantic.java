package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ，
 * heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。
 * 水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
 *
 * 提示：
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 *
 * @author donglin
 * @since 2022-04-27
 */
public class PacificAtlantic {

    private int m;
    private int n;
    private int[][] heights;
    /**
     * pacificFlag[i][j]表示雨水从单元格(ri, ci)流入太平洋的情况
     * 1表示可以流入
     * -1表示不可以
     * 0则是还未处理完
     * atlanticFlag同理，只不过是大西洋的
     */
    private int[][] pacificFlag;
    private int[][] atlanticFlag;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        m = heights.length;
        n = heights[0].length;
        pacificFlag = new int[m][n];
        atlanticFlag = new int[m][n];
        initPacificFlag();
        initAtlanticFlag();
        handlePacific();
        handleAtlantic();
        return merge();
    }

    private void initPacificFlag() {
        for (int i = 0; i < m; i++) {
            pacificFlag[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            pacificFlag[0][j] = 1;
        }
    }

    private void initAtlanticFlag() {
        for (int i = 0; i < m; i++) {
            atlanticFlag[i][n - 1] = 1;
        }
        for (int j = 0; j < n; j++) {
            atlanticFlag[m - 1][j] = 1;
        }
    }

    private void handlePacific() {
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                handle(pacificFlag, i, j);
            }
        }
    }

    private void handleAtlantic() {
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                handle(atlanticFlag, i, j);
            }
        }
    }

    private List<List<Integer>> merge() {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificFlag[i][j] == 1 && atlanticFlag[i][j] == 1) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private int handle(int[][] flag, int i, int j) {
        if (handle(flag, i, j, i - 1, j)
                || handle(flag, i, j, i, j - 1)
                || handle(flag, i, j, i + 1, j)
                || handle(flag, i, j, i, j + 1)) {
            return 1;
        }
        return -1;
    }

    private boolean handle(int[][] flag, int i, int j, int ni, int nj) {
        if (flag[i][j] != 0) {
            return flag[i][j] == 1;
        }
        if (ni < 0 || ni >= m || nj < 0 || nj >= n) {
            return false;
        }
        int value = heights[i][j];
        if (value < heights[ni][nj]) {
            return false;
        }
        int nFlag = flag[ni][nj];
        if (nFlag == 1) {
            flag[i][j] = 1;
            return true;
        }
        if (nFlag == -1) {
            return false;
        }
        // 防无限递归
        flag[i][j] = -1;
        nFlag = handle(flag, ni, nj);
        if (nFlag == 1) {
            flag[i][j] = 1;
        } else {
            flag[i][j] = 0;
        }
        return flag[i][j] == 1;
    }


    @Test
    public void case1() {
        List<List<Integer>> expect = Arrays.asList(
                Arrays.asList(0, 4),
                Arrays.asList(1, 3),
                Arrays.asList(1, 4),
                Arrays.asList(2, 2),
                Arrays.asList(3, 0),
                Arrays.asList(3, 1),
                Arrays.asList(4, 0)
        );
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        Assert.assertTrue(CommonUtil.isEqualListLists(expect, pacificAtlantic(heights)));
    }

    @Test
    public void case2() {
        List<List<Integer>> expect = Arrays.asList(
                Arrays.asList(0, 0),
                Arrays.asList(0, 1),
                Arrays.asList(1, 0),
                Arrays.asList(1, 1)
        );
        int[][] heights = {{2, 1}, {1, 2}};
        Assert.assertTrue(CommonUtil.isEqualListLists(expect, pacificAtlantic(heights)));
    }

    @Test
    public void case3() {
        List<List<Integer>> expect = Collections.singletonList(
                Arrays.asList(0, 0)
        );
        int[][] heights = {{1}};
        Assert.assertTrue(CommonUtil.isEqualListLists(expect, pacificAtlantic(heights)));
    }

    @Test
    public void case4() {
        List<List<Integer>> expect = Arrays.asList(
                Arrays.asList(0, 16),
                Arrays.asList(1, 16),
                Arrays.asList(2, 13),
                Arrays.asList(2, 14),
                Arrays.asList(2, 15),
                Arrays.asList(36, 1),
                Arrays.asList(37, 1),
                Arrays.asList(37, 2),
                Arrays.asList(38, 2),
                Arrays.asList(39, 0),
                Arrays.asList(39, 1),
                Arrays.asList(40, 0),
                Arrays.asList(40, 1)
        );
        int[][] heights = {
                {4, 4, 1, 7, 4, 18, 5, 5, 1, 6, 6, 10, 17, 19, 13, 3, 19},
                {19, 8, 3, 14, 18, 11, 2, 2, 5, 2, 19, 15, 18, 12, 16, 7, 19},
                {2, 4, 15, 2, 6, 4, 18, 13, 12, 11, 0, 11, 6, 19, 17, 11, 9},
                {10, 2, 0, 7, 13, 3, 7, 0, 5, 4, 10, 2, 3, 18, 10, 8, 10},
                {13, 16, 8, 5, 15, 12, 8, 14, 16, 18, 18, 19, 10, 14, 9, 4, 12},
                {0, 16, 14, 14, 13, 15, 2, 16, 1, 13, 17, 9, 6, 11, 17, 4, 13},
                {7, 9, 1, 5, 18, 15, 2, 1, 13, 3, 0, 7, 8, 8, 9, 12, 0},
                {7, 13, 14, 5, 3, 16, 5, 4, 5, 3, 9, 11, 11, 3, 1, 17, 12},
                {8, 18, 17, 9, 1, 0, 18, 7, 16, 15, 14, 14, 16, 8, 11, 13, 10},
                {14, 19, 19, 19, 19, 12, 2, 17, 17, 8, 10, 19, 16, 7, 10, 12, 17},
                {7, 0, 5, 2, 10, 7, 1, 0, 15, 3, 5, 2, 14, 16, 17, 9, 10},
                {11, 10, 15, 4, 17, 11, 17, 14, 18, 11, 17, 15, 19, 1, 9, 7, 17},
                {10, 8, 12, 15, 13, 3, 15, 14, 5, 4, 4, 4, 0, 11, 16, 14, 15},
                {16, 5, 3, 5, 13, 1, 6, 3, 8, 9, 3, 18, 11, 9, 7, 5, 14},
                {5, 7, 14, 13, 6, 12, 10, 6, 6, 12, 5, 0, 2, 0, 0, 15, 19},
                {14, 11, 17, 13, 6, 11, 15, 0, 6, 4, 5, 1, 15, 1, 19, 0, 14},
                {17, 5, 0, 0, 10, 13, 4, 10, 17, 5, 5, 6, 16, 19, 1, 11, 0},
                {14, 18, 11, 8, 0, 1, 0, 11, 1, 7, 15, 11, 4, 5, 18, 14, 19},
                {17, 10, 17, 17, 1, 17, 16, 16, 19, 15, 0, 14, 15, 2, 1, 18, 4},
                {12, 13, 0, 15, 16, 3, 1, 7, 10, 9, 0, 2, 13, 4, 7, 1, 15},
                {9, 6, 17, 12, 6, 19, 2, 15, 3, 14, 10, 15, 10, 11, 15, 13, 10},
                {2, 19, 4, 12, 19, 5, 18, 9, 4, 5, 1, 9, 17, 8, 14, 12, 15},
                {5, 1, 6, 17, 0, 15, 13, 3, 14, 13, 15, 4, 15, 11, 7, 15, 4},
                {14, 5, 6, 9, 11, 6, 4, 10, 16, 11, 6, 6, 0, 17, 13, 1, 10},
                {5, 18, 3, 15, 10, 6, 10, 6, 12, 5, 13, 2, 5, 7, 3, 3, 19},
                {11, 7, 17, 17, 8, 2, 11, 3, 0, 7, 16, 13, 7, 0, 12, 11, 14},
                {18, 1, 19, 7, 14, 9, 8, 2, 3, 16, 7, 9, 16, 4, 18, 8, 3},
                {5, 8, 19, 0, 10, 12, 9, 12, 10, 2, 11, 3, 15, 8, 18, 12, 3},
                {15, 2, 4, 9, 4, 4, 18, 12, 6, 10, 10, 3, 6, 6, 17, 3, 14},
                {18, 17, 13, 0, 12, 15, 3, 2, 15, 17, 8, 16, 8, 7, 17, 18, 2},
                {5, 12, 12, 17, 18, 11, 8, 6, 13, 13, 14, 18, 17, 9, 16, 8, 5},
                {16, 1, 9, 13, 6, 12, 15, 3, 12, 6, 2, 14, 10, 16, 11, 3, 8},
                {0, 7, 16, 3, 7, 0, 10, 10, 7, 3, 4, 18, 7, 18, 7, 15, 12},
                {8, 7, 12, 17, 8, 8, 18, 11, 7, 12, 18, 2, 19, 6, 6, 3, 13},
                {6, 19, 14, 9, 16, 9, 13, 13, 4, 11, 0, 0, 3, 1, 9, 10, 7},
                {0, 8, 14, 13, 1, 7, 7, 7, 2, 15, 12, 6, 10, 10, 3, 14, 8},
                {10, 17, 1, 8, 8, 16, 14, 18, 18, 1, 10, 12, 3, 13, 10, 6, 5},
                {8, 17, 17, 6, 2, 14, 5, 3, 7, 5, 4, 5, 13, 14, 17, 14, 15},
                {2, 6, 12, 4, 14, 11, 14, 3, 12, 10, 12, 15, 9, 7, 0, 4, 5},
                {13, 11, 5, 13, 12, 3, 19, 10, 16, 8, 3, 11, 7, 10, 0, 5, 18},
                {0, 18, 1, 8, 19, 11, 0, 1, 2, 19, 14, 11, 10, 15, 12, 3, 15}
        };
        List<List<Integer>> result = pacificAtlantic(heights);
        System.out.println(result);
        Assert.assertTrue(CommonUtil.isEqualListLists(expect, result));
    }

    @Test
    public void case5() {
        List<List<Integer>> expect = Arrays.asList(
                Arrays.asList(0, 13),
                Arrays.asList(0, 14),
                Arrays.asList(1, 13),
                Arrays.asList(11, 3),
                Arrays.asList(12, 0),
                Arrays.asList(12, 2),
                Arrays.asList(12, 3),
                Arrays.asList(13, 0),
                Arrays.asList(13, 1),
                Arrays.asList(13, 2),
                Arrays.asList(14, 0),
                Arrays.asList(15, 0)
        );
        int[][] heights = {{8, 13, 11, 18, 14, 16, 16, 4, 4, 8, 10, 11, 1, 19, 7},
                {2, 9, 15, 16, 14, 5, 8, 15, 9, 5, 14, 6, 10, 15, 5},
                {15, 16, 17, 10, 3, 6, 3, 4, 2, 17, 0, 12, 4, 1, 3},
                {13, 6, 13, 15, 15, 16, 4, 10, 7, 4, 19, 19, 4, 9, 13},
                {7, 1, 9, 14, 9, 11, 5, 4, 15, 19, 6, 0, 0, 13, 5},
                {9, 9, 15, 12, 15, 5, 1, 1, 18, 1, 2, 16, 15, 18, 9},
                {13, 0, 4, 18, 12, 0, 11, 0, 1, 15, 1, 15, 4, 2, 0},
                {11, 13, 12, 16, 9, 18, 6, 8, 18, 1, 5, 12, 17, 13, 5},
                {7, 17, 2, 5, 0, 17, 9, 18, 4, 13, 6, 13, 7, 2, 1},
                {2, 3, 9, 0, 19, 6, 6, 15, 14, 4, 8, 1, 19, 5, 9},
                {3, 10, 5, 11, 7, 14, 1, 5, 3, 19, 12, 5, 2, 13, 16},
                {0, 8, 10, 18, 17, 5, 5, 8, 2, 11, 5, 16, 4, 9, 14},
                {15, 9, 16, 18, 9, 5, 2, 5, 13, 3, 10, 19, 9, 14, 3},
                {12, 11, 16, 1, 10, 12, 6, 18, 6, 6, 18, 10, 9, 5, 2},
                {17, 9, 6, 6, 14, 9, 2, 2, 13, 13, 15, 17, 15, 3, 14},
                {18, 14, 12, 6, 18, 16, 4, 10, 19, 5, 6, 8, 9, 1, 6}};
        List<List<Integer>> result = pacificAtlantic(heights);
        System.out.println(result);
        Assert.assertTrue(CommonUtil.isEqualListLists(expect, result));
    }

}
