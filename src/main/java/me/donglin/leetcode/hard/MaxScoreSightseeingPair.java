package me.donglin.leetcode.hard;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;

/**
 * 1014. 最佳观光组合
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 * 返回一对观光景点能取得的最高分。
 *
 * 示例：
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *
 *
 * 提示：
 * 2 <= A.length <= 50000
 * 1 <= A[i] <= 1000
 *
 * @author donglin
 * @since 2020-06-17
 */
public class MaxScoreSightseeingPair extends BaseTest {

    private int maxScoreSightseeingPair(int[] A) {
        int maxScore = 0;
        // 表示 max(后面的数-距离)
        int maxValue = A[A.length - 1] - 1;
        for (int i = A.length - 2; i >= 0; i--) {
            maxScore = Math.max(maxScore, maxValue + A[i]);
            maxValue = Math.max(maxValue , A[i]) - 1;
        }
        return maxScore;
    }


    @Override
    public void case1() {
        Assert.assertEquals(11, maxScoreSightseeingPair(new int[]{8,1,5,2,6}));
        Assert.assertEquals(3, maxScoreSightseeingPair(new int[]{2, 2}));
        Assert.assertEquals(10, maxScoreSightseeingPair(new int[]{3, 1, 1, 1, 1, 10}));
        Assert.assertEquals(11, maxScoreSightseeingPair(new int[]{6, 1, 1, 1, 1, 10}));
    }

}
