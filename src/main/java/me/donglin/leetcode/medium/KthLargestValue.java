package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;


/**
 * 1738. 找出第 K 大的异或坐标值
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 *
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 *
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 *
 * 示例 1：
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 *
 * 示例 2：
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 *
 * 示例 3：
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 *
 * 示例 4：
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 10^6
 * 1 <= k <= m * n
 *
 * @author donglin
 * @version 1.0
 * @since 2021/05/19
 */
public class KthLargestValue {

    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length + 1;
        int n = matrix[0].length + 1;
        int[][] dp = new int[m][n];
        dp[1][1] = matrix[0][0];
        int[] counts = new int[1000001];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j-1] ^ dp[i-1][j] ^ dp[i-1][j-1] ^ matrix[i-1][j-1];
                counts[dp[i][j]]++;
            }
        }
        int c = 0;
        for (int i = counts.length - 1; i >= 0; i--) {
            c += counts[i];
            if (c >= k) {
                return i;
            }
        }
        return 0;
    }

    @Test
    public void case1() {
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 1;
        Assert.assertEquals(7, kthLargestValue(matrix, k));
    }

    @Test
    public void case2() {
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 2;
        Assert.assertEquals(5, kthLargestValue(matrix, k));
    }

    @Test
    public void case3() {
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 3;
        Assert.assertEquals(4, kthLargestValue(matrix, k));
    }

    @Test
    public void case4() {
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 4;
        Assert.assertEquals(0, kthLargestValue(matrix, k));
    }
}
