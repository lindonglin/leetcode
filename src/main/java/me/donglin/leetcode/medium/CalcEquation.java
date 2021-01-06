package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * 399. 除法求值
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
 * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 *
 * 示例 1：
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * 示例 2：
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
 * queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 *
 * 示例 3：
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * 提示：
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 *
 * @author donglin
 * @since 2021/01/06
 */
public class CalcEquation {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>(values.length);
        for (int i = 0, size = equations.size(); i < size; i++) {
            String q1 = equations.get(i).get(0);
            String q2 = equations.get(i).get(1);
            map.computeIfAbsent(q1, v -> new HashMap<>(values.length)).put(q2, values[i]);
            map.computeIfAbsent(q2, v -> new HashMap<>(values.length)).put(q1, 1 / values[i]);
        }
        double[] result = new double[queries.size()];
        for (int i = 0, size = queries.size(); i < size; i++) {
            List<String> pair = queries.get(i);
            result[i] = quotient(pair.get(0), pair.get(1), map);
        }
        return result;
    }

    private double quotient(String divisor, String dividend, Map<String, Map<String, Double>> map) {
        Map<String, Double> quotientMap = map.get(divisor);
        if (quotientMap == null) {
            return -1;
        }
        if (quotientMap.containsKey(dividend)) {
            return quotientMap.get(dividend);
        }
        double quotient = -1;
        // 这样就不会产生环导致无限递归
        quotientMap.put(dividend, quotient);
        for (Map.Entry<String, Double> entry : quotientMap.entrySet()) {
            if (entry.getValue() != -1) {
                double q = quotient(entry.getKey(), dividend, map);
                if (q != -1) {
                    quotient = entry.getValue() * q;
                    break;
                }
            }
        }
        quotientMap.put(dividend, quotient);
        return quotient;
    }

    @Test
    public void case1() {
        List<List<String>> equations = asList(asList("a", "b"), asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = asList(asList("a", "c"), asList("b", "a"), asList("a", "e"), asList("a", "a"),  asList("x", "x"));
        double[] expected = {6.00000, 0.50000, -1.00000, 1.00000, -1.00000};
        Assert.assertArrayEquals(expected, calcEquation(equations, values, queries), 0.001);
    }

    @Test
    public void case2() {
        List<List<String>> equations = asList(asList("a", "b"), asList("b", "c"), asList("bc","cd"));
        double[] values = {1.5, 2.5, 5.0};
        List<List<String>> queries = asList(asList("a", "c"), asList("c", "b"), asList("bc", "cd"), asList("cd", "bc"));
        double[] expected = {3.75000, 0.40000, 5.00000, 0.20000};
        Assert.assertArrayEquals(expected, calcEquation(equations, values, queries), 0.001);
    }

    @Test
    public void case3() {
        List<List<String>> equations = Collections.singletonList(asList("a", "b"));
        double[] values = {0.5};
        List<List<String>> queries = asList(asList("a", "b"), asList("b", "a"), asList("a", "c"), asList("x", "y"));
        double[] expected = {0.50000, 2.00000, -1.00000, -1.00000};
        Assert.assertArrayEquals(expected, calcEquation(equations, values, queries), 0.001);
    }

    @Test
    public void case4() {
        List<List<String>> equations = asList(asList("a", "e"), asList("b", "e"));
        double[] values = {4, 3};
        List<List<String>> queries = asList(asList("a", "b"), asList("e", "e"), asList("x", "x"));
        double[] expected = {1.33333, 1.0, -1.0};
        Assert.assertArrayEquals(expected, calcEquation(equations, values, queries), 0.001);
    }

    @Test
    public void case5() {
        List<List<String>> equations = asList(asList("a", "b"), asList("c", "d"), asList("e", "f"), asList("g", "h"));
        double[] values = {4.5, 2.3, 8.9, 0.44};
        List<List<String>> queries = asList(asList("a", "c"), asList("d", "f"), asList("h", "e"), asList("b", "e"),
                asList("d", "h"), asList("g", "f"), asList("c", "g"));
        double[] expected = {-1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0};
        Assert.assertArrayEquals(expected, calcEquation(equations, values, queries), 0.001);
    }

}
