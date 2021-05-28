package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 477. 汉明距离总和
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 *
 * 示例:
 * 输入: 4, 14, 2
 * 输出: 6
 *
 * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 注意:
 *
 * 数组中元素的范围为从 0到 10^9。
 * 数组的长度不超过 10^4。
 *
 * @author donglin
 * @version 1.0
 * @since 2021/05/28
 */
public class TotalHammingDistance {

    public int totalHammingDistance(int[] nums) {
        int[] count = new int[32];
        for (int num : nums) {
            int k = 0;
            while (num > 0) {
                if ((num & 1) == 1) {
                    count[k]++;
                }
                num >>= 1;
                k++;
            }
        }
        int n = nums.length;
        int result = 0;
        for (int c : count) {
            result += c * (n - c);
        }
        return result;
    }

    public int totalHammingDistance1(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                result += totalHammingDistance(nums[i], nums[j]);
            }
        }
        return result;
    }

    private int totalHammingDistance(int a, int b) {
        if (a == b) {
            return 0;
        }
        int r = a ^ b;
        r = (r & 0x55555555) + ((r >> 1) & 0x55555555);
        r = (r & 0x33333333) + ((r >> 2) & 0x33333333);
        r = (r & 0x0F0F0F0F) + ((r >> 4) & 0x0F0F0F0F);
        r = (r & 0x00FF00FF) + ((r >> 8) & 0x00FF00FF);
        r = (r & 0x0000FFFF) + ((r >> 16) & 0x0000FFFF);
        return r;
    }

    @Test
    public void case1() {
        int[] nums = {4, 14, 2};
        Assert.assertEquals(6, totalHammingDistance(nums));
        Assert.assertEquals(6, totalHammingDistance1(nums));
    }
}
