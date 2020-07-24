package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1025. 除数博弈
 *
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 *
 * 示例 1：
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 *
 * 示例 2：
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *
 * 提示：
 * 1 <= N <= 1000
 *
 * @author donglin
 * @since 2020-07-24
 */
public class DivisorGame {

    @Test
    public void case1() {
        Assert.assertTrue(divisorGame(2));
        Assert.assertFalse(divisorGame(3));
    }

    /**
     * n = 2时，显然 爱丽丝赢
     * 假设 n 为 2i及2i之内任意偶数时()，爱丽丝赢，
     * 则当 n = 2i + 2时，爱丽丝可选择1，这样轮到鲍勃时 n = 2i + 1
     * 此时n为奇数，因此鲍勃选择的数也只能是奇数，如此回到爱丽丝时 n 还是偶数，且不大于 2i。由假设可知，还是爱丽丝赢。
     * 由数学归纳法可知，当n为偶数时，爱丽丝赢。
     *
     * 当n为奇数时，我们已经推导出次数爱丽丝选完之后轮到鲍勃时n为偶数，因此鲍勃赢了，也就是说爱丽丝输了
     *
     * 综上所述，爱丽丝是否能赢取决于n是否为偶数
     */
    private boolean divisorGame(int N) {
        return (N & 1) == 0;
    }
}
