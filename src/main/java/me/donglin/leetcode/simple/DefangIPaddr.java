package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1108. IP 地址无效化
 * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
 *
 * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 *
 * 示例 1：
 * 输入：address = "1.1.1.1"
 * 输出："1[.]1[.]1[.]1"
 *
 * 示例 2：
 * 输入：address = "255.100.50.0"
 * 输出："255[.]100[.]50[.]0"
 *
 * 提示：
 * 给出的 address 是一个有效的 IPv4 地址
 *
 * @author donglin
 * @since 2022-06-21
 */
public class DefangIPaddr {

    public String defangIPaddr(String address) {
        char[] res = new char[address.length() + 6];
        int i = 0;
        for (char c : address.toCharArray()) {
            if (c == '.') {
                res[i++] = '[';
                res[i++] = '.';
                res[i++] = ']';
            } else {
                res[i++] = c;
            }
        }
        return new String(res);
    }

    @Test
    public void case1() {
        Assert.assertEquals("1[.]1[.]1[.]1", defangIPaddr("1.1.1.1"));
    }
}
