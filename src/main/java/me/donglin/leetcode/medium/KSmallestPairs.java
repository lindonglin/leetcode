package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 373. 查找和最小的 K 对数字
 * 给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 *
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 *
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 *
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * 示例 2:
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * 示例 3:
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 *
 *
 * 提示:
 * 1 <= nums1.length, nums2.length <= 105
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1 和 nums2 均为升序排列
 * 1 <= k <= 1000
 *
 * @author donglin
 * @since 2022-09-15
 */
public class KSmallestPairs {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>(k, Comparator.comparing(Node::getSum).reversed());
        for (int a: nums1) {
            for (int b : nums2) {
                Node node = new Node(a, b);
                if (queue.size() < k) {
                    queue.add(node);
                    continue;
                }
                //noinspection ConstantConditions
                if (node.sum >= queue.peek().sum) {
                    break;
                }
                queue.poll();
                queue.add(node);
            }
        }
        k = queue.size();
        List<List<Integer>> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(null);
        }
        Node node;
        int i = 0;
        while (i++ < k && !queue.isEmpty()) {
            node = queue.poll();
            result.set(k - i, Arrays.asList(node.x, node.y));
        }
        return result;
    }

    private static class Node {
        private final int x;
        private final int y;
        private final int sum;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.sum = x + y;
        }

        public int getSum() {
            return sum;
        }
    }

    @Test
    public void case1() {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        List<List<Integer>> expect = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 4), Arrays.asList(1, 6));
        Assert.assertTrue(CommonUtil.isEqualListLists(expect, kSmallestPairs(nums1, nums2, 3)));
    }
}
