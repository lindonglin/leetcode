package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;


/**
 * 522. 最长特殊序列 II
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
 *
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 *
 *  s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 *
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。
 * "aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 *
 *
 * 示例 1：
 * 输入: strs = ["aba","cdc","eae"]
 * 输出: 3
 *
 * 示例 2:
 * 输入: strs = ["aaa","aaa","aa"]
 * 输出: -1
 *
 * 提示:
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] 只包含小写英文字母
 *
 * @author donglin
 * @since 2022-06-27
 */
public class FindLUSlength {

    public int findLUSlength(String[] strs) {
        int n = strs.length;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            boolean check = true;
            for (int j = 0; j < n; ++j) {
                if (i != j && isSubseq(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    public boolean isSubseq(String s, String t) {
        int ptS = 0, ptT = 0;
        while (ptS < s.length() && ptT < t.length()) {
            if (s.charAt(ptS) == t.charAt(ptT)) {
                ++ptS;
            }
            ++ptT;
        }
        return ptS == s.length();
    }


    @Test
    public void case1() {
        System.out.println(Long.MAX_VALUE);
        Assert.assertEquals(3, findLUSlength(new String[]{"aba", "cdc", "eae"}));
    }

    @Test
    public void case2() {
        Assert.assertEquals(-1, findLUSlength(new String[]{"aaa", "aaa", "aa"}));
    }

}
