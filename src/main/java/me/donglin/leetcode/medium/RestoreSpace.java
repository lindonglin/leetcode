package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 面试题 17.13. 恢复空格
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * <p>
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 * <p>
 * 示例：
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * <p>
 * 提示：
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 *
 * @author donglin
 * @since 2020-07-09
 */
public class RestoreSpace {

    public int restoreSpace(String[] dictionary, String sentence) {
        Trie root = new Trie();
        for (String word : dictionary) {
            root.insert(word.toCharArray());
        }
        char[] chars = sentence.toCharArray();
        int n = sentence.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            Trie trie = root;
            for (int j = i; j >= 1; j--) {
                int k = chars[j - 1] - 'a';
                if (trie.next[k] == null) {
                    break;
                } else if (trie.next[k].isEnd) {
                    dp[i] = Math.min(dp[j - 1], dp[i]);
                }
                if (dp[i] == 0) {
                    break;
                }
                trie = trie.next[k];
            }
        }
        return dp[n];
    }

    private static class Trie {

        private final Trie[] next;
        private boolean isEnd;

        public Trie() {
            next = new Trie[26];
        }

        public void insert(char[] chars) {
            Trie curPos = this;
            for (int i = chars.length - 1; i >= 0; i--) {
                int t = chars[i] - 'a';
                if (curPos.next[t] == null) {
                    curPos.next[t] = new Trie();
                }
                curPos = curPos.next[t];
            }
            curPos.isEnd = true;
        }
    }

    @Test
    public void case1() {
        String[] dictionary = {"looked", "just", "like", "her", "brother"};
        String sentence = "jesslookedjustliketimherbrother";
        Assert.assertEquals(7, restoreSpace(dictionary, sentence));
    }

    @Test
    public void case2() {
        String[] dictionary = {"looked", "just"};
        String sentence = "lookedajust";
        Assert.assertEquals(1, restoreSpace(dictionary, sentence));
    }

    @Test
    public void case3() {
        String[] dictionary = {"looked", "just"};
        String sentence = "lookedjust";
        Assert.assertEquals(0, restoreSpace(dictionary, sentence));
    }

    @Test
    public void case4() {
        String[] dictionary = {"sssjjs", "hschjf", "hhh", "fhjchfcfshhfjhs", "sfh", "jsf", "cjschjfscscscsfjcjfcfcfh", "hccccjjfchcffjjshccsjscsc", "chcfjcsshjj", "jh", "h", "f", "s", "jcshs", "jfjssjhsscfc"};
        String sentence = "sssjjssfshscfjjshsjjsjchffffs";
        Assert.assertEquals(7, restoreSpace(dictionary, sentence));
    }

}
