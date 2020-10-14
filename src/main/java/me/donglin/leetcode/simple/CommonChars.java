package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1002. 查找常用字符
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 *
 * 示例 2：
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *
 * 提示：
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 *
 * @author donglin
 * @since 2020-10-14
 */
public class CommonChars {

    public List<String> commonChars(String[] A) {
        int[][] statistics = new int[26][A.length];
        for (int i = 0; i < A.length; i++) {
            for (char c: A[i].toCharArray()) {
                statistics[c - 'a'][i]++;
            }
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            int min = Integer.MAX_VALUE;
            for (int c : statistics[i]) {
                min = Math.min(min, c);
            }
            for (int k = 0; k < min; k++) {
                result.add(String.valueOf((char) (i + 'a')));
            }
        }
        return result;
    }

    @Test
    public void case1() {
        String[] a = {"bella", "label", "roller"};
        List<String> expected = Arrays.asList("e", "l", "l");
        Assert.assertEquals(expected, commonChars(a));
    }

    @Test
    public void case2() {
        String[] a = {"cool", "lock", "cook"};
        List<String> expected = Arrays.asList("c", "o");
        Assert.assertEquals(expected, commonChars(a));
    }
}
