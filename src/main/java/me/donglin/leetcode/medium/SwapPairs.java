package me.donglin.leetcode.medium;

import me.donglin.leetcode.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 * @author donglin
 * @since 2020-10-13
 */
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        ListNode next = newHead.next;
        newHead.next = head;
        head.next = swapPairs(next);
        return newHead;
    }

    @Test
    public void case1() {
        ListNode head = ListNode.getInstance(1, 2, 3, 4);
        ListNode expected = ListNode.getInstance(2, 1, 4, 3);
        Assert.assertEquals(expected, swapPairs(head));
    }

    @Test
    public void case2() {
        ListNode head = ListNode.getInstance();
        ListNode expected = ListNode.getInstance();
        Assert.assertEquals(expected, swapPairs(head));
    }

    @Test
    public void case3() {
        ListNode head = ListNode.getInstance(1);
        ListNode expected = ListNode.getInstance(1);
        Assert.assertEquals(expected, swapPairs(head));
    }

    @Test
    public void case4() {
        ListNode head = ListNode.getInstance(1, 2, 3, 4, 5, 6, 7);
        ListNode expected = ListNode.getInstance(2, 1, 4, 3, 6, 5, 7);
        Assert.assertEquals(expected, swapPairs(head));
    }
}
