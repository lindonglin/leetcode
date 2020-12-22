package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * @author donglin
 * @since 2020-12-22
 */
public class ZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> item;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode node;
        while (!queue.isEmpty()) {
            item = new ArrayList<>();
            for (int i = 0, size = queue.size(); i < size; i++) {
                node = queue.poll();
                assert node != null;
                item.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (result.size() % 2 == 1) {
                Collections.reverse(item);
            }
            result.add(item);
        }
        return result;
    }

    @Test
    public void case1() {
        TreeNode root = TreeNode.from(3, 9, 20, null, null, 15, 7);
        List<List<Integer>> expected = Arrays.asList(Collections.singletonList(3), Arrays.asList(20, 9), Arrays.asList(15, 7));
        Assert.assertTrue(CommonUtil.isEqualListLists(expected, zigzagLevelOrder(root)));
    }

}
