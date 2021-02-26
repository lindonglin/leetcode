package me.donglin.leetcode.hard;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 1178. 猜字谜
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 *
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 *
 * 单词 word 中包含谜面 puzzle 的第一个字母。
 * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；
 * 而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 *
 *
 * 示例：
 *
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 *
 * 提示：
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] 都是小写英文字母。
 * 每个 puzzles[i] 所包含的字符都不重复。
 *
 * @author donglin
 * @version 1.0
 * @since 2021/02/26
 */
public class FindNumOfValidWords {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> result = new ArrayList<>(puzzles.length);
        int[] wordsBits = pretreatment(words);
        int[] puzzlesBits = pretreatment(puzzles);
        for (int i = 0; i < puzzles.length; i++) {
            int puzzleBits = puzzlesBits[i];
            int puzzleFirstCharBit = 1 << (puzzles[i].charAt(0) - 'a');
            int count = 0;
            for (int wordBits : wordsBits) {
                if ((wordBits & puzzleFirstCharBit) > 0 && (puzzleBits & wordBits) == wordBits) {
                    count++;
                }
            }
            result.add(count);
        }
        return result;
    }

    private int[] pretreatment(String[] strings) {
        int[] bitsArr = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            int bits =  0;
            for (char c : strings[i].toCharArray()) {
                bits |= (1 << (c - 'a'));
            }
            bitsArr[i] = bits;
        }
        return bitsArr;
    }


    @Test
    public void case1() {
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        List<Integer> expected = Arrays.asList(1, 1, 3, 2, 4, 0);
        Assert.assertTrue(CommonUtil.isEquals(expected, findNumOfValidWords(words, puzzles)));
    }

}
