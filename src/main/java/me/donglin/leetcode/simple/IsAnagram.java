package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * @author donglin
 * @since 2020-11-22
 */
public class IsAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] nums = new int[26];
        for (char c : s.toCharArray()) {
            nums[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            nums[c - 'a']--;
            if (nums[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 进阶
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void case1() {
        String s = "anagram";
        String t = "nagaram";
        Assert.assertTrue(isAnagram(s, t));
    }

    @Test
    public void case2() {
        String s = "rat";
        String t = "car";
        Assert.assertFalse(isAnagram(s, t));
    }


    @Test
    public void case3() {
        String s = "东南西北";
        String t = "东西南北";
        Assert.assertTrue(isAnagram2(s, t));
    }

    @Test
    public void case4() {
        String s = "春夏秋冬";
        String t = "东南西北";
        Assert.assertFalse(isAnagram2(s, t));
    }
}
