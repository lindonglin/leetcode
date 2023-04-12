package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * 27. 移除元素
 * @author donglin
 * @since 2023-04-12
 */
public class RemoveElement extends BaseTest {

    public int removeElement(int[] nums, int val) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            while (r >= l && nums[r] == val) {
                r--;
            }
            if (r < l) {
                return l;
            }
            if (nums[l] == val) {
                nums[l] = nums[r];
                r--;
            }
            l++;
        }
        return l;
    }

    @Override
    public void case1() {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int expected = 2;
        Assert.assertEquals(expected, removeElement(nums, val));
        for (int i = 0; i < expected; i++) {
            Assert.assertTrue(nums[i] != val);
        }
    }

    @Test
    public void case2() {
        int[] nums = {3, 3, 3, 3};
        int val = 3;
        int expected = 0;
        Assert.assertEquals(expected, removeElement(nums, val));
    }

    @Test
    public void case3() {
        int[] nums = {3, 2, 2, 3};
        int val = 1;
        int expected = 4;
        Assert.assertEquals(expected, removeElement(nums, val));
    }
    @Test
    public void case4() {
        int[] nums = {2, 3, 3, 3};
        int val = 3;
        int expected = 1;
        Assert.assertEquals(expected, removeElement(nums, val));
    }
}
