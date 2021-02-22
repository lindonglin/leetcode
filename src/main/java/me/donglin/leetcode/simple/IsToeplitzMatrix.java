package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 766. 托普利茨矩阵
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * 输出：true
 * 解释：
 * 在上述矩阵中, 其对角线为:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 * 各条对角线上的所有元素均相同, 因此答案是 True 。
 *
 * 示例 2：
 * 输入：matrix = [[1,2],[2,2]]
 * 输出：false
 * 解释：
 * 对角线 "[1, 2]" 上的元素不同。
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 *
 * 进阶：
 * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
 * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 *
 * @author donglin
 * @version 1.0
 * @since 2021/02/22
 */
public class IsToeplitzMatrix {


    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 1; i < n; i++) {
            for (int k = 1; k < m; k++) {
                if (matrix[i][k] != matrix[i-1][k-1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中
     */
    public boolean isToeplitzMatrixAdvanced(int[][] matrix) {
        // 假如一次只能载入1/3行
        int onceSize = Math.max(2, matrix[0].length / 3);
        return isToeplitzMatrixAdvanced(matrix, onceSize);
    }

    /**
     * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中
     * @param onceSize 一次只能载入一行的多少数据到内存
     */
    private boolean isToeplitzMatrixAdvanced(int[][] matrix, int onceSize) {
        int n = matrix.length;
        int m = matrix[0].length;
        int start = 0;
        int end = onceSize;
        OncePartlyRowHandler handler = new OncePartlyRowHandler();
        while (start + 1 < m) {
            handler.nextPartly(matrix[0], start, end);
            for (int i = 1; i < n; i++) {
                if (!handler.validate(matrix[i])) {
                    return false;
                }
            }
            start = end - 1;
            end = Math.min(end + onceSize, m);
        }
        return true;
    }


    /**
     * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中
     */
    private static class OncePartlyRowHandler {

        /**
         * 上一行
         */
        private int[] partlyRow;

        private int start;
        private int end;


        public void nextPartly(int[] partlyRow, int start, int end) {
            this.partlyRow = partlyRow;
            this.start = start;
            this.end = end;
        }

        public boolean validate(int[] nextPartlyRow) {
            for (int i = start + 1; i < end; i++) {
                if (nextPartlyRow[i] != partlyRow[i-1]) {
                    return false;
                }
            }
            partlyRow = nextPartlyRow;
            return true;
        }
    }



    @Test
    public void case1() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
        Assert.assertTrue(isToeplitzMatrix(matrix));
        Assert.assertTrue(isToeplitzMatrixAdvanced(matrix));
    }

    @Test
    public void case2() {
        int[][] matrix = {{1, 2}, {2, 2}};
        Assert.assertFalse(isToeplitzMatrix(matrix));
        Assert.assertFalse(isToeplitzMatrixAdvanced(matrix));
    }

    @Test
    public void case3() {
        int[][] matrix = {{18, 66}};
        Assert.assertTrue(isToeplitzMatrix(matrix));
        Assert.assertTrue(isToeplitzMatrixAdvanced(matrix));
    }

    @Test
    public void case4() {
        int[][] matrix = {{1}};
        Assert.assertTrue(isToeplitzMatrix(matrix));
        Assert.assertTrue(isToeplitzMatrixAdvanced(matrix));
    }
}
