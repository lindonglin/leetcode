package me.donglin.leetcode.medium;

import me.donglin.leetcode.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 *
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * @author donglin
 * @since 2020-10-20
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // 找到链表的中间位置
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 然后反转链表的后半段
        ListNode reverse = reverse(slow.next);
        slow.next = null;

        ListNode node = head;
        ListNode nodeNext, reverseNext;
        while (node != null && reverse != null) {
            nodeNext = node.next;
            reverseNext = reverse.next;
            node.next = reverse;
            reverse.next = nodeNext;
            node = nodeNext;
            reverse = reverseNext;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode first = head;
        ListNode second = first.next;
        ListNode third = second != null ? second.next : null;
        while (second != null) {
            second.next = first;
            first = second;
            second = third;
            third = second != null ? second.next : null;
        }
        head.next = null;
        return first;
    }

    @Test
    public void case1() {
        ListNode head = ListNode.getInstance(1, 2, 3, 4);
        ListNode expected = ListNode.getInstance(1, 4, 2, 3);
        reorderList(head);
        Assert.assertEquals(expected, head);
    }

    @Test
    public void case2() {
        ListNode head = ListNode.getInstance(1, 2, 3, 4, 5);
        ListNode expected = ListNode.getInstance(1, 5, 2, 4, 3);
        reorderList(head);
        Assert.assertEquals(expected, head);
    }

    @Test
    public void case3() {
        ListNode head = ListNode.getInstance(1);
        ListNode expected = ListNode.getInstance(1);
        reorderList(head);
        Assert.assertEquals(expected, head);
    }

}
