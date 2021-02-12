package me.donglin.leetcode.simple;


import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * @author donglin
 * @since 2020-09-09
 */
public class GetRow {

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return Collections.singletonList(1);
        }
        Integer[] arr = new Integer[rowIndex + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;
        for (int k = 1; k < arr.length; k++) {
            for (int i = k; i > 0; i--) {
                arr[i] = arr[i] + arr[i - 1];
            }
        }
        return Arrays.asList(arr);
    }

    /**
     * 组合数方法
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> result = new ArrayList<>(rowIndex + 1);
        long cur = 1;
        for (int i = 0; i <= rowIndex; i++) {
            result.add((int) cur);
            cur = cur * (rowIndex - i) / (i + 1);
        }
        return result;
    }

    @Test
    public void case1() {
        int rowIndex = 3;
        List<Integer> expected = Arrays.asList(1, 3, 3, 1);
        Assert.assertTrue(CommonUtil.isEquals(expected, getRow(rowIndex)));
        Assert.assertTrue(CommonUtil.isEquals(expected, getRow2(rowIndex)));
    }


    @Test
    public void case2() {
        int rowIndex = 4;
        List<Integer> expected = Arrays.asList(1, 4, 6, 4, 1);
        Assert.assertTrue(CommonUtil.isEquals(expected, getRow(rowIndex)));
        Assert.assertTrue(CommonUtil.isEquals(expected, getRow2(rowIndex)));
    }
}
