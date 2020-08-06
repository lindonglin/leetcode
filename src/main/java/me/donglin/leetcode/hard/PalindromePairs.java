package me.donglin.leetcode.hard;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 336. 回文对
 * 给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1：
 * 输入：["abcd","dcba","lls","s","sssll"]
 * 输出：[[0,1],[1,0],[3,2],[2,4]]
 * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * 示例 2：
 * 输入：["bat","tab","cat"]
 * 输出：[[0,1],[1,0]]
 * 解释：可拼接成的回文串为 ["battab","tabbat"]
 *
 * @author donglin
 * @since 2020-08-06
 */
public class PalindromePairs {

    @Test
    public void case1() {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0), Arrays.asList(3, 2), Arrays.asList(2, 4));
        Assert.assertTrue(CommonUtil.isEquals(expected, palindromePairs(words)));
    }

    @Test
    public void case2() {
        String[] words = {"bat", "tab", "cat"};
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0));
        Assert.assertTrue(CommonUtil.isEquals(expected, palindromePairs(words)));
    }

    @Test
    public void case3() {
        String[] words = {"ab", "ba", "abc", "cba"};
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0), Arrays.asList(2, 1), Arrays.asList(2, 3),
                Arrays.asList(0, 3), Arrays.asList(3, 2));
        Assert.assertTrue(CommonUtil.isEquals(expected, palindromePairs(words)));
    }


    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        String[] reverses = new String[n];
        for (int i = 0; i < n; i++) {
            reverses[i] = reverse(words[i]);
        }
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Boolean> palindrome = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                String s1 = words[i];
                String s2 = words[k];
                if (s1.length() == s2.length()) {
                    if (s1.equals(reverses[k])) {
                        palindrome.put(s1 + s2, true);
                        palindrome.put(s2 + s1, true);
                        result.add(Arrays.asList(i, k));
                        result.add(Arrays.asList(k, i));
                    } else {
                        palindrome.put(s1 + s2, false);
                        palindrome.put(s2 + s1, false);
                    }
                } else if (s1.length() > s2.length()) {
                    helper(s1, s2, i, k, reverses, palindrome, result);
                } else {
                    helper(s2, s1, k, i, reverses, palindrome, result);
                }
            }
        }
        return result;
    }

    private void helper(String s1, String s2, int i1, int i2, String[] reverses, Map<String, Boolean> palindrome, List<List<Integer>> result) {
        // s1 + s2
        String left = s1.substring(0, s2.length());
        String mid = s1.substring(s2.length());
        String right = reverses[i2];
        if (isPalindrome(left, mid, right, palindrome)) {
            palindrome.put(s1 + s2, true);
            result.add(Arrays.asList(i1, i2));
        }
        // s2 + s1
        left = right;
        mid = s1.substring(0, s1.length() - s2.length());
        right = s1.substring(s1.length() - s2.length());
        if (isPalindrome(left, mid, right, palindrome)) {
            palindrome.put(s2 + s1, true);
            result.add(Arrays.asList(i2, i1));
        }
    }

    private boolean isPalindrome(String left, String mid, String right, Map<String, Boolean> palindrome) {
       if (left.equals(right)) {
            if (!palindrome.containsKey(mid)) {
                palindrome.put(mid, isPalindrome(mid));
            }
            return palindrome.get(mid);
        } else {
           return false;
       }
    }

    private boolean isPalindrome(String str) {
        for (int i = 0, size = str.length() - 1, n = size / 2; i <= n; i++) {
            if (str.charAt(i) != str.charAt(size - i)) {
                return false;
            }
        }
        return true;
    }

    private String reverse(String str) {
        char[] chars = new char[str.length()];
        for (int i = 0, size = str.length() - 1; i <= size; i++) {
            chars[i] = str.charAt(size - i);
        }
        return new String(chars);
    }
}
