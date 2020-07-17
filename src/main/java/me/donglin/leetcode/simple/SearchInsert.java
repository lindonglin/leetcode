package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * @author donglin
 * @since 2020/07/17
 */
public class SearchInsert {

    @Test
    public void case1() {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        Assert.assertEquals(2, searchInsert(nums, target));
        Assert.assertEquals(2, searchInsert1(nums, target));
    }

    @Test
    public void case2() {
        int[] nums = {1, 3, 5, 6};
        int target = 2;
        Assert.assertEquals(1, searchInsert(nums, target));
        Assert.assertEquals(1, searchInsert1(nums, target));
    }

    @Test
    public void case3() {
        int[] nums = {1, 3, 5, 6};
        int target = 7;
        Assert.assertEquals(4, searchInsert(nums, target));
        Assert.assertEquals(4, searchInsert1(nums, target));
    }
    @Test
    public void case4() {
        int[] nums = {1, 3, 5, 6};
        int target = 0;
        Assert.assertEquals(0, searchInsert(nums, target));
        Assert.assertEquals(0, searchInsert1(nums, target));
    }

    private int searchInsert(int[] nums, int target) {
        for (int i = 0, size = nums.length; i < size; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 二分法 O(logn)
     */
    private int searchInsert1(int[] nums, int target) {
        if (target <= nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (right + left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left + 1;
    }
}
