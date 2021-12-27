package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 825. 适龄的朋友
 *
 * @author donglin
 * @since 2021-12-27
 */
public class NumFriendRequests {

    public int numFriendRequests(int[] ages) {
        int[] counts = new int[121];
        for (int age : ages) {
            counts[age]++;
        }
        int result = 0;
        for (int age : ages) {
            int limit = (age  >> 1) + 8;
            if (limit > age) {
                continue;
            }
            for (int i = limit; i <= age; i++) {
                result += counts[i];
            }
            result--;
        }
        return result;
    }

    @Test
    public void case1() {
        Assert.assertEquals(2, numFriendRequests(new int[]{16, 16}));
    }

    @Test
    public void case2() {
        Assert.assertEquals(2, numFriendRequests(new int[]{16, 17, 18}));
    }

    @Test
    public void case3() {
        Assert.assertEquals(3, numFriendRequests(new int[]{20, 30, 100, 110, 120}));
    }

    @Test
    public void case4() {
        Assert.assertEquals(3, numFriendRequests(new int[]{108, 115, 5, 24, 82}));
    }

    @Test
    public void case5() {
        Assert.assertEquals(29, numFriendRequests(new int[]{73, 106, 39, 6, 26, 15, 30, 100, 71, 35, 46, 112, 6, 60, 110}));
    }


}
