package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 *
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @author donglin
 * @since 2020-07-13
 */
public class Intersect {

    @Test
    public void case1() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] expected = {2, 2};
        Assert.assertArrayEquals(expected, intersect(nums1, nums2));
    }


    @Test
    public void case2() {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] expected = {9, 4};
        Assert.assertArrayEquals(expected, intersect(nums1, nums2));
    }

    private int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int v : nums1) {
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int v : nums2) {
            int count = map.getOrDefault(v, 0);
            if (count > 0) {
                list.add(v);
                map.put(v, count - 1);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0, size = list.size(); i < size; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
