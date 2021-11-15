package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 319 灯泡开关
 *
 * @author donglin
 * @since 2021-11-15
 */
public class BulbSwitch {

    public int bulbSwitch(int n) {
       int count = 0, i = 1;
       while (i * i <= n) {
           i++;
           count++;
       }
       return count;
    }


    public int bulbSwitch2(int n) {
        return (int) Math.sqrt(n);
    }

    @Test
    public void case1() {
        Assert.assertEquals(0, bulbSwitch(0));
        Assert.assertEquals(1, bulbSwitch(1));
        Assert.assertEquals(1, bulbSwitch(2));
        Assert.assertEquals(1, bulbSwitch(3));
        Assert.assertEquals(2, bulbSwitch(4));
    }
}
