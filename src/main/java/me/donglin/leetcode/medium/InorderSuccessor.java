package me.donglin.leetcode.medium;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 面试题 04.06. 后继者
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 * 输入: root = [2,1,3], p = 1
 *   2
 *  / \
 * 1   3
 * 输出: 2
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 * 输出: null
 *
 * @author donglin
 * @since 2022-05-16
 */
public class InorderSuccessor {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(-1);
        head.right = root;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(head);
        TreeNode node, cur;
        while (!stack.isEmpty()) {
            node = stack.pop();
            cur = node.right;
            while (cur != null) {
                if (cur.val == p.val) {
                    return p.right != null ? p.right : stack.peek();
                }
                stack.push(cur);
                cur = cur.left;
            }
            if (node.val == p.val) {
                return p.right != null ? p.right : stack.peek();
            }
        }
        return null;
    }

    @Test
    public void case1() {
        TreeNode root = TreeNode.from(2, 1, 3);
        TreeNode p = root.left;
        Assert.assertEquals(root, inorderSuccessor(root, p));
    }

    @Test
    public void case2() {
        TreeNode root = TreeNode.from(5, 3, 6, 2, 4, null, null, 1);
        TreeNode p = root.right;
        Assert.assertNull(inorderSuccessor(root, p));
    }


    @Test
    public void case3() {
        TreeNode root = TreeNode.from(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
        TreeNode p = root.left;
        Assert.assertEquals(root.left.right.left, inorderSuccessor(root, p));
    }
}
