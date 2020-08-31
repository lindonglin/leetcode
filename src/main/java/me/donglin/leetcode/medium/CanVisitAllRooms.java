package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * 841. 钥匙和房间
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 *
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，
 * 其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 *
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 *
 * 你可以自由地在房间之间来回走动。
 *
 * 如果能进入每个房间返回 true，否则返回 false。
 *
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 *
 * 示例 2：
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * 提示：
 *
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * 所有房间中的钥匙数量总计不超过 3000。
 *
 * @author donglin
 * @since 2020-08-31
 */
public class CanVisitAllRooms {

    @Test
    public void case1() {
        List<List<Integer>> rooms = asList(singletonList(1), singletonList(2), singletonList(3), emptyList());
        Assert.assertTrue(canVisitAllRooms(rooms));
    }

    @Test
    public void case2() {
        List<List<Integer>> rooms = asList(asList(1, 3), asList(3, 0, 1), singletonList(2), singletonList(0));
        Assert.assertFalse(canVisitAllRooms(rooms));
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visits = new boolean[rooms.size()];
        visits[0] = true;
        int[] stack = new int[rooms.size()];
        int top = 0;
        int visitNumber = 1;
        while (top >= 0) {
            for (int k : rooms.get(stack[top--])) {
                if (!visits[k]) {
                    visits[k] = true;
                    visitNumber++;
                    stack[++top] = k;
                }
            }
        }
        return visitNumber == rooms.size();
    }
}
