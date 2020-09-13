package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * @author donglin
 * @since 2020-09-13
 */
public class Exist {

    @Test
    public void case1() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        Assert.assertTrue(exist(board, "ABCCE"));
        Assert.assertTrue(exist(board, "SEE"));
        Assert.assertFalse(exist(board, "ABCB"));
    }

    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        int row = board.length;
        int col = board[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (exist(board, r, c, chars, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, int r, int c, char[] word, int i) {
        if (board[r][c] != word[i]) {
            return false;
        }
        i++;
        if (i == word.length) {
            return true;
        } else if (i > word.length) {
            return false;
        }
        board[r][c] = ' ';
        if (r > 0 && exist(board, r - 1, c, word, i)) {
            return true;
        }
        if (r < board.length - 1 && exist(board, r + 1, c, word, i)) {
            return true;
        }
        if (c > 0 && exist(board, r, c - 1, word, i)) {
            return true;
        }
        if (c < board[0].length - 1 && exist(board, r, c + 1, word, i)) {
            return true;
        }
        board[r][c] = word[i - 1];
        return false;
    }

}
