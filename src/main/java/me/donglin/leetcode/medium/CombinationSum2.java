package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * @author donglin
 * @since 2020-09-10
 */
public class CombinationSum2 {

    @Test
    public void case1() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 7), Arrays.asList(1, 2, 5), Arrays.asList(2, 6), Arrays.asList(1, 1, 6));
        Assert.assertTrue(CommonUtil.isEquals(expected, combinationSum2(candidates, target)));
    }

    @Test
    public void case2() {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 2, 2), Collections.singletonList(5));
        Assert.assertTrue(CommonUtil.isEquals(expected, combinationSum2(candidates, target)));
    }


    @Test
    public void case3() {
        int[] candidates = {3, 1, 3, 5, 1, 1};
        int target = 8;
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 1, 1, 5), Arrays.asList(1, 1, 3, 3), Arrays.asList(3, 5));
        Assert.assertTrue(CommonUtil.isEquals(expected, combinationSum2(candidates, target)));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2(candidates, 0, target, result, new ArrayList<>());
        return result;
    }

    public void combinationSum2(int[] candidates, int start, int target, List<List<Integer>> result, List<Integer> temp) {
        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        int retry = 1;
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            if (i + 1< candidates.length && candidates[i + 1] == num) {
                retry++;
            } else if (retry > 1){
                // 有重复数字的单独处理
                int oldSize = temp.size();
                for (int st = num, max = Math.min(retry * num, target); st <= max ; st += num) {
                    // 分别重复几次num
                    temp.add(num);
                    combinationSum2(candidates, i + 1, target - st, result, temp);
                }
                while (temp.size() > oldSize) {
                    temp.remove(oldSize);
                }
                retry = 1;
            } else if (num <= target) {
                temp.add(num);
                combinationSum2(candidates, i + 1, target - num, result, temp);
                temp.remove(temp.size() - 1);
                retry = 1;
            }
        }
    }
}
