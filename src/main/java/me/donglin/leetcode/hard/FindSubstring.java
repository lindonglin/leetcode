package me.donglin.leetcode.hard;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 30. 串联所有单词的子串
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 *
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *
 * 提示：
 * 1 <= s.length <= 10000
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 *
 * @author donglin
 * @since 2022-06-23
 */
public class FindSubstring {

    public List<Integer> findSubstring(String s, String[] words) {
        int l = words[0].length();
        int subSize = words.length * l;
        char[] source = s.toCharArray();
        int left = 0, right = subSize, size = source.length;
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        while (right <= size) {
            for (int i = left + l; i <= right; i += l) {
                String word = new String(source, i - l, l);
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            for (String w : words) {
                Integer count = map.get(w);
                if (count == null) {
                    break;
                }
                if (count > 1) {
                    map.put(w, count - 1);
                } else {
                    map.remove(w);
                }
            }
            if (map.isEmpty()) {
                res.add(left);
            }
            left++;
            right++;
            map.clear();
        }
        return res;
    }


    /**
     * 忽略words中长度都一样的暴力解法
     */
    public List<Integer> findSubstring1(String s, String[] words) {
        int subSize = 0;
        for (String word : words) {
            subSize += word.length();
        }
        char[] source = s.toCharArray();
        Map<Character, List<char[]>> indexMap = new HashMap<>(26);
        for (String word : words) {
            char[] cw = word.toCharArray();
            List<char[]> indexList = indexMap.computeIfAbsent(cw[0], k -> new ArrayList<>());
            indexList.add(cw);
        }
        int left = 0, right = subSize, size = source.length;
        List<Integer> res = new ArrayList<>();
        while (right <= size) {
            if (contains(source, left, right, indexMap)) {
                res.add(left);
            }
            left++;
            right++;
        }
        return res;
    }

    private boolean contains(char[] source, int left, int right, Map<Character, List<char[]>> indexMap) {
        if (left == right) {
            return true;
        }
        List<char[]> candidates = indexMap.get(source[left]);
        if (candidates == null) {
            return false;
        }
        for (char[] candidate : candidates) {
            if (contains(source, left, candidate)) {
                candidate[0] = ' ';
                if (contains(source, left + candidate.length, right, indexMap)) {
                    candidate[0] = source[left];
                    return true;
                }
                candidate[0] = source[left];
            }
        }
        return false;
    }

    private boolean contains(char[] source, int from, char[] target) {
        for (int i = 0, n = target.length; i < n; i++)
            if (source[i + from] != target[i])
                return false;

        return true;
    }


    @Test
    public void case1() {
        String[] words = {"foo", "bar"};
        List<Integer> expected = Arrays.asList(0, 9);
        Assert.assertTrue(CommonUtil.isEquals(expected, findSubstring("barfoothefoobarman",words )));
        Assert.assertTrue(CommonUtil.isEquals(expected, findSubstring1("barfoothefoobarman",words )));
    }

    @Test
    public void case2() {
        String[] words = {"word", "good", "best", "word"};
        List<Integer> expected = Collections.emptyList();
        Assert.assertTrue(CommonUtil.isEquals(expected, findSubstring("wordgoodgoodgoodbestword",words )));
        Assert.assertTrue(CommonUtil.isEquals(expected, findSubstring1("wordgoodgoodgoodbestword",words )));
    }

    @Test
    public void case3() {
        String[] words = {"bar", "foo", "the"};
        List<Integer> expected = Arrays.asList(6, 9, 12);
        Assert.assertTrue(CommonUtil.isEquals(expected, findSubstring("barfoofoobarthefoobarman",words )));
        Assert.assertTrue(CommonUtil.isEquals(expected, findSubstring1("barfoofoobarthefoobarman",words )));
    }


    @Test
    public void case4() {
        String[] words = {"word","good","best","good"};
        List<Integer> expected = Collections.singletonList(8);
        Assert.assertTrue(CommonUtil.isEquals(expected, findSubstring("wordgoodgoodgoodbestword",words )));
    }

}
