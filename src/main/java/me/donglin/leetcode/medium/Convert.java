package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * <p>
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * <p>
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 *
 * @author donglin
 * @since 2022-09-04
 */
public class Convert {

    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }
        char[] chars = s.toCharArray();
        StringBuilder buffer = new StringBuilder(s.length());
        // 变换后相邻两个直立列的元素距离
        int uprightDist = 2 * numRows - 2;
        // 变换后中间列元素与前一个直立列的元素距离
        int dist = uprightDist - 2;

        // 第一行
        int next = 0;
        while (next < chars.length) {
            buffer.append(chars[next]);
            next += uprightDist;
        }
        for (int i = 1; i < numRows - 1; i++) {
            // 变换后第i行
            buffer.append(chars[i]);
            next = i + dist;
            while (next < chars.length) {
                buffer.append(chars[next]);
                int nextUpright = next + uprightDist - dist;
                if (nextUpright < chars.length) {
                    buffer.append(chars[nextUpright]);
                }
                next = nextUpright + dist;
            }
            dist -= 2;
        }
        // 最后一行
        next = numRows - 1;
        while (next < chars.length) {
            buffer.append(chars[next]);
            next += uprightDist;
        }
        return buffer.toString();
    }

    @Test
    public void case1() {
        Assert.assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
    }

    @Test
    public void case2() {
        Assert.assertEquals("PINALSIGYAHRPI", convert("PAYPALISHIRING", 4));
    }

    @Test
    public void case3() {
        Assert.assertEquals("A", convert("A", 1));
    }
}
