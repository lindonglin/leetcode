package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;

import java.util.*;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * @author donglin
 * @since 2020-09-18
 */
public class PermuteUnique extends BaseTest {


    @Override
    public void case1() {
        int[] nums = {1, 1, 2};
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 1), Arrays.asList(2, 1, 1));
        Assert.assertTrue(CommonUtil.isEquals(expected, permuteUnique(nums)));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutes = new ArrayList<>();
        Arrays.sort(nums);
        permuteUnique(nums,  permutes, new ArrayList<>(), new boolean[nums.length]);
        return permutes;
    }
    public void permuteUnique(int[] nums, List<List<Integer>> permutes, List<Integer> temp, boolean[] visits) {
        if (temp.size() == nums.length) {
            permutes.add(new ArrayList<>(temp));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (visits[i]) {
                    continue;
                }
                if (i > 0 && nums[i - 1] == nums[i] && !visits[i - 1]) {
                    continue;
                }
                visits[i] = true;
                temp.add(nums[i]);
                permuteUnique(nums, permutes, temp, visits);
                temp.remove(temp.size() - 1);
                visits[i] = false;
            }
        }
    }
}
