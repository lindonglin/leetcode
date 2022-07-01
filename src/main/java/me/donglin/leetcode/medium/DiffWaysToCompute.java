package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，
 * 计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 *
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 10^4 。
 *
 * 示例 1：
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * 示例 2：
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 * 提示：
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99]
 *
 * @author donglin
 * @since 2022-07-01
 */
public class DiffWaysToCompute {

    static final int ADDITION = -1;
    static final int SUBTRACTION = -2;
    static final int MULTIPLICATION = -3;

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ops = new ArrayList<>();
        char[] chars = expression.toCharArray();
        int i = 0, n = chars.length;
        while (i < n) {
            int num = 0;
            while (i < n && chars[i] >= '0' && chars[i] <= '9') {
                num = num * 10 + chars[i] - '0';
                i++;
            }
            ops.add(num);
            if (i >= n) {
                break;
            }
            if (chars[i] == '+') {
                ops.add(ADDITION);
            } else if (chars[i] == '-') {
                ops.add(SUBTRACTION);
            } else {
                ops.add(MULTIPLICATION);
            }
            i++;
        }
        //  dp[i][j]来表示对应表达式 在按不同优先级组合数字和运算符的ops[i]到ops[j]中能产生的全部可能结果
        List<Integer>[][] dp = new List[ops.size()][ops.size()];
        for (i = 0; i < ops.size(); i++) {
            for (int j = 0; j < ops.size(); j++) {
                dp[i][j] = new ArrayList<>();
            }
        }
        for (i = 0; i < ops.size(); i += 2) {
            dp[i][i].add(ops.get(i));
        }
        for (i = 3; i <= ops.size(); i++) {
            for (int j = 0; j + i <= ops.size(); j += 2) {
                int l = j;
                int r = j + i - 1;
                for (int k = j + 1; k < r; k += 2) {
                    List<Integer> left = dp[l][k - 1];
                    List<Integer> right = dp[k + 1][r];
                    for (int num1 : left) {
                        for (int num2 : right) {
                            if (ops.get(k) == ADDITION) {
                                dp[l][r].add(num1 + num2);
                            } else if (ops.get(k) == SUBTRACTION) {
                                dp[l][r].add(num1 - num2);
                            } else if (ops.get(k) == MULTIPLICATION) {
                                dp[l][r].add(num1 * num2);
                            }
                        }
                    }
                }
            }
        }
        return dp[0][ops.size() - 1];
    }

    @Test
    public void case1() {
        List<Integer> expected = Arrays.asList(0, 2);
        Assert.assertTrue(CommonUtil.isEquals(expected, diffWaysToCompute("2-1-1")));
    }

    @Test
    public void case2() {
        List<Integer> expected = Arrays.asList(-34, -14, -10, -10, 10);
        Assert.assertTrue(CommonUtil.isEquals(expected, diffWaysToCompute("2*3-4*5")));
    }

}
