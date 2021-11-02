package me.donglin.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode node = (ListNode) o;
        return val == node.val &&
                Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();
        ListNode node = this;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list.toString();
    }
}
