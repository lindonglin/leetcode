package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * @author donglin
 * @since 2023-04-19
 */
public class ThreeSumClosest extends BaseTest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0, n = nums.length; i < n; i++) {
            int l = i + 1, r = n - 1;
            while (l < r) {
                int diff = nums[i] + nums[l] + nums[r] - target;
                if (diff == 0) {
                    return target;
                }
                if (diff > 0) {
                    r--;
                } else {
                    l++;
                }
                if (Math.abs(diff) < minDiff) {
                    minDiff = Math.abs(diff);
                    sum = diff + target;
                }
            }
        }
        return sum;
    }

    @Override
    public void case1() {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        Assert.assertEquals(2, threeSumClosest(nums, target));
    }

    @Test
    public void case2() {
        int[] nums = {0, 0, 0};
        int target = 1;
        Assert.assertEquals(0, threeSumClosest(nums, target));
    }
}
