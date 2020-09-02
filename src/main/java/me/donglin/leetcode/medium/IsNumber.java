package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
 * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 *
 * @author donglin
 * @since 2020-09-02
 */
public class IsNumber {

    public boolean isNumber(String s) {
        s = s.trim().replace('E', 'e');
        if (s.isEmpty()) {
            return false;
        }
        if (s.charAt(s.length() - 1) == 'e') {
            return false;
        }
        String[] arr = s.split("e");
        if (arr.length == 1) {
            return isNumber(arr[0], true);
        }
        if (arr.length == 2) {
            return isNumber(arr[0], true) && isNumber(arr[1], false);
        }
        return false;
    }

    public boolean isNumber(String s, boolean mayHasPoint) {
        String s1 = s.replace(".", "");
        if (s1.isEmpty()) {
            return false;
        }
        if (!mayHasPoint && s1.length() != s.length()) {
            return false;
        }
        if (s1.length() + 1 < s.length()) {
            // 说明不止一个小数点
            return false;
        }
        if (s1.length() == 1) {
            return s1.charAt(0) >= '0' && s1.charAt(0) <= '9';
        }
        char[] chars = s.toCharArray();
        if (chars.length == 1) {
            return chars[0] >= '0' && chars[0] <= '9';
        }
        if (chars[0] == '+' || chars[0] == '-') {
            chars[0] = '0';
        }
        for (char c : chars) {
            if ((c < '0' || c > '9') && c != '.') {
                return false;
            }
        }
        return true;
    }



    @Test
    public void case1() {
        Assert.assertTrue(isNumber("+100"));
    }

    @Test
    public void case2() {
        Assert.assertTrue(isNumber("5e2"));
    }

    @Test
    public void case3() {
        Assert.assertTrue(isNumber("-123"));
    }

    @Test
    public void case4() {
        Assert.assertTrue(isNumber("3.1416"));
    }

    @Test
    public void case5() {
        Assert.assertTrue(isNumber("-1E-16"));
    }

    @Test
    public void case6() {
        Assert.assertTrue(isNumber("0123"));
    }

    @Test
    public void case7() {
        Assert.assertFalse(isNumber("12e"));
    }

    @Test
    public void case8() {
        Assert.assertFalse(isNumber("12e"));
    }

    @Test
    public void case9() {
        Assert.assertFalse(isNumber("1a3.14"));
    }

    @Test
    public void case10() {
        Assert.assertFalse(isNumber("1.2.3"));
    }

    @Test
    public void case11() {
        Assert.assertFalse(isNumber("+-5"));
    }

    @Test
    public void case12() {
        Assert.assertFalse(isNumber("12e+5.4"));
    }

    @Test
    public void case13() {
        Assert.assertTrue(isNumber("+.8"));
    }

    @Test
    public void case14() {
        Assert.assertFalse(isNumber(".."));
    }

    @Test
    public void case15() {
        Assert.assertFalse(isNumber(".e1"));
    }

    @Test
    public void case16() {
        Assert.assertFalse(isNumber("-."));
    }

    @Test
    public void case17() {
        Assert.assertFalse(isNumber(".-4"));
    }
}
