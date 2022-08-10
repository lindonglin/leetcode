package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 640. 求解方程
 * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 * 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 *
 * 示例 1：
 * 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 *
 * 示例 2:
 * 输入: equation = "x=x"
 * 输出: "Infinite solutions"
 *
 * 示例 3:
 * 输入: equation = "2x=x"
 * 输出: "x=0"
 *
 * 提示:
 * 3 <= equation.length <= 1000
 * equation 只有一个 '='.
 * equation 方程由整数组成，其绝对值在 [0, 100] 范围内，不含前导零和变量 'x' 。
 *
 * @author donglin
 * @since 2022-08-10
 */
public class SolveEquation {

    public String solveEquation(String equation) {
        int a = 0, b = 0, ops = 1;
        char[] chars = equation.toCharArray();
        int t;
        for (int i = 0; i < chars.length; i++) {
            t = 0;
            char c = chars[i];
            if (c == '+') {
                ops = 1;
            } else if (c == '-') {
                ops = -1;
            } else if (c == '=') {
                ops = 1;
                a *= -1;
                b *= -1;
            } else {
                boolean flag = false;
                while (c >= '0' && c <= '9') {
                    t = t * 10 + (c - '0');
                    flag = true;
                    if (i + 1 < chars.length) {
                        c = chars[++i];
                    } else {
                        break;
                    }
                }
                t = t > 0 || flag ? t : 1;
                if (chars[i] == 'x') {
                    a += ops * t;
                } else {
                    b += ops * t;
                    if (i + 1 < chars.length) {
                        i--;
                    }
                }

            }
        }
        if (a == 0 && b == 0) {
            return "Infinite solutions";
        }
        if (a == 0) {
            return "No solution";
        }
        return String.format("x=%d", -b / a);
    }

    @Test
    public void case1() {
        Assert.assertEquals("x=2", solveEquation("x+5-3+x=6+x-2"));
    }

    @Test
    public void case2() {
        Assert.assertEquals("Infinite solutions", solveEquation("x=x"));
    }

    @Test
    public void case3() {
        Assert.assertEquals("x=0", solveEquation("2x=x"));
    }

    @Test
    public void case4() {
        Assert.assertEquals("No solution", solveEquation("x+1=x"));
    }

    @Test
    public void case5() {
        Assert.assertEquals("x=0", solveEquation("21x=x"));
    }

    @Test
    public void case6() {
        Assert.assertEquals("x=-1", solveEquation("2x+3=-x"));
    }


    @Test
    public void case7() {
        Assert.assertEquals("Infinite solutions", solveEquation("0x=0"));
    }
}
