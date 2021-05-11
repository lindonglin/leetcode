package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


/**
 * 1723. 完成所有工作的最短时间
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 *
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。
 * 工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 *
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 *
 * 示例 1：
 * 输入：jobs = [3,2,3], k = 3
 * 输出：3
 * 解释：给每位工人分配一项工作，最大工作时间是 3 。
 *
 * 示例 2：
 * 输入：jobs = [1,2,4,7,8], k = 2
 * 输出：11
 * 解释：按下述方式分配工作：
 * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
 * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
 * 最大工作时间是 11 。
 *
 * 提示：
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 10^7
 *
 * @author donglin
 * @version 1.0
 * @since 2021/05/08
 */
public class MinimumTimeRequired {

    /**
     * 二分查找+枚举+回溯
     */
    public int minimumTimeRequired1(int[] jobs, int k) {
        int sum = 0;
        for (int j : jobs) {
            sum += j;
        }
        // 显然，最大的工作时间在 [left, right]区间内，
        // 因此可以通过二分查找枚举找出该区间中满足分配任务且最大工作时间最小的值，即是结果
        int left = sum / k;
        int right = sum;
        // 先排序，因为需要从最大工作量的任务开始分配
        Arrays.sort(jobs);
        while (left < right) {
            int mid = (left + right) / 2;
            if (check(jobs, k, mid)) {
                // 说明当前值能分配成功，那查找有没有更小的值也能满足
                right = mid;
            } else {
                // 说明无法分配，那只能往上继续了
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] jobs, int k, int limit) {
        // 当前工人分配的工作量
        int[] worker = new int[k];
        return backtrack(jobs, worker, 0, limit);
    }

    private boolean backtrack(int[] jobs, int[] worker, int i, int limit) {
        if (i >= jobs.length) {
            return true;
        }
        for (int j = 0; j < worker.length; j++) {
            if (worker[j] + jobs[i] <= limit) {
                worker[j] += jobs[i];
                if (backtrack(jobs, worker, i+1, limit)) {
                    return true;
                }
                worker[j] -= jobs[i];
            }
            // 1. 工人j没分配工作,明显不符合条件
            // 2. 该任务分配给工人j后达到limit，但是不满足条件(否则就不会到这里来了)，说明这个limit不行
            if (worker[j] == 0 || worker[j] + jobs[i] == limit) {
                break;
            }
        }
        return false;
    }

    /**
     * 动态规划+状态压缩
     */
    public int minimumTimeRequired2(int[] jobs, int k) {
        // c是用位表示的任务集合
        // sums[c]表示集合c里的总任务量
        // nums[i-1][c]表示给i个人分配任务c的可能方案中最小的最大单人工作时间
        int len = (1 << jobs.length) - 1;
        int[][] nums = new int[k][len + 1];
        int[] sums = new int[len + 1];
        for (int c = 1; c <= len; c++) {
            int x = Integer.numberOfTrailingZeros(c);
            int y = c - (1 << x);
            // 将集合c拆成集合j和任务x，显然y比c小，因此此时已经算出sums[y]了，而jobs[x]又是已知的
            sums[c] = sums[y] + jobs[x];
        }
        // 初始化单人分配任务的工作量，即nums[0][c]
        System.arraycopy(sums, 1, nums[0], 1, len);
        for (int i = 1; i < k; i++) {
            for (int c = 0; c <= len; c++) {
                int min = Integer.MAX_VALUE;
                for (int sc = c; sc != 0; sc = (sc - 1) & c) {
                    // 遍历c的子集sc,显然sc的补集为c-sc(用二进制位表示出来看就很显然)
                    min = Math.min(min, Math.max(nums[i - 1][c - sc], sums[sc]));
                }
                nums[i][c] = min;
            }
        }
        return nums[k-1][len];
    }

    @Test
    public void case1() {
        int[] jobs = {3, 2, 3};
        int k = 3;
        Assert.assertEquals(3, minimumTimeRequired1(jobs, k));
        Assert.assertEquals(3, minimumTimeRequired2(jobs, k));
    }

    @Test
    public void case2() {
        int[] jobs = {1, 2, 4, 7, 8};
        int k = 2;
        Assert.assertEquals(11, minimumTimeRequired1(jobs, k));
        Assert.assertEquals(11, minimumTimeRequired2(jobs, k));
    }

    @Test
    public void case3() {
        int[] jobs = {1, 2, 4, 7, 8};
        int k = 3;
        Assert.assertEquals(8, minimumTimeRequired1(jobs, k));
        Assert.assertEquals(8, minimumTimeRequired2(jobs, k));
    }

    @Test
    public void case4() {
        int[] jobs = {1, 2, 4, 7, 8, 8, 9, 10, 12};
        int k = 4;
        Assert.assertEquals(16, minimumTimeRequired1(jobs, k));
        Assert.assertEquals(16, minimumTimeRequired2(jobs, k));
    }

}
