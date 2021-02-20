package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 697. 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1：
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 *
 * 示例 2：
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 *
 * 提示：
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 *
 * @author donglin
 * @version 1.0
 * @since 2021/02/20
 */
public class FindShortestSubArray {

    public int findShortestSubArray(int[] nums) {
        int maxValue = 0;
        for (int value : nums) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        Node[] nodes = new Node[maxValue + 1];
        Node node;
        for (int i = 0; i < nums.length; i++) {
            node = nodes[nums[i]];
            if (node == null) {
                nodes[nums[i]] = new Node(i);
            } else {
                node.add(i);
            }
        }
        int maxDegree = 0;
        int minSize = nums.length;
        for (Node n : nodes) {
            if (n == null) {
                continue;
            }
            if (n.degree() > maxDegree) {
                maxDegree = n.degree();
                minSize = n.size();
            } else if (n.degree() == maxDegree && n.size() < minSize) {
                minSize = n.size();
            }
        }
        return minSize;
    }

    private static class Node {
        private final int start;
        private int end;
        private int degree;

        public Node(int i ) {
            start = i;
            end = i;
            degree = 1;
        }

        public void add(int i) {
            end = i;
            degree++;
        }

        public int size() {
            return end - start + 1;
        }

        public int degree() {
            return degree;
        }
    }

    @Test
    public void case1() {
        int[] nums = {1, 2, 2, 3, 1};
        Assert.assertEquals(2, findShortestSubArray(nums));
    }

    @Test
    public void case2() {
        int[] nums = {1, 2, 2, 3, 1, 4, 2};
        Assert.assertEquals(6, findShortestSubArray(nums));
    }

}
