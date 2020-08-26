package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * @author donglin
 * @since 2020-08-26
 */
public class LetterCombinations {

    private static final List<List<Character>> DICTIONARY = new ArrayList<>();

    static {
        DICTIONARY.add(Arrays.asList('a', 'b', 'c'));
        DICTIONARY.add(Arrays.asList('d', 'e', 'f'));
        DICTIONARY.add(Arrays.asList('g', 'h', 'i'));
        DICTIONARY.add(Arrays.asList('j', 'k', 'l'));
        DICTIONARY.add(Arrays.asList('m', 'n', 'o'));
        DICTIONARY.add(Arrays.asList('p', 'q', 'r', 's'));
        DICTIONARY.add(Arrays.asList('t', 'u', 'v'));
        DICTIONARY.add(Arrays.asList('w', 'x', 'y', 'z'));
    }

    @Test
    public void case1() {
        String digits = "23";
        List<String> expected = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        Assert.assertTrue(CommonUtil.isEquals(expected, letterCombinations(digits)));
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        letterCombinations(digits.toCharArray(), 0, builder, result);
        return result;
    }

    public void letterCombinations(char[] chars, int i, StringBuilder builder, List<String> result) {
        if (i == chars.length) {
            result.add(builder.toString());
            return;
        }
        for (char c : DICTIONARY.get(chars[i] - '2')) {
            builder.append(c);
            letterCombinations(chars, i + 1, builder, result);
            builder.deleteCharAt(i);
        }
    }
}
