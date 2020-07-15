package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * @author donglin
 * @since 2020/07/15
 */
public class NumTrees {

    @Test
    public void case1() {
        Assert.assertEquals(5, numTrees(3));
        Assert.assertEquals(2, numTrees(2));
        Assert.assertEquals(1, numTrees(1));
    }

    private int numTrees(int n) {
        int[] nums = new int[n + 1];
        nums[0] = nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int k = 0; k < i; k++) {
                nums[i] += nums[k] * nums[i - k - 1];
            }
        }
        return nums[n];
    }
}
