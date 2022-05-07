package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 433. 最小基因变化
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。
 * 如果无法完成此基因变化，返回 -1 。
 *
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 *
 * 示例 1：
 * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * 输出：1
 *
 * 示例 2：
 * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 输出：2
 *
 * 示例 3：
 * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 输出：3
 *
 * 提示：
 * start.length == 8
 * end.length == 8
 * 0 <= bank.length <= 10
 * bank[i].length == 8
 * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 *
 * @author donglin
 * @since 2022-05-07
 */
public class MinMutation {

    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        if (bank == null) {
            bank = new String[0];
        }
        int n = bank.length + 2;
        char[][] all = new char[8][n];
        boolean[][] flag = new boolean[n][n];
        all[0] = start.toCharArray();
        int i = 1;
        for (String b : bank) {
            all[i++] = b.toCharArray();
        }
        all[i] = end.toCharArray();
        for (i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = 0;
                for (int k = 0; k < 8; k++) {
                    if (all[i][k] != all[j][8]) {
                        diff++;
                        if (diff > 1) {
                            flag[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        Integer[][] dict = new Integer[n + 1][n + 1];
        for (i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (flag[i][j]) {

                }
            }
        }
        return 0;
    }

    @Test
    public void case1() {
        String start = "AACCGGTT", end = "AACCGGTA";
        String[] bank = {"AACCGGTA"};
        Assert.assertEquals(1, minMutation(start, end, bank));
    }

    @Test
    public void case2() {
        String start = "AACCGGTT", end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        Assert.assertEquals(2, minMutation(start, end, bank));
    }

    @Test
    public void case3() {
        String start = "AAAAACCC", end = "AACCCCCC";
        String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        Assert.assertEquals(2, minMutation(start, end, bank));
    }

}
