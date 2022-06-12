package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 890. 查找和替换模式
 * 你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
 *
 * 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
 *
 * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
 *
 * 返回 words 中与给定模式匹配的单词列表。
 *
 * 你可以按任何顺序返回答案。
 *
 * 示例：
 * 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * 输出：["mee","aqq"]
 * 解释：
 * "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
 * "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
 * 因为 a 和 b 映射到同一个字母。
 *
 * 提示：
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length <= 20
 *
 * @author donglin
 * @since 2022-06-12
 */
public class FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        char[] patterns = pattern.toCharArray();
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (match(word, patterns)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean match(String word, char[] patterns) {
        char[] map = new char[26];
        char[] reverseMap = new char[26];
        char[] chars = word.toCharArray();
        for (int i = 0, n = chars.length; i < n; i++) {
            char c = chars[i];
            if (map[c - 'a'] == 0) {
                char pc = reverseMap[patterns[i] - 'a'];
                if (pc != 0 && pc != c) {
                    return false;
                }
                map[c - 'a'] = patterns[i];
                reverseMap[patterns[i] - 'a'] = c;
            } else if (map[c - 'a'] != patterns[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void case1() {
        String[] words = {"abc","deq","mee","aqq","dkd","ccc"};
        String pattern = "abb";
        List<String> expect = Arrays.asList("mee", "aqq");
        Assert.assertTrue(CommonUtil.isEquals(expect, findAndReplacePattern(words, pattern)));
    }
}
