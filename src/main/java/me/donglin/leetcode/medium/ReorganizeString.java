package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 767. 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 *
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *
 * 示例 1:
 * 输入: S = "aab"
 * 输出: "aba"
 *
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 *
 * 注意:
 * S 只包含小写字母并且长度在[1, 500]区间内。
 *
 * @author donglin
 * @since 2020-11-30
 */
public class ReorganizeString {

    public String reorganizeString(String S) {
        if (S.length() <= 1) {
            return S;
        }
        Element[] elements = new Element[26];
        for (char c = 'a'; c <= 'z'; c++) {
            elements[c - 'a'] = new Element(c);
        }
        for (char c : S.toCharArray()) {
            elements[c - 'a'].num++;
        }
        Arrays.sort(elements, Comparator.comparingInt(Element::getNum).reversed());
        if (elements[0].num > (S.length() + 1) / 2) {
            return "";
        }
        int ri = 0, ei = 0;
        char[] result = new char[S.length()];
        while (ri < S.length()) {
            result[ri++] = elements[ei].value;
            elements[ei].num--;
            ei = nextIndex(ei, elements);
        }
        return new String(result);
    }

    private int nextIndex(int curIndex, Element[] elements) {
        int max = 0;
        int index = -1;
        Element element;
        char cur = elements[curIndex].value;
        for (int i = 0; i < elements.length; i++) {
            element = elements[i];
            if (element.value != cur) {
                if (element.num > max) {
                    index = i;
                    max = element.num;
                }
            }
        }
        return index;

    }

    private static class Element {
        private final char value;
        private int num;

        public Element(char value) {
            this.value = value;
        }

        public int getNum() {
            return num;
        }
    }

    @Test
    public void case1() {
        Assert.assertEquals("aba", reorganizeString("aab"));
    }

    @Test
    public void case2() {
        Assert.assertEquals("", reorganizeString("aaab"));
    }

    @Test
    public void case3() {
        Assert.assertEquals("ababacabcdabcd", reorganizeString("aaaaabbbbcccdd"));
    }

    @Test
    public void case4() {
        Assert.assertEquals("abababaca", reorganizeString("aaaaabbbc"));
    }
}
