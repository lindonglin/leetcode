package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;

/**
 * @author donglin
 * @since 2023-03-19
 */
public class RemoveDuplicates extends BaseTest {

    public int removeDuplicates(int[] nums) {
        int k = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[k]) {
                nums[++k] = nums[i];
            }
        }
        return k + 1;
    }


    @Override
    public void case1() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int expectedSize = 5;
        int[] expectedNums = {0, 1, 2, 3, 4};
        int k = removeDuplicates(nums);
        Assert.assertEquals(expectedSize, k);
        for (int i = 0; i < k; i++) {
            Assert.assertEquals(expectedNums[i], nums[i]);
        }
    }
}
