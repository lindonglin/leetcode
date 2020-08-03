package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 提示：
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 *
 * @author donglin
 * @since 2020-08-03
 */
public class AddStrings {

    @Test
    public void case1() {
        Assert.assertEquals("16", addStrings("9", "7"));
        Assert.assertEquals("19", addStrings("9", "10"));
        Assert.assertEquals("302", addStrings("3", "299"));
    }

    public String addStrings(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int i = num1.length() - 1;
        int k = num2.length() - 1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while (i >= 0 || k >= 0 || carry > 0) {
            int v1 = i >= 0 ? chars1[i--] - '0' : 0;
            int v2 = k >= 0 ? chars2[k--] - '0' : 0;
            int sum = v1 + v2 + carry;
            if (sum > 9) {
                carry = 1;
                builder.append(sum - 10);
            } else {
                carry = 0;
                builder.append(sum);
            }
        }
        return builder.reverse().toString();
    }
}
