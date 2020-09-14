package me.donglin.leetcode.medium;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author donglin
 * @since 2020-09-14
 */
public class InorderTraversal {

    @Test
    public void case1() {
        TreeNode root = TreeNode.from(1, null, 2, 3);
        List<Integer> expected = Arrays.asList(1, 3, 2);
        Assert.assertEquals(expected, inorderTraversal(root));
    }


    @Test
    public void case2() {
        TreeNode root = TreeNode.from(1, 2, 3, 4, 5, null, 6);
        List<Integer> expected = Arrays.asList(4, 2, 5, 1, 3, 6);
        Assert.assertEquals(expected, inorderTraversal(root));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.pollLast();
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (node.left != null) {
                queue.offer(new TreeNode(node.val));
                queue.offer(node.left);
            } else {
                result.add(node.val);
            }
        }
        return result;
    }
}
