package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * @author donglin
 * @since 2020-07-29
 */
public class FindMedianSortedArrays {

    private static final double DELTA = 0;

    @Test
    public void case1() {
        int[] num1 = {1, 3};
        int[] num2 = {2};
        double expected = 2;
        Assert.assertEquals(expected, findMedianSortedArrays(num1, num2), DELTA);
    }

    @Test
    public void case2() {
        int[] num1 = {1, 2};
        int[] num2 = {3, 4};
        double expected = 2.5;
        Assert.assertEquals(expected, findMedianSortedArrays(num1, num2), DELTA);
    }

    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int n1 = nums1.length;
        int n2 = nums2.length;
        // 两个数组合并起来后的中间位置
        int mergeMid = (n1 + n2 + 1) / 2;
        int left = 0;
        int right = n1;
        while (left < right) {
            int i = (left + right + 1) / 2;
            int k = mergeMid - i;
            if (nums1[i - 1] > nums2[k]) {
                right = i - 1;
            } else {
                left = i;
            }
        }
        int i = (left + right + 1) / 2;
        int k = mergeMid - i;
        int num1Left = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int num1Right = i == n1 ? Integer.MAX_VALUE : nums1[i];
        int num2Left = k == 0 ? Integer.MIN_VALUE : nums2[k - 1];
        int num2Right = k == n2 ? Integer.MAX_VALUE : nums2[k];
        if (((n1 + n2) & 1) == 1) {
            return Math.max(num1Left, num2Left);
        }
        return (Math.max(num1Left, num2Left) + Math.min(num1Right, num2Right)) / 2.0;
    }
}
