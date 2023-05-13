package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;

/**
 * 2441. 与对应负数同时存在的最大正整数
 * @author donglin
 * @since 2023-05-13
 */
public class FindMaxK extends BaseTest {

    public int findMaxK(int[] nums) {
        int[] cache = new int[2001];
        int max = -1;
        for (int num : nums) {
            if (num < 0) {
                cache[num + 1000] = 1;
                if (-num > max && cache[-num + 1000] == 1) {
                    max = -num;
                }
            } else {
                cache[num + 1000] = 1;
                if (num > max && cache[1000 - num] == 1) {
                    max = num;
                }
            }
        }
        return max;
    }

    @Override
    public void case1() {
        int[] nums = {-1, 2, -3, 3};
        Assert.assertEquals(3, findMaxK(nums));
    }

    @Override
    public void case2() {
        int[] nums = {-1, 10, 6, 7, -7, 1};
        Assert.assertEquals(7, findMaxK(nums));
    }

    @Override
    public void case3() {
        int[] nums = {-10, 8, 6, 7, -2, -3};
        Assert.assertEquals(-1, findMaxK(nums));
    }
}
