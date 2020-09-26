package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * @author donglin
 * @since 2020-09-26
 */
public class PathSum {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum(root, sum, result, new ArrayList<>());
        return result;
    }

    public void pathSum(TreeNode node, int target, List<List<Integer>> result, List<Integer> temp) {
        if (node == null) {
            return;
        }
        temp.add(node.val);
        if (node.val == target && node.left == null && node.right == null) {
            result.add(new ArrayList<>(temp));
        } else {
            pathSum(node.left, target - node.val, result, temp);
            pathSum(node.right, target - node.val, result, temp);
        }
        temp.remove(temp.size() - 1);
    }

    @Test
    public void case1() {
        TreeNode root = TreeNode.from(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1);
        int sum = 22;
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(5, 4, 11, 2), Arrays.asList(5, 8, 4, 5));
        Assert.assertTrue(CommonUtil.isEquals(expected, pathSum(root, sum)));
    }
}
