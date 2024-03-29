package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 933. 最近的请求次数
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 *
 * 请你实现 RecentCounter 类：
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。
 * 确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 *
 *
 * 示例 1：
 * 输入：
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * 输出：
 * [null, 1, 2, 3, 3]
 * 解释：
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
 * recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
 *
 * 提示：
 * 1 <= t <= 10^9
 * 保证每次对 ping 调用所使用的 t 值都 严格递增
 * 至多调用 ping 方法 10^4 次
 *
 * @author donglin
 * @since 2022-05-06
 */
public class RecentCounter {

    private final List<Integer> list = new LinkedList<>();

    public RecentCounter() {

    }

    public int ping(int t) {
        list.add(t);
        int limit = t - 3000;
        while (!list.isEmpty() && list.get(0) < limit) {
            list.remove(0);
        }
        return list.size();
    }

    @Test
    public void case1() {
        RecentCounter counter = new RecentCounter();
        Assert.assertEquals(1, counter.ping(1));
        Assert.assertEquals(2, counter.ping(100));
        Assert.assertEquals(3, counter.ping(3001));
        Assert.assertEquals(3, counter.ping(3002));
    }

}
