package me.donglin.leetcode.simple;

import me.donglin.leetcode.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author donglin
 * @since 2020-10-23
 */
public class IsPalindrome {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 然后反转链表的后半段
        ListNode reverse = reverse(slow.next);
        while (head != null && reverse != null) {
            if (head.val != reverse.val) {
                return false;
            }
            head = head.next;
            reverse = reverse.next;
        }
        return true;
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
        ListNode head = ListNode.getInstance(1, 2);
        Assert.assertFalse(isPalindrome(head));
    }

    @Test
    public void case2() {
        ListNode head = ListNode.getInstance(1, 2, 2, 1);
        Assert.assertTrue(isPalindrome(head));
    }

    @Test
    public void case3() {
        ListNode head = ListNode.getInstance(1, 2, 1);
        Assert.assertTrue(isPalindrome(head));
    }
}
