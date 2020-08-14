package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;


/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 *
 * @author donglin
 * @since 2020-08-14
 */
public class ValidBrackets {

    @Test
    public void case1() {
        Assert.assertTrue(isValid("()"));
        Assert.assertTrue(isValid("()[]{}"));
        Assert.assertFalse(isValid("(]"));
        Assert.assertFalse(isValid("([)]"));
        Assert.assertTrue(isValid("{[]}"));
    }

    private boolean isValid(String s) {
        char[] chars = s.toCharArray();
        char[] stack = new char[chars.length];
        int top = -1;
        for (char c : chars) {
            if (isOpen(c)) {
                stack[++top] = c;
            } else if (isClose(c)) {
                if (top < 0) {
                    return false;
                }
                if (!isMatch(stack[top--], c)) {
                    return false;
                }
            }
        }
        return top < 0;
    }

    private boolean isOpen(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private boolean isClose(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private boolean isMatch(char open, char close) {
        return open == '(' && close ==')'
                || open == '[' && close ==']'
                || open == '{' && close =='}';
    }
}
