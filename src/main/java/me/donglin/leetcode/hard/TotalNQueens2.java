package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 52. N皇后 II
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * <p>
 * 示例:
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * <p>
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。
 * 当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 *
 * @author donglin
 * @since 2020-10-17
 */
public class TotalNQueens2 {

    private int totalCount;
    /**
     * row行的cols[row]列上有皇后
     */
    private int[] cols;
    /**
     * hasQueen[col]上是否有皇后了
     */
    private boolean[] hasQueen;

    public int totalNQueens(int n) {
        cols = new int[n];
        hasQueen = new boolean[n];
        totalCount = 0;
        Arrays.fill(cols, -1);
        int i = 0;
        for (int size = n / 2; i < size; i++) {
            preFind(0, i);
        }
        totalCount *= 2;
        if (n % 2 == 1) {
            preFind(0, i);
        }
        return totalCount;
    }

    private void preFind(int row, int col) {
        cols[row] = col;
        hasQueen[col] = true;
        find(row + 1);
        hasQueen[col] = false;
    }

    private void find(int row) {
        if (row == cols.length) {
            totalCount += 1;
            return;
        }
        for (int i = 0; i < hasQueen.length; i++) {
            if (hasQueen[i]) {
                continue;
            }
            boolean find = true;
            for (int k = 0; k < row; k++) {
                if (row - k == Math.abs(cols[k] - i)) {
                    // 在同一斜线上
                    find = false;
                    break;
                }
            }
            if (find) {
                preFind(row, i);
            }
        }
    }

    @Test
    public void case1() {
        Assert.assertEquals(1, totalNQueens(1));
    }

    @Test
    public void case2() {
        Assert.assertEquals(0, totalNQueens(2));
    }

    @Test
    public void case3() {
        Assert.assertEquals(0, totalNQueens(3));
    }

    @Test
    public void case4() {
        Assert.assertEquals(2, totalNQueens(4));
    }

}
