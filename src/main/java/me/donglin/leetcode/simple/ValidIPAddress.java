package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;


/**
 * 468. 验证IP地址
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 *
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。
 * 例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 *
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，
 * 而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 *
 * 示例 1：
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 *
 * 示例 2：
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 *
 * 示例 3：
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 *
 * 提示：
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 *
 * @author donglin
 * @since 2022-05-29
 */
public class ValidIPAddress {

    public String validIPAddress(String queryIP) {
        char[] chars = queryIP.toCharArray();
        if (validIPV4Address(chars)) {
            return "IPv4";
        }
        if (validIPV6Address(chars)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean validIPV4Address(char[] chars) {
        int num = 0;
        int count = 0;
        // 用来判断是否有前导0的
        int flag = 0;
        for (char c : chars) {
            if (c == '.') {
                if (flag == 0 || num > 255 || (flag > 1 && num < Math.pow(10, flag - 1))) {
                    return false;
                }
                count++;
                num = 0;
                flag = 0;
            } else if (c >= '0' && c <= '9') {
                flag += 1;
                num = num * 10 + c - '0';
            } else {
                return false;
            }
        }
        return flag > 0 && count == 3 && num < 256 && !(flag > 1 && num < Math.pow(10, flag - 1));
    }

    private boolean validIPV6Address(char[] chars) {
        int k = 0;
        int n = chars.length;
        for (int i = 0; i < 8; i++) {
            int b = 0;
            while (k < n) {
                char c = chars[k++];
                if (c == ':') {
                    break;
                }
                if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')) {
                    b++;
                } else {
                    return false;
                }
            }
            if (b > 4 || b < 1) {
                return false;
            }
        }
        return k == n && chars[n - 1] != ':';
    }

    @Test
    public void case1() {
        Assert.assertEquals("IPv4", validIPAddress("172.16.254.1"));
    }

    @Test
    public void case2() {
        Assert.assertEquals("IPv6", validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
    }

    @Test
    public void case3() {
        Assert.assertEquals("Neither", validIPAddress("256.256.256.256"));
    }

    @Test
    public void case4() {
        Assert.assertEquals("Neither", validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
    }

    @Test
    public void case5() {
        Assert.assertEquals("IPv4", validIPAddress("192.0.0.1"));
    }

    @Test
    public void case6() {
        Assert.assertEquals("Neither", validIPAddress("192.01.0.1"));
    }

    @Test
    public void case7() {
        Assert.assertEquals("Neither", validIPAddress("1.0.1."));
    }

    @Test
    public void case8() {
        Assert.assertEquals("Neither", validIPAddress("172.256.254.1"));
    }

    @Test
    public void case9() {
        Assert.assertEquals("IPv4", validIPAddress("172.16.254.255"));
    }

}
