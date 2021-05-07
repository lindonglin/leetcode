package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 *
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 *
 * @author donglin
 * @version 1.0
 * @since 2021/05/07
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        int maxLen = 0;
        int index = 0;
        boolean odd = false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int oddLen = longestPalindromeOdd(i, chars);
            int evenLen = longestPalindromeEven(i, chars);
            if (oddLen > maxLen) {
                index = i;
                odd = true;
                maxLen = oddLen;
            }
            if (evenLen > maxLen) {
                index = i;
                odd = false;
                maxLen = evenLen;
            }
        }
        return odd ? s.substring(index - maxLen / 2, index + maxLen / 2 + 1) :
                s.substring(index - maxLen / 2 + 1, index + maxLen / 2 + 1);
    }

    private int longestPalindromeOdd(int c, char[] chars) {
        int l = c - 1;
        int r = c + 1;
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }
    private int longestPalindromeEven(int c, char[] chars) {
        int l = c;
        int r = c + 1;
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    @Test
    public void case1() {
        String s = "babad";
        Set<String> expected = new HashSet<>();
        expected.add("bab");
        expected.add("aba");
        Assert.assertTrue(expected.contains(longestPalindrome(s)));
    }

    @Test
    public void case2() {
        String s = "cbbd";
        Assert.assertEquals("bb", longestPalindrome(s));
    }

    @Test
    public void case3() {
        String s = "ac";
        Set<String> expected = new HashSet<>();
        expected.add("a");
        expected.add("c");
        Assert.assertTrue(expected.contains(longestPalindrome(s)));
    }

    @Test
    public void case4() {
        String s = "bb";
        Assert.assertEquals("bb", longestPalindrome(s));
    }

    @Test
    public void case5() {
        String s = "ccc";
        Assert.assertEquals("ccc", longestPalindrome(s));
    }
}
