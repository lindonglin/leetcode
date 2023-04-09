package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;


/**
 * 2399. 检查相同字母间的距离
 * @author donglin
 * @since 2023-04-09
 */
public class CheckDistances extends BaseTest {

    public boolean checkDistances(String s, int[] distance) {
        int[] d = new int[27];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int k = chars[i] - 'a';
            if (d[k] == 0) {
                d[k] = -i - 1;;
            } else if (d[k] < 0) {
                d[k] = i + d[k];
            }
        }
        for (int i = 0; i < 26; i++) {
            if (d[i] != 0 && d[i] != distance[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void case1() {
        String s = "abaccb";
        int[] distance = {1, 3, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Assert.assertTrue(checkDistances(s, distance));
    }
}
