package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

/**
 * 410. 分割数组的最大值
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 注意:
 * 数组长度 n 满足以下条件:
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 *
 * 示例:
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 * 输出: 18
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 * @author donglin
 * @since 2020-07-25
 */
public class SplitArray {

    @Test
    public void case1() {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        Assert.assertEquals(18, splitArray(nums, m));
    }

    @Test
    public void case2() {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 1;
        Assert.assertEquals(32, splitArray(nums, m));
    }


    @Test
    public void case3() {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 5;
        Assert.assertEquals(10, splitArray(nums, m));
    }

    @Test
    public void case4() {
        int[] nums = {1, 2147483647};
        int m = 2;
        Assert.assertEquals(2147483647, splitArray(nums, m));
    }

    @Test
    public void case5() {
        int[] nums = {0, 0, 0, 0, 0};
        int m = 3;
        Assert.assertEquals(0, splitArray(nums, m));
    }

    /**
     * (二分查找 + 贪心)官方答案
     * 思路及算法
     *
     * 「使……最大值尽可能小」是二分搜索题目常见的问法。
     *
     * 本题中，我们注意到：当我们选定一个值 xx，我们可以线性地验证是否存在一种分割方案，满足其最大分割子数组和不超过 xx。策略如下：
     *
     * 贪心地模拟分割的过程，从前到后遍历数组，用 sum 表示当前分割子数组的和，cnt 表示已经分割出的子数组的数量（包括当前子数组），
     * 那么每当 sum 加上当前值超过了 xx，我们就把当前取的值作为新的一段分割子数组的开头，
     * 并将 cnt 加 11。遍历结束后验证是否cnt 不超过 mm。
     *
     * 这样我们可以用二分查找来解决。二分的上界为数组 nums 中所有元素的和，下界为数组 nums 中所有元素的最大值。
     * 通过二分查找，我们可以得到最小的最大分割子数组和，这样就可以得到最终的答案了。
     */
    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int num : nums) {
            right += num;
            if (left < num) {
                left = num;
            }
        }
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[] nums, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int num : nums) {
            if (sum + num > x) {
                cnt++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return cnt <= m;
    }
}
