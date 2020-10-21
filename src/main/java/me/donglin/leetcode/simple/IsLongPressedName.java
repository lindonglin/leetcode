package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 925. 长按键入
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 *
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 *
 * 示例 1：
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 *
 * 示例 2：
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 *
 * 示例 3：
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 *
 * 示例 4：
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 *
 * 提示：
 * name.length <= 1000
 * typed.length <= 1000
 * name 和 typed 的字符都是小写字母。
 *
 *
 * @author donglin
 * @since 2020-10-21
 */
public class IsLongPressedName {

    public boolean isLongPressedName(String name, String typed) {
        if (name.equals(typed)) {
            return true;
        }
        if (name.length() >= typed.length()) {
            return false;
        }
        char pre = ' ';
        int i = 0, j = 0;
        char[] chars1 = name.toCharArray();
        char[] chars2 = typed.toCharArray();
         for (; i < chars1.length && j < chars2.length; j++) {
            if (chars1[i] == chars2[j]) {
                pre = chars1[i];
                i++;
            } else if (chars2[j] != pre){
                return false;
            }
        }
        for (; j < chars2.length; j++) {
            if (chars2[j] != pre){
                return false;
            }
        }
        return i == name.length();
    }

    @Test
    public void case1() {
        Assert.assertTrue(isLongPressedName("alex", "aaleex"));
    }

    @Test
    public void case2() {
        Assert.assertFalse(isLongPressedName("saeed", "ssaaedd"));
    }

    @Test
    public void case3() {
        Assert.assertTrue(isLongPressedName("leelee", "lleeelee"));
    }

    @Test
    public void case4() {
        Assert.assertTrue(isLongPressedName("laiden", "laiden"));
    }

    @Test
    public void case5() {
        Assert.assertTrue(isLongPressedName("vtkgn", "vttkgnn"));
    }

    @Test
    public void case6() {
        Assert.assertFalse(isLongPressedName("pyplrz", "ppyypllr"));
    }

}
