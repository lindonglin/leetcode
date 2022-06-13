package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1051. 高度检查器
 * 学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度顺序排成一行。
 * 排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。
 * 给你一个整数数组 heights ，表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。
 * 返回满足 heights[i] != expected[i] 的 下标数量 。
 *
 * 示例：
 * 输入：heights = [1,1,4,2,1,3]
 * 输出：3
 * 解释：
 * 高度：[1,1,4,2,1,3]
 * 预期：[1,1,1,2,3,4]
 * 下标 2 、4 、5 处的学生高度不匹配。
 *
 * 示例 2：
 * 输入：heights = [5,1,2,3,4]
 * 输出：5
 * 解释：
 * 高度：[5,1,2,3,4]
 * 预期：[1,2,3,4,5]
 * 所有下标的对应学生高度都不匹配。
 *
 * 示例 3：
 * 输入：heights = [1,2,3,4,5]
 * 输出：0
 * 解释：
 * 高度：[1,2,3,4,5]
 * 预期：[1,2,3,4,5]
 * 所有下标的对应学生高度都匹配。
 *
 * 提示：
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 *
 * @author donglin
 * @since 2022-06-13
 */
public class HeightChecker {

    public int heightChecker(int[] heights) {
        int[] sort = new int[101];
        for (int h : heights) {
            sort[h]++;
        }
        int res = 0;
        int i = 0;
        for (int h = 1; h < sort.length; h++) {
            for (int k = 1; k <= sort[h]; k++) {
                if (heights[i++] != h) {
                    res++;
                }
            }
        }
        return res;
    }

    @Test
    public void case1() {
        Assert.assertEquals(3, heightChecker(new int[]{1, 1, 4, 2, 1, 3}));
    }

    @Test
    public void case2() {
        Assert.assertEquals(5, heightChecker(new int[]{5, 1, 2, 3, 4}));
    }

    @Test
    public void case3() {
        Assert.assertEquals(0, heightChecker(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void case4() {
        Assert.assertEquals(2, heightChecker(new int[]{1, 2, 5, 4, 5}));
    }
}
