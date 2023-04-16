package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;

/**
 * 12. 整数转罗马数字
 * @author donglin
 * @since 2023-04-16
 */
public class IntToRoman extends BaseTest {

    private static final String[] THOUSANDS = {"", "M", "MM", "MMM"};
    private static final String[] HUNDREDS = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] TENS = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] ONES = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    public String intToRoman(int num) {
        return THOUSANDS[num / 1000] +
                HUNDREDS[num % 1000 / 100] +
                TENS[num % 100 / 10] +
                ONES[num % 10];
    }

    @Override
    public void case1() {
        Assert.assertEquals("III", intToRoman(3));
        Assert.assertEquals("IV", intToRoman(4));
        Assert.assertEquals("IX", intToRoman(9));
        Assert.assertEquals("LVIII", intToRoman(58));
    }
}
