package me.donglin.leetcode.hard;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 51. N 皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例：
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 提示：
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 * @author donglin
 * @since 2020-09-03
 */
public class SolveNQueens {

    @Test
    public void case1() {
        List<List<String>> expected = Arrays.asList(
                Arrays.asList(".Q..", "...Q", "Q...", "..Q."),
                Arrays.asList("..Q.", "Q...", "...Q", ".Q..")
        );
        Assert.assertTrue(CommonUtil.isEquals(expected, solveNQueens(4)));
    }

    @Test
    public void case2() {
        List<List<String>> expected = Collections.singletonList(Collections.singletonList("Q"));
        Assert.assertTrue(CommonUtil.isEquals(expected, solveNQueens(1)));
    }

    @Test
    public void case3() {
        List<List<String>> expected = Collections.emptyList();
        Assert.assertTrue(CommonUtil.isEquals(expected, solveNQueens(3)));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // 第row行第cols[row]列上有皇后
        int[] cols = new int[n];
        // usefulCol 存的是还有没皇后的列
        Set<Integer> usefulCol = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            usefulCol.add(i);
        }
        Arrays.fill(cols, -1);
        for (int i = 0; i < n; i++) {
            cols[0] = i;
            usefulCol.remove(i);
            solveNQueens(1, cols, result, usefulCol);
            usefulCol.add(i);
        }
        return result;
    }

    public void solveNQueens(int row, int[] cols, List<List<String>> result, Set<Integer> usefulCol) {
        if (row == cols.length) {
            result.add(generateResultItem(cols));
            return;
        }
        if (cols[row] != -1) {
            return;
        }
        Integer[] canSelectCols = usefulCol.toArray(new Integer[0]);
        for (int i : canSelectCols) {
            boolean find = true;
            for (int k = 0; k < row; k++) {
                if (row - k == Math.abs(cols[k] - i)) {
                    // 在同一斜线上
                    find = false;
                    break;
                }
            }
            if (find) {
                cols[row] = i;
                usefulCol.remove(i);
                solveNQueens(row + 1, cols, result, usefulCol);
                usefulCol.add(i);
                cols[row] = -1;
            }
        }
    }

    private List<String> generateResultItem(int[] cols) {
        char[] chars = new char[cols.length];
        Arrays.fill(chars, '.');
        List<String> result = new ArrayList<>();
        for (int i : cols) {
            chars[i] = 'Q';
            result.add(new String(chars));
            chars[i] = '.';
        }
        return result;
    }
}
