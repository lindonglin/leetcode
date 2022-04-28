package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 905. 按奇偶排序数组
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * 返回满足此条件的 任一数组 作为答案。
 *
 * 示例 1：
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[0]
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 *
 * @author donglin
 * @since 2022-04-28
 */
public class SortArrayByParity {

    public int[] sortArrayByParity(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if ((nums[l] & 1) == 0) {
                l++;
                continue;
            }
            if ((nums[r] & 1) == 1) {
                r--;
                continue;
            }
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }
        return nums;
    }

    @Test
    public void case1() {
        int[] expect = {4, 2, 1, 3};
        Assert.assertArrayEquals(expect, sortArrayByParity(new int[]{3, 1, 2, 4}));
    }

    @Test
    public void case2() {
        int[] expect = {0};
        Assert.assertArrayEquals(expect, sortArrayByParity(new int[]{0}));
    }

}
