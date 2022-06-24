package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 515. 在每个树行中找最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 * 示例1：
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 *
 * 示例2：
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 *
 * 提示：
 * 二叉树的节点个数的范围是 [0,10^4]
 * -2^31 <= Node.val <= 2^31 - 1
 *
 * @author donglin
 * @since 2022-06-24
 */
public class LargestValues {

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue1 = new LinkedList<>();
        Deque<TreeNode> queue2 = new LinkedList<>();
        Deque<TreeNode> tmp;
        queue1.add(root);
        int value = Integer.MIN_VALUE;
        while (!queue1.isEmpty()) {
            TreeNode node = queue1.poll();
            value = Math.max(value, node.val);
            if (node.left != null) {
                queue2.add(node.left);
            }
            if (node.right != null) {
                queue2.add(node.right);
            }
            if (queue1.isEmpty()) {
                res.add(value);
                tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;
                value = Integer.MIN_VALUE;
            }
        }
        return res;
    }

    @Test
    public void case1() {
        TreeNode root = TreeNode.from(1, 3, 2, 5, 3, null, 9);
        List<Integer> expected = Arrays.asList(1, 3, 9);
        Assert.assertTrue(CommonUtil.isEquals(expected, largestValues(root)));
    }

    @Test
    public void case2() {
        TreeNode root = TreeNode.from(1, 2, 3);
        List<Integer> expected = Arrays.asList(1, 3);
        Assert.assertTrue(CommonUtil.isEquals(expected, largestValues(root)));
    }

}
