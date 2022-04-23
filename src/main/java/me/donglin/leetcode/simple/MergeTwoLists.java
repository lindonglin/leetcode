package me.donglin.leetcode.simple;

import me.donglin.leetcode.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * @author donglin
 * @since 2022-04-23
 */
public class MergeTwoLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode(0);
        ListNode cur = result;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }
        return result.next;
    }

    @Test
    public void case1() {
        Assert.assertEquals(ListNode.getInstance(1, 1, 2, 3, 4, 4),
                mergeTwoLists(ListNode.getInstance(1, 2, 4), ListNode.getInstance(1, 3, 4)));
    }

    @Test
    public void case2() {
        Assert.assertNull(mergeTwoLists(null, null));
    }

    @Test
    public void case3() {
        Assert.assertEquals(ListNode.getInstance(0), mergeTwoLists(null, ListNode.getInstance(0)));
    }
}
