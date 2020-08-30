package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 557. 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *
 * 提示：
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 * @author donglin
 * @since 2020-08-30
 */
public class ReverseWords {

    @Test
    public void case1() {
        Assert.assertEquals("s'teL ekat edoCteeL tsetnoc", reverseWords("Let's take LeetCode contest"));
    }

    @Test
    public void case2() {
        Assert.assertEquals(" ldl ", reverseWords(" ldl "));
    }

    public String reverseWords(String s) {
        char[] result = new char[s.length()];
        char[] chars = s.toCharArray();
        int start = 0;
        int i = 0;
        for (int end = 0; end < chars.length; end++) {
            if (chars[end] == ' ') {
                for (int k = end - 1; k >= start; k--) {
                    result[i++] = chars[k];
                }
                result[i++] = ' ';
                start = end + 1;
            }
        }
        for (int k = s.length() - 1; k >= start; k--) {
           result[i++] = chars[k];
        }
        return new String(result);
    }
}
