package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 953. 验证外星语词典
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 *
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；
 * 否则，返回 false。
 *
 * 示例 1：
 * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * 输出：true
 * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
 *
 * 示例 2：
 * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * 输出：false
 * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
 *
 * 示例 3：
 * 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * 输出：false
 * 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，
 * 其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
 *
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * 在 words[i] 和 order 中的所有字符都是英文小写字母。
 *
 * @author donglin
 * @since 2022-05-17
 */
public class IsAlienSorted {

    public boolean isAlienSorted(String[] words, String order) {
        int[] orders = new int[26];
        for (int i = 0; i < 26; i++) {
            orders[order.charAt(i) - 'a'] = i;
        }
        char[] pre = {};
        for (String word : words) {
            char[] chars = word.toCharArray();
            if (!isAlienSorted(pre, chars, orders)) {
                return false;
            }
            pre = chars;
        }
        return true;
    }

    private boolean isAlienSorted(char[] word1, char[] word2, int[] orders) {
        int n = Math.min(word1.length, word2.length);
        for (int i = 0; i < n; i++) {
            int diff = orders[word1[i]-'a'] - orders[word2[i] - 'a'];
            if (diff < 0) {
                return true;
            } else if (diff > 0){
                return false;
            }
        }
        return word1.length <= word2.length;
    }

    @Test
    public void case1() {
        String[] words = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        Assert.assertTrue(isAlienSorted(words, order));
    }

    @Test
    public void case2() {
        String[] words = {"word", "world", "row"};
        String order = "worldabcefghijkmnpqstuvxyz";
        Assert.assertFalse(isAlienSorted(words, order));
    }

    @Test
    public void case3() {
        String[] words = {"apple", "app"};
        String order = "abcdefghijklmnopqrstuvwxyz";
        Assert.assertFalse(isAlienSorted(words, order));
    }

    @Test
    public void case4() {
        String[] words = {"kuvp", "q"};
        String order = "ngxlkthsjuoqcpavbfdermiywz";
        Assert.assertTrue(isAlienSorted(words, order));
    }

}
