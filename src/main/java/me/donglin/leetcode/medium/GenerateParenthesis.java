package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 22. 括号生成
 * @author donglin
 * @since 2023-05-03
 */
@SuppressWarnings("unchecked")
public class GenerateParenthesis extends BaseTest {

    @SuppressWarnings("rawtypes")
    private static final ArrayList[] cache = new ArrayList[100];
    static {
        cache[0] = new ArrayList<>();
        cache[0].add("");
        cache[1] = new ArrayList<>();
        cache[1].add("()");
    }

    public List<String> generateParenthesis(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (String l : generateParenthesis(i)) {
                for (String r : generateParenthesis(n - i - 1)) {
                    res.add("(" + l + ")" + r);
                }
            }
        }
        cache[n] = res;
        return res;
    }

    @Override
    public void case1() {
        List<String> expect = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
        Assert.assertTrue(CommonUtil.isEquals(expect, generateParenthesis(3)));
    }

    @Test
    public void case2() {
        List<String> expect = Collections.singletonList("()");
        Assert.assertTrue(CommonUtil.isEquals(expect, generateParenthesis(1)));
    }

    @Test
    public void case3() {
        List<String> expect = Arrays.asList("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()");
        List<String> a = generateParenthesis(4);
        expect.sort(String::compareTo);
        a.sort(String::compareTo);
        Assert.assertTrue(CommonUtil.isEquals(expect, generateParenthesis(4)));
    }
}
