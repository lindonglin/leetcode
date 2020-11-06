package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 *
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 * @author donglin
 * @since 2020/11/05
 */
public class LadderLength {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int target = -1;
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).equals(endWord)) {
                target = i;
                break;
            }
        }
        if (target == -1) {
            return 0;
        }
        int n = wordList.size();
        int[][] ladders = new int[n + 1][n + 1];
        int limit = n * 2 + 1;
        for (int i = 0; i < ladders.length; i++) {
            for (int j = 0; j < ladders.length; j++) {
                ladders[i][j] = limit;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isAdjacent(wordList.get(i), wordList.get(j))) {
                    ladders[i][j] = ladders[j][i] = 1;
                }
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (isAdjacent(wordList.get(i), beginWord)) {
                queue.offer(i);
                ladders[n][i] = 1;
            }
        }
        while (!queue.isEmpty()) {
            int k = queue.poll();
            for (int i = 0; i < n; i++) {
                if (ladders[n][i] >= limit && ladders[k][i] < limit) {
                    ladders[n][i] = Math.min(ladders[n][i], ladders[n][k] + ladders[k][i]);
                    if (i == target) {
                        return ladders[n][i] + 1;
                    }
                    queue.offer(i);
                }
            }
        }
        int result = ladders[n][target];
        return result >= limit ? 0 : result + 1;
    }

    private boolean isAdjacent(String s1, String s2) {
        int num = 0;
        for (int i = 0, size = s1.length(); i < size; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                num++;
            }
            if (num > 1) {
                return false;
            }
        }
        return num == 1;
    }

    @Test
    public void case1() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        Assert.assertEquals(5, ladderLength(beginWord, endWord, wordList));
    }

    @Test
    public void case2() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log");
        Assert.assertEquals(0, ladderLength(beginWord, endWord, wordList));
    }

    @Test
    public void case3() {
        String beginWord = "red";
        String endWord = "tax";
        List<String> wordList = Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
        Assert.assertEquals(4, ladderLength(beginWord, endWord, wordList));
    }


}
