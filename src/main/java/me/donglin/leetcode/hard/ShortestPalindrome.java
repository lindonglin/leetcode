package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;


/**
 * 214. 最短回文串
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 *
 * 示例 1:
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 *
 * 示例 2:
 * 输入: "abcd"
 * 输出: "dcbabcd"
 *
 * @author donglin
 * @since 2020-08-29
 */
public class ShortestPalindrome {

    @Test
    public void case1() {
        Assert.assertEquals("aaacecaaa", shortestPalindrome("aacecaaa"));
    }

    @Test
    public void case2() {
        Assert.assertEquals("dcbabcd", shortestPalindrome("abcd"));
    }

    @Test
    public void case3() {
        Assert.assertEquals("aabacbababcabaa", shortestPalindrome("ababcabaa"));
    }


    /**
     * KMP算法
     */
    public String shortestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();
        // 前缀表 (值减了1)
        int[] prefix = prefixTable(chars);
        int best = -1;
        for (int i = n - 1; i >= 0; --i) {
            while (best != -1 && chars[best + 1] != chars[i]) {
                best = prefix[best] - 1;
            }
            if (chars[best + 1] == chars[i]) {
                ++best;
            }
        }
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        return new StringBuffer(add).reverse().append(s).toString();
    }

    private int[] prefixTable(char[] chars) {
        int n = chars.length;
        int[] prefix = new int[n];
        int len = 0, i = 1;
        while (i < n) {
            if (chars[i] == chars[len]) {
                prefix[i++] = ++len;
            } else if (len > 0) {
                len = prefix[len - 1];
            } else {
                prefix[i++] = 0;
            }
        }
        return prefix;
    }
}
