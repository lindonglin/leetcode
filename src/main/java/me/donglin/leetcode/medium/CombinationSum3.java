package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * @author donglin
 * @since 2020-09-11
 */
public class CombinationSum3 extends BaseTest {

    @Override
    public void case1() {
        List<List<Integer>> expected = Collections.singletonList(Arrays.asList(1, 2, 4));
        Assert.assertTrue(CommonUtil.isEquals(expected, combinationSum3(3, 7)));
    }

    @Override
    public void case2() {
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 2, 6), Arrays.asList(1, 3, 5), Arrays.asList(2, 3, 4));
        Assert.assertTrue(CommonUtil.isEquals(expected, combinationSum3(3, 9)));
    }


    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(1, k, n, result, new ArrayList<>());
        return result;
    }

    public void combinationSum(int num, int k, int target, List<List<Integer>> result, List<Integer> temp) {
        if (temp.size() == k && target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (temp.size() >= k) {
            return;
        }
        for (; num <= 9; num++) {
            if (num <= target) {
                temp.add(num);
                combinationSum(num + 1, k, target - num, result, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
