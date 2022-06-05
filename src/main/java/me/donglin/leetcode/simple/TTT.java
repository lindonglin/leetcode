package me.donglin.leetcode.simple;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author donglin
 * @since 2021-06-24
 */
public class TTT {

    public void combination(int[][] nums) {
        List<int[]> result = combination(nums, 0);
        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    private List<int[]> combination(int[][] nums, int i) {
        List<int[]> list = new ArrayList<>();
        if (i == nums.length - 1) {
            for (int num : nums[i]) {
                int[] arr = new int[nums.length];
                arr[i] = num;
                list.add(arr);
            }
            return list;
        }
        List<int[]> subList = combination(nums, i + 1);
        for (int num : nums[i]) {
            for (int[] arr : subList) {
                arr[i] = num;
                list.add(arr.clone());
            }
        }
        return list;
    }

    @Test
    public void test() {
        int[][] nums = {
                {1, 2, 3},
                {4, 5},
                {6, 7, 8, 9},
                {10, 11, 12, 13, 14, 15}
        };
        combination(nums);
    }
}
