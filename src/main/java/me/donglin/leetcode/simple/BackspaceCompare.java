package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 844. 比较含退格的字符串
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 *
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 示例 1：
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 *
 * 示例 2：
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 *
 * 示例 3：
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 *
 * 示例 4：
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *
 * 提示：
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 *
 * 进阶：
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 *
 * @author donglin
 * @since 2020-10-19
 */
public class BackspaceCompare {

    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int bi = 0, bj = 0;
        while (i >= 0 && j >= 0) {
            boolean flag = true;
            if (S.charAt(i) == '#') {
                bi++;
                i--;
                flag = false;
            } else if (bi > 0) {
                i--;
                bi--;
                flag = false;
            }
            if (T.charAt(j) == '#') {
                bj++;
                j--;
                flag = false;
            } else if (bj > 0){
                j--;
                bj--;
                flag = false;
            }
            if (flag && S.charAt(i--) != T.charAt(j--)) {
                return false;
            }
        }
        while (i >= 0) {
            if (S.charAt(i) == '#') {
                bi++;
                i--;
            } else if (bi > 0) {
                i--;
                bi--;
            } else {
                return false;
            }
        }
        while (j >= 0) {
            if (T.charAt(j) == '#') {
                bj++;
                j--;
            } else if (bj > 0){
                j--;
                bj--;
            } else {
                return false;
            }
        }
        return i - bi * 2 < 0 && j - bj * 2 < 0;
    }

    @Test
    public void case1() {
        Assert.assertTrue(backspaceCompare("ab#c", "ad#c"));
    }

    @Test
    public void case2() {
        Assert.assertTrue(backspaceCompare("ab##", "c#d#"));
    }

    @Test
    public void case3() {
        Assert.assertTrue(backspaceCompare("a##c", "#a#c"));
    }

    @Test
    public void case4() {
        Assert.assertFalse(backspaceCompare("a#c", "b"));
    }

    @Test
    public void case5() {
        Assert.assertTrue(backspaceCompare("bxj##tw", "bxo#j##tw"));
    }

    @Test
    public void case6() {
        Assert.assertFalse(backspaceCompare("bxj##tw", "bxj###tw"));
    }

}
