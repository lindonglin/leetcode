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
        int l1 = first.length();
        int l2 = second.length();
        int dict = l1 - l2;
        if (dict > 1 || dict < -1) {
            return false;
        }
        int diff = 0;
        if (dict == 0) {
            for (int i = 0; i < l1; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    diff++;
                    if (diff > 1) {
                        return false;
                    }
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

}
