package me.donglin.leetcode;

/**
 * @author donglin
 * @since 2020-08-18
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode getInstance(int... values) {
        ListNode flag = new ListNode(0);
        ListNode node = flag;
        for (int val : values) {
            node.next = new ListNode(val);
            node = node.next;
        }
        return flag.next;
    }

}
