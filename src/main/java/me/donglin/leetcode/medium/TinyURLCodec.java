package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 535. TinyURL 的加密与解密
 * TinyURL 是一种 URL 简化服务，
 * 比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，
 * 它将返回一个简化的URL http://tinyurl.com/4e9iAk 。
 * 请你设计一个类来加密与解密 TinyURL 。
 *
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，
 * 并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
 *
 * 实现 Solution 类：
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 *
 * 示例：
 * 输入：url = "https://leetcode.com/problems/design-tinyurl"
 * 输出："https://leetcode.com/problems/design-tinyurl"
 *
 * 解释：
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // 返回加密后得到的 TinyURL 。
 * string ans = obj.decode(tiny); // 返回解密后得到的原本的 URL 。
 *
 *
 * 提示：
 * 1 <= url.length <= 10^4
 * 题目数据保证 url 是一个有效的 URL
 *
 * @author donglin
 * @since 2022-06-29
 */
public class TinyURLCodec {

    private static final String ENDPOINT = "https://tinyurl.com/";

    private static final char[] DICT = new char[62];
    static {
        int i = 0;
        for (char c = '0'; c <= '9'; c++, i++) {
            DICT[i] = c;
        }
        for (char c = 'A'; c <= 'Z'; c++, i++) {
            DICT[i] = c;
        }
        for (char c = 'a'; c <= 'z'; c++, i++) {
            DICT[i] = c;
        }
    }

    private final Map<String, String> long2shortMap = new HashMap<>();
    private final Map<String, String> short2longMap = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String randomCode = long2shortMap.get(longUrl);
        if (randomCode == null) {
            char[] chars = new char[6];
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                chars[i] = DICT[random.nextInt(62)];
            }
            randomCode = new String(chars);
            long2shortMap.put(longUrl, randomCode);
            short2longMap.put(randomCode, longUrl);
        }
        return ENDPOINT + randomCode;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return short2longMap.get(shortUrl.substring(ENDPOINT.length()));
    }

    @Test
    public void case1() {
        String url = "https://leetcode.com/problems/design-tinyurl";
        TinyURLCodec codec = new TinyURLCodec();
        String res = codec.decode(codec.encode(url));
        Assert.assertEquals(url, res);
    }

}
