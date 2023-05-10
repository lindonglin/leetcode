package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * 2432. 处理用时最长的那个任务的员工
 * @author donglin
 * @since 2023-05-05
 */
public class HardestWorker extends BaseTest {

    public int hardestWorker(int n, int[][] logs) {
        int start = 0, maxTime = 0, res = -1;
        for (int[] log : logs) {
            int time = log[1] - start;
            if (time > maxTime) {
                maxTime = time;
                res = log[0];
            } else if (time == maxTime && log[0] < res) {
                res = log[0];
            }
            start = log[1];
        }
        return res;
    }

    @Override
    public void case1() {
        int n = 10;
        int[][] logs = {{0, 3}, {2, 5}, {0, 9}, {1, 15}};
        Assert.assertEquals(1, hardestWorker(n, logs));
    }

    @Override
    public void case2() {
        int n = 26;
        int[][] logs = {{1, 1}, {3, 7}, {2, 12}, {7, 17}};
        Assert.assertEquals(3, hardestWorker(n, logs));
    }

    @Test
    public void case3() {
        int n = 70;
        int[][] logs = {{36, 3}, {1, 5}, {12, 8}, {25, 9}, {53, 11}, {29, 12}, {52, 14}};
        Assert.assertEquals(12, hardestWorker(n, logs));
    }

}
