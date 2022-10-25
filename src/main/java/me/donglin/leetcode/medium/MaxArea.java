package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 11. 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 * @author donglin
 * @since 2022-10-25
 */
public class MaxArea {

    public int maxArea(int[] height) {
        // 双指针循坏收窄，排除不可能的状态
        int n = height.length;
        int l = 0, r = n - 1;
        int area = 0;
        while (l < r) {
            area = height[l] < height[r]
                    ? Math.max(area, (r - l) * height[l++])
                    : Math.max(area, (r - l) * height[r--]);
        }
        return area;
    }

    @Test
    public void case1() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        Assert.assertEquals(49, maxArea(height));
    }

    @Test
    public void case2() {
        int[] height = {1, 1};
        Assert.assertEquals(1, maxArea(height));
    }

}
