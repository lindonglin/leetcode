package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * @author donglin
 * @since 2020-08-02
 */
public class Flatten {

    @Test
    public void case1() {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(5, null, new TreeNode(6)));
        TreeNode expected = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4, new TreeNode(5, new TreeNode(6))))));
        flatten(root);
        Assert.assertEquals(expected, root);
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        link(root);
    }

    public TreeNode link(TreeNode node) {
        TreeNode tail = node;
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left != null) {
            tail = link(left);
            tail.left = null;
            tail.right = right;
            node.left = null;
            node.right = left;
        }
        if (right != null) {
            tail = link(right);
            tail.left = null;
        }
        return tail;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode right) {
            this.val = val;
            this.right = right;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TreeNode treeNode = (TreeNode) o;
            return val == treeNode.val &&
                    Objects.equals(left, treeNode.left) &&
                    Objects.equals(right, treeNode.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, left, right);
        }
    }
}
