package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *
 * 提示：
 * 输入的字符串长度不会超过 1000 。
 *
 * @author donglin
 * @since 2020-08-19
 */
public class CountSubstrings {

    @Test
    public void case1() {
        Assert.assertEquals(3, countSubstrings("abc"));
    }

    @Test
    public void case3() {
        Assert.assertEquals(6, countSubstrings("aaa"));
    }

    public int countSubstrings(String s) {
        int n = s.length();
        StringBuffer t = new StringBuffer("$#");
        // 添加间隔的#字符，是的所有的回文都是奇数长度的，这样不会改变结果
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        n = t.length();
        t.append('!');

        int[] f = new int[n];
        // iMax为最右侧回文的中心点， rMax为最右侧回文的最有点
        int iMax = 0, rMax = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            // 第i个元素的初始值取i到最右回文边界 和 i的对称点的长度的小值
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            // 中心拓展
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // 动态维护 iMax 和 rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
            ans += f[i] / 2;
        }

        return ans;
    }
}
