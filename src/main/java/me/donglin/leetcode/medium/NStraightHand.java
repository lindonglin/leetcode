package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 846. 一手顺子
 *
 * @author donglin
 * @since 2021-12-30
 */
public class NStraightHand {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if ((hand.length % groupSize) != 0) {
            return false;
        }
        Arrays.sort(hand);
        boolean[] flag = new boolean[hand.length];
        flag[0] = true;
        int beginValue = hand[0];
        int hi = 1;
        Integer nextBeginIndex = null;
        int serial = 1;
        while (hi < hand.length) {
            if (serial == groupSize) {
                if (nextBeginIndex != null) {
                    hi = nextBeginIndex;
                    nextBeginIndex = null;
                }
                beginValue = hand[hi];
                flag[hi] = true;
                serial = 1;
                hi++;
                continue;
            }
            if (flag[hi]) {
                hi++;
                continue;
            }
            if (hand[hi] == beginValue + serial) {
                serial++;
                flag[hi++] = true;
            } else if (hand[hi] == beginValue + serial - 1) {
                if (nextBeginIndex == null) {
                    nextBeginIndex = hi;
                }
                hi++;
            } else {
                return false;
            }
        }
        return serial == groupSize;
    }

    @Test
    public void case1() {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        Assert.assertTrue(isNStraightHand(hand, groupSize));
    }

    @Test
    public void case2() {
        int[] hand = {1, 2, 3, 4, 5};
        int groupSize = 4;
        Assert.assertFalse(isNStraightHand(hand, groupSize));
    }

    @Test
    public void case3() {
        int[] hand = {8, 9, 7, 1, 2, 3, 4, 5};
        int groupSize = 4;
        Assert.assertFalse(isNStraightHand(hand, groupSize));
    }

    @Test
    public void case4() {
        int[] hand = {5, 3, 4, 1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        Assert.assertTrue(isNStraightHand(hand, groupSize));
    }

    @Test
    public void case5() {
        int[] hand = {1, 2, 3, 4, 5, 6};
        int groupSize = 2;
        Assert.assertTrue(isNStraightHand(hand, groupSize));
    }

    @Test
    public void case6() {
        int[] hand = {5, 1};
        int groupSize = 1;
        Assert.assertTrue(isNStraightHand(hand, groupSize));
    }

    @Test
    public void case7() {
        int[] hand = {1, 1, 2, 2, 3, 3};
        int groupSize = 2;
        Assert.assertFalse(isNStraightHand(hand, groupSize));
    }
    @Test
    public void case8() {
        int[] hand = {1, 1, 2, 2, 3, 3};
        int groupSize = 3;
        Assert.assertTrue(isNStraightHand(hand, groupSize));
    }
}
