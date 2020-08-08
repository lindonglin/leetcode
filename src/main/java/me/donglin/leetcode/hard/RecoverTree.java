package me.donglin.leetcode.hard;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;


/**
 * 99. 恢复二叉搜索树
 *
 * 二叉搜索树中的两个节点被错误地交换。
 *
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 * 输入: [1,3,null,null,2]
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * 示例 2:
 * 输入: [3,1,4,null,null,2]
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * 输出: [2,1,4,null,null,3]
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 *
 * 进阶:
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 *
 * @author donglin
 * @since 2020-08-08
 */
public class RecoverTree {

    @Test
    public void case1() {
        TreeNode root = TreeNode.fullOrder(1, 3, null, null, 2);
        TreeNode expected = TreeNode.fullOrder(3, 1, null, null, 2);
        recoverTree(root);
        Assert.assertEquals(expected, root);
    }

    @Test
    public void case2() {
        TreeNode root = TreeNode.fullOrder(3, 1, 4, null, null, 2);
        TreeNode expected = TreeNode.fullOrder(2, 1, 4, null, null, 3);
        recoverTree(root);
        Assert.assertEquals(expected, root);
    }
    public void recoverTree(TreeNode root) {
        // 中序遍历时遍历到的上一个节点，两个错误的节点
        TreeNode[] cache = {new TreeNode(Integer.MIN_VALUE), null, null};
        recoverTree(root, cache);
        int tmp = cache[2].val;
        cache[2].val = cache[1].val;
        cache[1].val = tmp;
    }

    private boolean recoverTree(TreeNode curNode, TreeNode[] cache) {
        if (curNode == null) {
            return false;
        }
        if (recoverTree(curNode.left, cache)) {
            return true;
        }
        if (curNode.val < cache[0].val) {
            if (cache[1] == null) {
                // 第一个值小于后续节点值的节点node和node的下一个节点
                cache[1] = cache[0];
                cache[2] = curNode;
            } else {
                // 第二个值小于后续节点值的节点node
                cache[2] = curNode;
                return true;
            }
        }
        cache[0] = curNode;
        return recoverTree(curNode.right, cache);
    }
}
