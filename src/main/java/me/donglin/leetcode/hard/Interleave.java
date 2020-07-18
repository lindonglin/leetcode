package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 *
 * 示例 2:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 * @author donglin
 * @since 2020-07-18
 */
public class Interleave {

    @Test
    public void case1() {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        Assert.assertTrue(isInterleave(s1, s2, s3));
    }

    @Test
    public void case2() {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
        Assert.assertFalse(isInterleave(s1, s2, s3));
    }

    @Test
    public void case3() {
        String s1 = "aa", s2 = "ab", s3 = "aaba";
        Assert.assertTrue(isInterleave(s1, s2, s3));
    }

    @Test
    public void case4() {
        String s1 = "bcbccabcccbcbbbcbbacaaccccacbaccabaccbabccbabcaabbbccbbbaa";
        String s2 = "ccbccaaccabacaabccaaccbabcbbaacacaccaacbacbbccccbac";
        String s3 = "bccbcccabbccaccaccacbacbacbabbcbccbaaccbbaacbcbaacbacbaccaaccabcaccacaacbacbacccbbabcccccbababcaabcbbcccbbbaa";
        Assert.assertFalse(isInterleave(s1, s2, s3));
    }

    @Test
    public void case5() {
        String s1 = "", s2 = "", s3 = "";
        Assert.assertTrue(isInterleave(s1, s2, s3));
    }

    @Test
    public void case6() {
        String s1 = "aa", s2 = "ab", s3 = "abaa";
        Assert.assertTrue(isInterleave(s1, s2, s3));
    }

    @Test
    public void case7() {
        String s1 = "a", s2 = "b", s3 = "ba";
        Assert.assertTrue(isInterleave(s1, s2, s3));
    }

    @Test
    public void case8() {
        String s1 = "a", s2 = "b", s3 = "ab";
        Assert.assertTrue(isInterleave(s1, s2, s3));
    }

    /**
     * 时间复杂度是O(mn)
     */
    private boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int size1 = s1.length();
        int size2 = s2.length();
        int size3 = s3.length();
        // match[i1][i2] 表示 s1的前i1个字符和s2的前i2个字符能否组成s3的前i1+i2个字符
        boolean[][] match = new boolean[s1.length() + 1][s2.length() + 1];
        match[0][0] = true;
        for (int i1 = 0; i1 <= size1; i1++) {
            for (int i2 = 0; i2 <= size2; i2++) {
                if (!match[i1][i2]) {
                    continue;
                }
                char c3 = i1 + i2 < size3? s3.charAt(i1 + i2) : 0;
                if (i1 < size1 && s1.charAt(i1) == c3) {
                    match[i1 + 1][i2] = true;
                }
                if (i2 < size2 && s2.charAt(i2) == c3) {
                    match[i1][i2 + 1] = true;
                }
            }

        }
        return match[size1][size2];
    }
}
