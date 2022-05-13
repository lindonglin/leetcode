package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 面试题 01.05. 一次编辑
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。
 * 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 *
 * 示例 1:
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *
 * 示例 2:
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 *
 * @author donglin
 * @since 2022-05-13
 */
public class OneEditAway {

    public boolean oneEditAway(String first, String second) {
        String s1, s2;
        if (first.length() < second.length()) {
            s1 = first;
            s2 = second;
        } else {
            s1 = second;
            s2 = first;
        }
        int l1 = s1.length();
        int l2 = s2.length();
        int dict = l2 - l1;
        int diff = 0;
        if (dict == 0) {
            for (int i = 0; i < l1; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diff++;
                    if (diff > 1) {
                        return false;
                    }
                }
            }
        } else if (dict > 1) {
            return false;
        } else {
            boolean flag = false;
            int i1 = 0, i2 = 0;
            while (i1 < l1 && i2 < l2) {
                if (s1.charAt(i1) != s2.charAt(i2)) {
                    if (!flag) {
                        flag = true;
                        i2++;
                    } else {
                        return false;
                    }
                } else {
                    i1++;
                    i2++;
                }
            }
        }
        return true;
    }

    @Test
    public void case1() {
        Assert.assertTrue(oneEditAway("pale", "ple"));
    }

    @Test
    public void case2() {
        Assert.assertFalse(oneEditAway("pales", "pal"));
    }

    @Test
    public void case3() {
        Assert.assertTrue(oneEditAway("a", "ab"));
    }

    @Test
    public void case4() {
        Assert.assertFalse(oneEditAway("teacher", "teachy"));
    }

}
