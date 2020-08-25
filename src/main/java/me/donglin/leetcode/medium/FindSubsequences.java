package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 491. 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * <p>
 * 示例:
 * <p>
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 * <p>
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 *
 * @author donglin
 * @since 2020-08-25
 */
public class FindSubsequences {

    @Test
    public void case1() {
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(4, 6),
                Arrays.asList(4, 7),
                Arrays.asList(4, 6, 7),
                Arrays.asList(4, 6, 7, 7),
                Arrays.asList(6, 7),
                Arrays.asList(6, 7, 7),
                Arrays.asList(7, 7),
                Arrays.asList(4, 7, 7)
        );
        Assert.assertTrue(CommonUtil.isEquals(expected, findSubsequences(nums)));
    }

    @Test
    public void case2() {
        int[] nums = {4, 7, 6, 7};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(4, 6),
                Arrays.asList(4, 7),
                Arrays.asList(4, 6, 7),
                Arrays.asList(6, 7),
                Arrays.asList(7, 7),
                Arrays.asList(4, 7, 7)
        );
        Assert.assertTrue(CommonUtil.isEquals(expected, findSubsequences(nums)));
    }


    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> subsequences = new HashSet<>();
        for (int val : nums) {
            List<List<Integer>> newSubsequences = new ArrayList<>();
            for (List<Integer> list : subsequences) {
                if (val >= list.get(list.size() - 1)) {
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(val);
                    newSubsequences.add(newList);
                }
            }
            subsequences.addAll(newSubsequences);
            subsequences.add(Collections.singletonList(val));
        }

        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : subsequences) {
            if (list.size() > 1) {
                result.add(list);
            }
        }
        return result;
    }

}
