package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 *
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 * @author donglin
 * @since 2020-06-27
 */
public class FirstMissingPositive {


    @Test
    public void case1() {
        Assert.assertEquals(3, firstMissingPositive(new int[]{1, 2, 0}));
        Assert.assertEquals(2, firstMissingPositive(new int[]{3, 4, -1, 1}));
        Assert.assertEquals(5, firstMissingPositive(new int[]{2, 3, 4, -1,1}));
        Assert.assertEquals(3, firstMissingPositive(new int[]{1, 7, 4, 9, 2, 5}));
        Assert.assertEquals(4, firstMissingPositive(new int[]{1, 7, 3, 9, 2, 5}));
        Assert.assertEquals(1, firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }

    private int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int i = 0;
        while (set.contains(++i));
        return i;
    }
}
