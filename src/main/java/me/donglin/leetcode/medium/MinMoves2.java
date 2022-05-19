package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 462. 最少移动次数使数组元素相等 II
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 *
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：
 * 只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 *
 * 示例 2：
 * 输入：nums = [1,10,2,9]
 * 输出：16
 *
 * 提示：
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * @author donglin
 * @since 2022-05-19
 */
public class MinMoves2 {

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int middle = nums[nums.length / 2];
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - middle);
        }
        return moves;
    }

    @Test
    public void case1() {
        Assert.assertEquals(2, minMoves2(new int[]{1, 2, 3}));
    }

    @Test
    public void case2() {
        Assert.assertEquals(16, minMoves2(new int[]{1, 10, 2, 9}));
    }

    @Test
    public void case3() {
        Assert.assertEquals(14, minMoves2(new int[]{1, 0, 0, 8, 6}));
    }


}
