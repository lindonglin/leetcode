package me.donglin.leetcode.medium;

import me.donglin.leetcode.ListNode;
import me.donglin.leetcode.TreeNode;
import me.donglin.leetcode.simple.IsBalancedTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * @author donglin
 * @since 2020-08-18
 */
public class SortedListToBst {

    private static final IsBalancedTree helper = new IsBalancedTree();

    @Test
    public void case1() {
        ListNode head = ListNode.getInstance(-10, -3, 0, 5, 9);
        Assert.assertTrue(helper.isBalanced(sortedListToBST(head)));
    }

    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        return sortedListToBST(nums, 0, nums.size() - 1);
    }

    public TreeNode sortedListToBST(List<Integer> nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = sortedListToBST(nums, start, mid - 1);
        root.right = sortedListToBST(nums, mid + 1, end);
        return root;
    }
}
