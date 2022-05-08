package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 * @author donglin
 * @since 2020-07-11
 */
public class CountSmaller {

    @Test
    public void case1() {
        int[] nums = {5,2,6,1};
        List<Integer> expected = Arrays.asList(2,1,1,0);
        Assert.assertEquals(expected, countSmaller(nums));
    }


    private List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 1) {
            return Collections.emptyList();
        }
        int n = nums.length;
        int[] sortNums = nums.clone();
        Arrays.sort(sortNums);
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(sortNums[i], i);
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
           result[0] = map.get(nums[i]);
        }
        List<Integer> list = new ArrayList<>();
        for (int v : result) {
            list.add(v);
        }
        return list;
    }
}