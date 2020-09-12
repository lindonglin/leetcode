package me.donglin.leetcode.simple;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 *
 * 示例 1：
 *
 * 输入：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 *
 * 提示：
 * 节点值的范围在32位有符号整数范围内。
 *
 * @author donglin
 * @since 2020-09-12
 */
public class AverageOfLevels {

    @Test
    public void case1() {
        TreeNode root = TreeNode.fullOrder(3, 9, 20, null, null, 15, 7);
        List<Double> expected = Arrays.asList(3D, 14.5, 11D);
        Assert.assertEquals(expected, averageOfLevels(root));
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> counts = new ArrayList<>();
        List<Long> sums = new ArrayList<>();
        averageOfLevels(root, 0, counts, sums);
        List<Double> result = new ArrayList<>(counts.size());
        for (int i = 0, size = counts.size(); i < size; i++) {
            result.add(1.0 * sums.get(i) / counts.get(i));
        }
        return result;
    }

    public void averageOfLevels(TreeNode node, int level, List<Integer> counts, List<Long> sums) {
        if (node == null) {
            return;
        }
        if (level < counts.size()) {
            counts.set(level, counts.get(level) + 1);
            sums.set(level, sums.get(level) + node.val);
        } else {
            counts.add(1);
            sums.add((long) node.val);
        }
        averageOfLevels(node.left, level + 1, counts, sums);
        averageOfLevels(node.right, level + 1, counts, sums);
    }

}
