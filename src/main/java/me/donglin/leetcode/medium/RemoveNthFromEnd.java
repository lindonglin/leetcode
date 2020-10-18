package me.donglin.leetcode.medium;

import me.donglin.leetcode.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 * @author donglin
 * @since 2020-10-18
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode remove = head;
        for (int i = 0; i < n; i++) {
            remove = remove.next;
        }
        if (remove == null) {
            return head.next;
        }
        ListNode node = head;
        while (remove.next != null) {
            remove = remove.next;
            node = node.next;
        }
        node.next = node.next.next;
        return head;
    }

    @Test
    public void case1() {
        ListNode head = ListNode.getInstance(1, 2, 3, 4, 5);
        ListNode expected = ListNode.getInstance(1, 2, 3, 5);
        Assert.assertEquals(expected, removeNthFromEnd(head, 2));
    }

    @Test
    public void case2() {
        ListNode head = ListNode.getInstance(1);
        ListNode expected = ListNode.getInstance();
        Assert.assertEquals(expected, removeNthFromEnd(head, 1));
    }
}
