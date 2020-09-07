package me.donglin.leetcode.simple;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 107. 二叉树的层次遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * @author donglin
 * @since 2020-09-07
 */
public class LevelOrderBottom {

    @Test
    public void case1() {
        TreeNode root = TreeNode.fullOrder(3, 9, 20, null, null, 15, 7);
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(15, 7), Arrays.asList(9, 20), Collections.singletonList(3));
        Assert.assertEquals(expected, levelOrderBottom(root));
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        levelOrderBottom(root, list, 0);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    public void levelOrderBottom(TreeNode node, List<List<Integer>> list, int high) {
        if (node == null) {
            return;
        }
        if (list.size() < high + 1) {
            list.add(new ArrayList<>());
        }
        list.get(high).add(node.val);
        levelOrderBottom(node.left, list, high + 1);
        levelOrderBottom(node.right, list, high + 1);
    }
}
