package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * @author donglin
 * @since 2020-07-14
 */
public class MinimumTotal {


    @Test
    public void case1() {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        Assert.assertEquals(11, minimumTotal(triangle));
    }

    @Test
    public void case2() {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(-1));
        triangle.add(Arrays.asList(-2, -3));
        Assert.assertEquals(-4, minimumTotal(triangle));
    }

    @Test
    public void case3() {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(-7));
        triangle.add(Arrays.asList(-2, -1));
        triangle.add(Arrays.asList(-5, -5, 9));
        triangle.add(Arrays.asList(4, -5, 4, 4));
        triangle.add(Arrays.asList(-6, -6, 2, -1, -5));
        triangle.add(Arrays.asList(3, 7, 8, -3, 7, -9));
        triangle.add(Arrays.asList(-9, -1, -9, 6, 9, 0, 7));
        triangle.add(Arrays.asList(-7, 0, -6, -8, 7, 1, -4, 9));
        triangle.add(Arrays.asList(-3, 2, -6, -9, -7, -6, -9, 4, 0));
        triangle.add(Arrays.asList(-8, -6, -3, -9, -2, -6, 7, -5, 0, 7));
        triangle.add(Arrays.asList(-9, -1, -2, 4, -2, 4, 4, -1, 2, -5, 5));
        triangle.add(Arrays.asList(1, 1, -6, 1, -2, -4, 4, -2, 6, -6, 0, 6));
        triangle.add(Arrays.asList(-3, -3, -6, -2, -6, -2, 7, -9, -5, -7, -5, 5, 1));
        Assert.assertEquals(-63, minimumTotal(triangle));
    }

    /**
     * 时间复杂度O(n^2). 空间复杂度O(n)
     */
    private int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return 0;
        }
        int n = triangle.size();
        // total[i] 表示从三角形上顶点当前遍历到的行的第i个元素最小路径和
        // 显然，遍历后最后一行后，total里的最小值就是所要求的结果
        int[] total = new int[n];
        total[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> nums = triangle.get(i);
            total[i] = total[i - 1] + nums.get(i);
            for (int k = i - 1; k > 0; k--) {
                total[k] = Math.min(total[k-1], total[k]) + nums.get(k);
            }
            total[0] += nums.get(0);
        }
        int min = total[0];
        for (int t : total) {
            if (t < min) {
                min = t;
            }
        }
        return min;
    }
}
