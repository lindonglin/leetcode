package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 459. 重复的子字符串
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 *
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 *
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 *
 * @author donglin
 * @since 2020-08-24
 */
public class RepeatedSubstringPattern {

    @Test
    public void case1() {
        Assert.assertTrue(repeatedSubstringPattern("abab"));
    }

    @Test
    public void case2() {
        Assert.assertFalse(repeatedSubstringPattern("aba"));
    }

    @Test
    public void case3() {
        Assert.assertTrue(repeatedSubstringPattern("abcabcabcabc"));
    }

    @Test
    public void case4() {
        Assert.assertFalse(repeatedSubstringPattern("a"));
    }

    @Test
    public void case5() {
        Assert.assertTrue(repeatedSubstringPattern("bb"));
    }

    public boolean repeatedSubstringPattern(String s) {
        return s.length() >= 2 && (s + s).substring(1, 2 * s.length() - 1).contains(s);
    }
}
