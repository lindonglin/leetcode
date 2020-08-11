package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * @author donglin
 * @since 2020-08-11
 */
public class SurroundedArea {

    private static final char EVENTUALLY_CHANGED_TO_O = 0;

    @Test
    public void case1() {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        char[][] expected = {
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solve(board);
        Assert.assertTrue(CommonUtil.isEquals(board, expected));

    }


    public void solve(char[][] board) {
        if (board.length < 3 || board[0].length < 3) {
            return;
        }
        int n = board.length - 1;
        int m = board[0].length - 1;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i <= m; i++) {
            if (board[0][i] == 'O') {
                queue.add(new int[]{0, i});
            }
            if (board[n][i] == 'O') {
                queue.add(new int[]{n, i});
            }
        }
        for (int k = 0; k <= n; k++) {
            if (board[k][0] == 'O') {
                queue.add(new int[]{k, 0});
            }
            if (board[k][m] == 'O') {
                queue.add(new int[]{k, m});
            }
        }

        int[] position;
        while (!queue.isEmpty()) {
            position = queue.poll();
            int i = position[0];
            int k = position[1];
            board[i][k] = EVENTUALLY_CHANGED_TO_O;
            if (i - 1 >= 0 && board[i - 1][k] == 'O') {
                queue.add(new int[]{i - 1, k});
            }
            if (i + 1 <= n && board[i + 1][k] == 'O') {
                queue.add(new int[]{i + 1, k});
            }
            if (k - 1 >= 0 && board[i][k - 1] == 'O') {
                queue.add(new int[]{i, k - 1});
            }
            if (k + 1 <= m && board[i][k + 1] == 'O') {
                queue.add(new int[]{i, k + 1});
            }
        }
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= m; k++) {
                if (board[i][k] == EVENTUALLY_CHANGED_TO_O) {
                    board[i][k] = 'O';
                } else {
                    board[i][k] = 'X';
                }
            }
        }
    }
}
