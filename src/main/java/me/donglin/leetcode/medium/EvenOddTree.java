package me.donglin.leetcode.medium;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 1609. 奇偶树
 *
 * @author donglin
 * @since 2021-12-25
 */
public class EvenOddTree {

    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        boolean odd = true;
        queue.offer(root);
        queue.offer(null);
        int preVal = 0;
        TreeNode node;
        while (queue.size() > 1) {
            node = queue.poll();
            if (node == null) {
                odd = !odd;
                queue.offer(null);
                preVal = Integer.MAX_VALUE * (odd ? -1 : 1);
                continue;
            }
            if (odd && !((node.val & 1) == 1 && node.val > preVal)) {
                return false;
            } else if (!odd && !((node.val & 1) == 0 && node.val < preVal)) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            preVal = node.val;
        }
        return true;
    }

    @Test
    public void case1() {
        Assert.assertTrue(isEvenOddTree(TreeNode.from(1, 10, 4, 3, null, 7, 9, 12, 8, 6, null, null, 2)));
    }

    @Test
    public void case2() {
        Assert.assertFalse(isEvenOddTree(TreeNode.from(5, 4, 2, 3, 3, 7)));
    }

    @Test
    public void case3() {
        Assert.assertFalse(isEvenOddTree(TreeNode.from(5, 9, 1, 3, 5, 7)));
    }

    @Test
    public void case4() {
        Assert.assertTrue(isEvenOddTree(TreeNode.from(1)));
    }

    @Test
    public void case5() {
        Assert.assertTrue(isEvenOddTree(TreeNode.from(11, 8, 6, 1, 3, 9, 11, 30, 20, 18, 16, 12, 10, 4, 2, 17)));
    }
}
