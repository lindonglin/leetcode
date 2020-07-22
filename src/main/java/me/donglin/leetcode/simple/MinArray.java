package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，
 * 输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 *
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * @author donglin
 * @since 2020/07/22
 */
public class MinArray {

    @Test
    public void case1() {
        int[] numbers = {3, 4, 5, 1, 2};
        Assert.assertEquals(1, minArray(numbers));
    }


    @Test
    public void case2() {
        int[] numbers = {2, 2, 2, 0, 1};
        Assert.assertEquals(0, minArray(numbers));
    }

    @Test
    public void case3() {
        int[] numbers = {2, 3, 4, 0, 1, 2, 2, 2, 2, 2};
        Assert.assertEquals(0, minArray(numbers));
    }


    @Test
    public void case4() {
        int[] numbers = {2, 3, 4, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2};
        Assert.assertEquals(0, minArray(numbers));
    }


    @Test
    public void case5() {
        int[] numbers = {1, 2};
        Assert.assertEquals(1, minArray(numbers));
    }

    @Test
    public void case6() {
        int[] numbers = {1, 2, 3};
        Assert.assertEquals(1, minArray(numbers));
    }


    @Test
    public void case7() {
        int[] numbers = {1, 2, 1};
        Assert.assertEquals(1, minArray(numbers));
    }


    @Test
    public void case8() {
        int[] numbers = {3, 1, 3};
        Assert.assertEquals(1, minArray(numbers));
    }

    private int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (numbers[mid] < numbers[right]) {
                // 显然，最小值在 [left, mid] 闭区间内
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                // 显然，最小值在 (mid, right] 左开右闭区间内
                left = mid + 1;
            } else {
                right--;
            }
        }
        return numbers[left];
    }
}
