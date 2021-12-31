package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 507. 完美数
 *
 * @author donglin
 * @since 2021-12-31
 */
public class CheckPerfectNumber {

    public boolean checkPerfectNumber(int num) {
        if (num < 6) {
            return false;
        }
        int flag = 1;
        int i = 2;
        while (true) {
            if (num % i == 0) {
                int div = num / i;
                if (div >= i) {
                    flag += i;
                    flag += div;
                } else {
                    break;
                }
            }
            i++;
        }
        return flag == num;
    }

    @Test
    public void case1() {
        Assert.assertTrue(checkPerfectNumber(28));
    }

    @Test
    public void case2() {
        Assert.assertTrue(checkPerfectNumber(6));
    }

    @Test
    public void case3() {
        Assert.assertFalse(checkPerfectNumber(2));
    }

    @Test
    public void case4() {
        Assert.assertTrue(checkPerfectNumber(496));
    }

    @Test
    public void case5() {
        Assert.assertTrue(checkPerfectNumber(8128));
    }

    @Test
    public void case6() {
        Assert.assertFalse(checkPerfectNumber(36));
    }
}
