package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 961. 在长度 2N 的数组中找出重复 N 次的元素
 * 给你一个整数数组 nums ，该数组具有以下属性：
 *
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [2,1,2,5,3,2]
 * 输出：2
 *
 * 示例 3：
 * 输入：nums = [5,1,5,2,5,3,5,4]
 * 输出：5
 *
 * 提示：
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 104
 * nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
 *
 * @author donglin
 * @since 2022-05-21
 */
public class RepeatedNTimes {

    public int repeatedNTimes(int[] nums) {
        int pre = nums[nums.length - 1];
        for (int num : nums) {
            if (num == pre) {
                return pre;
            }
            pre = num;
        }
        return nums[0] == nums[2] ? nums[0] : nums[1];
    }

    @Test
    public void case1() {
        Assert.assertEquals(3, repeatedNTimes(new int[]{1, 2, 3, 3}));
    }

    @Test
    public void case2() {
        Assert.assertEquals(2, repeatedNTimes(new int[]{2, 1, 2, 5, 3, 2}));
    }

    @Test
    public void case3() {
        Assert.assertEquals(5, repeatedNTimes(new int[]{5, 1, 5, 2, 5, 3, 5, 4}));
    }

    @Test
    public void case4() {
        Assert.assertEquals(9, repeatedNTimes(new int[]{9, 5, 6, 9}));
    }

}
