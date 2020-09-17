package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 685. 冗余连接 II
 * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
 *
 * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附
 * 加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
 *
 * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 *
 * 示例 1:
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的有向图如下:
 *   1
 *  / \
 * v   v
 * 2-->3
 *
 * 示例 2:
 * 输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * 输出: [4,1]
 * 解释: 给定的有向图如下:
 * 5 <- 1 -> 2
 *      ^    |
 *      |    v
 *      4 <- 3
 *
 * 注意:
 * 二维数组大小的在3到1000范围内。
 * 二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
 *
 * @author donglin
 * @since 2020-09-17
 */
public class FindRedundantDirectedConnection {

    @Test
    public void case1() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] expected = {2, 3};
        Assert.assertArrayEquals(expected, findRedundantDirectedConnection(edges));
    }

    @Test
    public void case2() {
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}};
        int[] expected = {4, 1};
        Assert.assertArrayEquals(expected, findRedundantDirectedConnection(edges));
    }

    @Test
    public void case3() {
        int[][] edges = {{2, 1}, {3, 1}, {4, 2}, {1, 4}};
        int[] expected = {2, 1};
        Assert.assertArrayEquals(expected, findRedundantDirectedConnection(edges));
    }


    public int[] findRedundantDirectedConnection(int[][] edges) {
        // 下标是节点，值是该节点的父节点
        int[] nums = new int[edges.length + 1];
        // tmp[0]有个多余的父节点，可能是tmp[1]
        int[] tmp = null;
        for (int[] pair : edges) {
            int p = pair[0];
            int c = pair[1];
            if (nums[c] > 0) {
                tmp = new int[]{c, p};
            } else {
                nums[c] = p;
            }
        }
        Set<Integer> cycle = new HashSet<>();
        if (tmp != null) {
            // 说明tmp[0]有一条边就是结果多于的边必定是多余的
            if (cycle(nums, tmp[0], cycle) != null) {
                return new int[]{nums[tmp[0]], tmp[0]};
            }
            return new int[]{tmp[1], tmp[0]};
        }
        // 说明有环,找出环中最后的边
        int i = 1;
        while (i < nums.length) {
            if (nums[i] == 0) {
                i++;
                continue;
            }
            // 是否有环
            Set<Pair> list = cycle(nums, i, cycle);
            if (list != null) {
                for (int k = edges.length - 1; k >= 0; k--) {
                    if (list.contains(new Pair(edges[k]))) {
                        return edges[k];
                    }
                }
            }
            i++;
        }
        return new int[0];
    }

    private Set<Pair> cycle(int[] nums, int k, Set<Integer> cycle) {
        cycle.clear();
        cycle.add(k);
        Set<Pair> list = new HashSet<>();
        while (nums[k] > 0) {
            list.add(new Pair(nums[k], k));
            if (cycle.contains(nums[k])) {
                return list;
            }
            int num = nums[k];
            cycle.add(num);
            k = num;
        }
        return null;
    }


    public static class Pair {
        private final int p;
        private final int c;

        public Pair(int p, int c) {
            this.p = p;
            this.c = c;
        }

        public Pair(int[] nums) {
            this.p = nums[0];
            this.c = nums[1];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return p == pair.p &&
                    c == pair.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(p, c);
        }
    }

}
