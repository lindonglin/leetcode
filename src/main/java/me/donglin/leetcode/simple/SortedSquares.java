package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 977. 有序数组的平方
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 * 示例 1：
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 *
 * 示例 2：
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 *
 * @author donglin
 * @since 2020/10/16
 */
public class SortedSquares {

    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        int k = 0;
        while (k < A.length && A[k] < 0) {
            k++;
        }
        int[] result = new int[A.length];
        int i = 0;
        int j = k - 1;
        while (j >= 0 && k < A.length) {
            if (A[j] + A[k] > 0) {
                result[i++] = A[j] * A[j--];
            } else {
                result[i++] = A[k] * A[k++];
            }
        }
        while (j >= 0) {
            result[i++] = A[j] * A[j--];
        }
        while (k < A.length) {
            result[i++] = A[k] * A[k++];
        }
        return result;
    }


    @Test
    public void case1() {
        int[] A = {-4, -1, 0, 3, 10};
        int[] expected = {0, 1, 9, 16, 100};
        Assert.assertArrayEquals(expected, sortedSquares(A));
    }


    @Test
    public void case2() {
        int[] A = {-7, -3, 2, 3, 11};
        int[] expected = {4, 9, 9, 49, 121};
        Assert.assertArrayEquals(expected, sortedSquares(A));
    }
}
