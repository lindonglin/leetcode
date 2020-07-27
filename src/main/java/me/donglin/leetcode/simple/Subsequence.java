package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 392. 判断子序列
 *
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 *
 * 后续挑战 :
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码
 *
 * @author donglin
 * @since 2020/07/27
 */
public class Subsequence {

    @Test
    public void case1() {
        Assert.assertTrue(isSubsequence("abc", "ahbgdc"));
        Assert.assertFalse(isSubsequence("axc", "ahbgdc"));
    }

    /**
     * 平均时间复杂度为O(s + t)
     * s和t分为为字符串长度
     *
     * 满足后续挑战，只需要计算一次 List<Integer>[] arr = extract(t)
     * 然后给 S1, S2, ... , Sk 共用即可
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        List<Integer>[] arr = extract(t);
        // 对于字母 i + 'a' 从 arrStartIndex[i] 开始在 arr 找合适的第一个小标即可，这样可降时间低复杂度
        int[] arrStartIndex = new int[arr.length];
        int lastIndex = -1;
        for (int i = 0, size = s.length(); i < size; i++) {
            int sIndex = s.charAt(i) - 'a';
            List<Integer> tIndexes = arr[s.charAt(i) - 'a'];
            boolean find = false;
            for (int k = arrStartIndex[sIndex]; k < tIndexes.size(); k++) {
                if (tIndexes.get(k) > lastIndex) {
                    lastIndex = tIndexes.get(k);
                    arrStartIndex[sIndex] = k + 1;
                    find = true;
                    break;
                }
            }
            if (!find) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将26个小写字符转换为数组的小标。
     * 数组元素为改字母在字符串中出现的所有位置，显然是按升序排序的
     */
    private List<Integer>[] extract(String t) {
        List<Integer>[] arr = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0, size = t.length(); i < size; i++) {
            arr[t.charAt(i) - 'a'].add(i);
        }
        return arr;
    }
}
