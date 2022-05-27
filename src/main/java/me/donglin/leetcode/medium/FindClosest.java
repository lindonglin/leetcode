package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 17.11. 单词距离
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
 * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 * 示例：
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 *
 * 提示：
 * words.length <= 100000
 *
 * @author donglin
 * @since 2022-05-27
 */
public class FindClosest {

    public int findClosest(String[] words, String word1, String word2) {
        int n = words.length;
        int pre1 = Integer.MIN_VALUE + n, pre2 = pre1, res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (words[i].equals(word1)) {
                res = Math.min(res, i - pre2);
                pre1 = i;
            } else if (words[i].equals(word2)) {
                res = Math.min(res, i - pre1);
                pre2 = i;
            }
        }
        return pre1 >= 0 && pre2 >= 0 ? res : 0;
    }

    /**
     * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同
     */
    public int findClosest2(String[] words, String word1, String word2) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            int hashcode = words[i].hashCode();
            if (!map.containsKey(hashcode)) {
                map.put(hashcode, new ArrayList<>());
            }
            map.get(words[i].hashCode()).add(i);
        }
        List<Integer> positions1 = map.get(word1.hashCode());
        if (positions1 == null) {
            return 0;
        }
        List<Integer> positions2 = map.get(word2.hashCode());
        if (positions2 == null) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (Integer i1 : positions1) {
            for (Integer i2 : positions2) {
                res = Math.min(res, Math.abs(i1 - i2));
            }
        }
        return res;
    }

    @Test
    public void case1() {
        String[] words = {"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"};
        String word1 = "a", word2 = "student";
        Assert.assertEquals(1, findClosest(words, word1, word2));
        Assert.assertEquals(1, findClosest2(words, word1, word2));
    }

    @Test
    public void case2() {
        String[] words = {"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"};
        String word1 = "an", word2 = "student";
        Assert.assertEquals(0, findClosest(words, word1, word2));
        Assert.assertEquals(0, findClosest2(words, word1, word2));
    }
}
