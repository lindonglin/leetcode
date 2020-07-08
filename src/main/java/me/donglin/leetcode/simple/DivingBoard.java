package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;


/**
 * 面试题 16.11. 跳水板
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。
 * 编写一个方法，生成跳水板所有可能的长度。
 * 返回的长度需要从小到大排列。
 *
 * 示例：
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 *
 * 提示：
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 * @author donglin
 * @since 2020/07/08
 */
public class DivingBoard {

    @Test
    public void case1() {
        int[] expected = {3, 4, 5, 6};
        Assert.assertArrayEquals(expected, divingBoard(1, 2, 3));
    }

    @Test
    public void case2() {
        int[] expected = {};
        Assert.assertArrayEquals(expected, divingBoard(4, 8, 0));
    }


    @Test
    public void case3() {
        int[] expected = {40};
        Assert.assertArrayEquals(expected, divingBoard(5, 5, 8));
    }

    private int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] result = new int[k + 1];
        result[0] = k * shorter;
        int slope = longer - shorter;
        for (int i = 1; i <= k; i++) {
            result[i] = result[i-1] + slope;
        }
        return result;
    }
}
