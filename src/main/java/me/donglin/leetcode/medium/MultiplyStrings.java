package me.donglin.leetcode.medium;

import me.donglin.leetcode.simple.AddStrings;
import org.junit.Assert;
import org.junit.Test;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * <p>
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author donglin
 * @since 2020-08-13
 */
public class MultiplyStrings {

    private static final AddStrings addStrings = new AddStrings();

    @Test
    public void case1() {
        Assert.assertEquals("6", multiply("2", "3"));
    }

    @Test
    public void case2() {
        Assert.assertEquals("63", multiply("9", "7"));
    }

    @Test
    public void case3() {
        Assert.assertEquals("56088", multiply("123", "456"));
    }

    @Test
    public void case4() {
        Assert.assertEquals("0", multiply("0", "456"));
    }

    @Test
    public void case5() {
        Assert.assertEquals("0", multiply("123", "0"));
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        StringBuilder zero = new StringBuilder();
        StringBuilder builder;
        String result = "";
        for (int i = num1.length() - 1; i >= 0; i--) {
            int carry = 0;
            int v1 = chars1[i] - '0';
            builder = new StringBuilder(zero);
            for (int k = num2.length() - 1; k >= 0; k--) {
                int v2 = chars2[k] - '0';
                int multiply = v1 * v2 + carry;
                carry = multiply / 10;
                builder.append(multiply % 10);
            }
            if (carry > 0) {
                builder.append(carry);
            }
            result = addStrings.addStrings(result, builder.reverse().toString());
            zero.append("0");
        }
        return result;
    }
}
