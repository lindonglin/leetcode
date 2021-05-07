package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 提示：
 * 0 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 * @author donglin
 * @version 1.0
 * @since 2021/05/07
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        char[] result = strs[0].toCharArray();
        for (int i = 1; i < strs.length; i++) {
            result = longestCommonPrefix(result, strs[i].toCharArray());
        }
        return new String(result);
    }

    public char[] longestCommonPrefix(char[] str1, char[] str2) {
        int length = Math.min(str1.length, str2.length);
        int i = 0;
        while (i < length && str1[i] == str2[i]) {
            i++;
        }
        return Arrays.copyOf(str1, i);
    }

    @Test
    public void case1() {
        String[] strs = {"flower", "flow", "flight"};
        Assert.assertEquals("fl", longestCommonPrefix(strs));
    }

    @Test
    public void case2() {
        String[] strs = {"dog", "racecar", "car"};
        Assert.assertEquals("", longestCommonPrefix(strs));
    }
}
