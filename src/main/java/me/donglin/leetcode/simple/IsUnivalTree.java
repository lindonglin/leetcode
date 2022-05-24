package me.donglin.leetcode.simple;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 965. 单值二叉树
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 *
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 *
 * 示例 1：
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：[2,2,2,5,2]
 * 输出：false
 *
 * 提示：
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 *
 * @author donglin
 * @since 2022-05-24
 */
public class IsUnivalTree {

    public boolean isUnivalTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode node;
        int value = root.val;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.val != value) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return true;
    }

    @Test
    public void case1() {
        Assert.assertTrue(isUnivalTree(TreeNode.from(1, 1, 1, 1, 1, null, 1)));
    }


    @Test
    public void case2() {
        Assert.assertFalse(isUnivalTree(TreeNode.from(2, 2, 2, 5, 2)));
    }

    @Test
    public void case3() {
        Assert.assertFalse(isUnivalTree(TreeNode.from(9, 9, 6, 9, 9)));
    }

}
