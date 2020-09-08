package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * @author donglin
 * @since 2020-09-08
 */
public class Combine {

    @Test
    public void case1() {
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2, 4), Arrays.asList(3, 4), Arrays.asList(2, 3),
                Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4)
        );
        Assert.assertTrue(CommonUtil.isEquals(expected, combine(4, 2)));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(result, n, k, 1, new ArrayList<>());
        return result;
    }

    private void combine(List<List<Integer>> result, int n, int k, int start, List<Integer> tempList) {
        if (k == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start, end = n - k + 1; i <= end; i++) {
            tempList.add(i);
            combine(result, n, k - 1, i + 1, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

}
