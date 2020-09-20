package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * @author donglin
 * @since 2020-09-20
 */
public class Subsets extends BaseTest {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets(nums, 0, result, new ArrayList<>());
        return result;
    }

    public void subsets(int[] nums, int i, List<List<Integer>> result, List<Integer> temp) {
        result.add(new ArrayList<>(temp));
        for (; i < nums.length; i++) {
            temp.add(nums[i]);
            subsets(nums, i + 1, result, temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Override
    public void case1() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> expected = asList(singletonList(3), singletonList(1), singletonList(2),
                asList(1, 2, 3), asList(1, 3), asList(2, 3), asList(1, 2), emptyList());
        Assert.assertTrue(CommonUtil.isEquals(expected, subsets(nums)));
    }

}
