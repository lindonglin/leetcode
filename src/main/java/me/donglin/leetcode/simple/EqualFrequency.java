package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2423. 删除字符使频率相同
 * @author donglin
 * @since 2023-04-29
 */
public class EqualFrequency extends BaseTest {

    public boolean equalFrequency(String word) {
        int[] counts = new int[26];
        for (char c : word.toCharArray()) {
            counts[c - 'a']++;
        }
        // key: 频次， value：有多少个字母是该频次
        Map<Integer, Integer> freqCount = new HashMap<>();
        for (int c : counts) {
            if (c > 0) {
                freqCount.put(c, freqCount.getOrDefault(c, 0) + 1);
            }
        }
        for (int c : counts) {
            if (c == 0) {
                continue;
            }
            int fc = freqCount.get(c);
            if (fc == 1) {
                freqCount.remove(c);
            } else {
                freqCount.put(c ,fc - 1);
            }
            if (c - 1 > 0) {
                freqCount.put(c - 1, freqCount.getOrDefault(c - 1, 0) + 1);
            }
            if (freqCount.size() == 1) {
                return true;
            }
            if (c - 1 > 0) {
                freqCount.put(c - 1, freqCount.get(c - 1) - 1);
                if (freqCount.get(c - 1) == 0) {
                    freqCount.remove(c - 1);
                }
            }
            freqCount.put(c, freqCount.getOrDefault(c, 0) + 1);
        }
        return false;
    }

    @Override
    public void case1() {
        Assert.assertTrue(equalFrequency("abcc"));
        Assert.assertFalse(equalFrequency("aazz"));
        Assert.assertTrue(equalFrequency("bac"));
        Assert.assertFalse(equalFrequency("ddaccb"));
    }
}
