package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
 * 同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 *
 * 提示：
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 * @author donglin
 * @since 2020-10-22
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        int n = S.length();
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            last[chars[i] - 'a'] = i;
        }
        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            end = Math.max(end, last[chars[i] - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }

    @Test
    public void case1() {
        String S = "ababcbacadefegdehijhklij";
        List<Integer> expected = Arrays.asList(9, 7, 8);
        Assert.assertEquals(expected, partitionLabels(S));
    }

    @Test
    public void case2() {
        String S = "zxcvcxz";
        List<Integer> expected = Collections.singletonList(7);
        Assert.assertEquals(expected, partitionLabels(S));
    }

    @Test
    public void case3() {
        String S = "qwert";
        List<Integer> expected = Arrays.asList(1, 1, 1, 1, 1);
        Assert.assertEquals(expected, partitionLabels(S));
    }
}
