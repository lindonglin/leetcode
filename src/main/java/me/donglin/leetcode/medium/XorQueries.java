package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1310. 子数组异或查询
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 * 并返回一个包含给定查询 queries 所有结果的数组。
 *
 * 示例 1：
 * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * 输出：[2,7,14,8]
 * 解释：
 * 数组中元素的二进制表示形式是：
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * 查询的 XOR 值为：
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 *
 * 示例 2：
 * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * 输出：[8,0,4,4]
 *
 *  提示：
 *  1 <= arr.length <= 3 * 10^4
 *  1 <= arr[i] <= 10^9
 *  1 <= queries.length <= 3 * 10^4
 *  queries[i].length == 2
 *  0 <= queries[i][0] <= queries[i][1] < arr.length
 *
 * @author donglin
 * @version 1.0
 * @since 2021/05/12
 */
public class XorQueries {

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] ^ arr[i - 1];
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = dp[queries[i][0]] ^ dp[queries[i][1] + 1];
        }
        return result;
    }

    @Test
    public void case1() {
        int[] arr = {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
        int[] expect = {2, 7, 14, 8};
        Assert.assertArrayEquals(expect, xorQueries(arr, queries));
    }
    @Test
    public void case2() {
        int[] arr = {16};
        int[][] queries = {{0, 0}, {0, 0}, {0, 0}};
        int[] expect = {16, 16, 16};
        Assert.assertArrayEquals(expect, xorQueries(arr, queries));
    }
}
