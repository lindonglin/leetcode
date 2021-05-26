package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1190. 反转每对括号间的子串
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 *
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 *
 * 注意，您的结果中 不应 包含任何括号。
 *
 * 示例 1：
 * 输入：s = "(abcd)"
 * 输出："dcba"
 *
 * 示例 2：
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 *
 * 示例 3：
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 *
 * 示例 4：
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 *
 * 提示：
 * 0 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 我们确保所有括号都是成对出现的
 *
 * @author donglin
 * @version 1.0
 * @since 2021/05/26
 */
public class ReverseParentheses {

    public String reverseParentheses(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] pair = new int[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                stack[++top] = i;
            } else if (chars[i] == ')') {
                int j = stack[top--];
                pair[i] = j;
                pair[j] = i;
            }
        }
        StringBuilder builder = new StringBuilder();
        int index = 0, step = 1;
        while (index < n) {
            if (chars[index] == '(' || chars[index] == ')') {
                index = pair[index];
                step = -step;
            } else {
                builder.append(s.charAt(index));
            }
            index += step;
        }
        return builder.toString();
    }

    @Test
    public void case1() {
        Assert.assertEquals("dcba", reverseParentheses("(abcd)"));
    }

    @Test
    public void case2() {
        Assert.assertEquals("iloveu", reverseParentheses("(u(love)i)"));
    }

    @Test
    public void case3() {
        Assert.assertEquals("leetcode", reverseParentheses("(ed(et(oc))el)"));
    }

    @Test
    public void case4() {
        Assert.assertEquals("apmnolkjihgfedcbq", reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }

    @Test
    public void case5() {
        Assert.assertEquals("acbdfeg", reverseParentheses("a(bc)d(ef)g"));
    }

}
