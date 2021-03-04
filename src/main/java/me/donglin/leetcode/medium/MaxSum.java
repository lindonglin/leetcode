package me.donglin.leetcode.medium;

import org.junit.Test;

/**
 * 假设有32，-40，60，25，-54，59，98，-92，-22，85十个数组成的数组a[10]。
 * 请用算法求出a[10]中sum(n,m)的最大值以及对应的n和m分别是多少，并写出对应的解题思路。
 * 说明：sum(n,m)表示数组从a[n]到a[m]的和。
 * 例如：sum(0,2)=32 +(-40)+60=52，sum(1,3)=(-40)+60+25=45。
 *
 * @author donglin
 * @since 2021-03-04
 */
public class MaxSum {


    public int[] sum(int[] nums) {
        int n = 0;
        int m = 0;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxSum) {
                maxSum = sum;
                n = start;
                m = i;
            }
            if (sum < 0) {
                start = i + 1;
                sum = 0;
            }
        }
        return new int[]{maxSum, n, m};
    }

    @Test
    public void case1() {
        int[] nums = {32, -40, 60, 25, -54, 59, 98, -92, -22, 85};
        int[] result = sum(nums);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
    @Test
    public void case5() {
        int[] nums = {32, -40, 60, 25, -54, 59, 98, -92, -22, 85};
        int[] result = sum(nums);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    @Test
    public void case2() {
        int[] nums = {32, -40, -60, -25, -54, -59, 98, -92, -22, -85};
        int[] result = sum(nums);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    @Test
    public void case3() {
        int[] nums = {32, -40, 60, -25, -54, -59, -98, -92, -22, -85};
        int[] result = sum(nums);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    @Test
    public void case4() {
        int[] nums = {-32, -40, -60, -25, -54, -59, -98, -92, -22, -85};
        int[] result = sum(nums);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}
