package me.donglin.leetcode.simple;

import me.donglin.leetcode.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 237. 删除链表中的节点
 * 请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
 *
 * 题目数据保证需要删除的节点 不是末尾节点 。
 *
 * @author donglin
 * @since 2021-11-02
 */
public class DeleteNode {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    @Test
    public void case1() {
        ListNode node = ListNode.getInstance(4, 5, 1, 9);
        deleteNode(node.next);
        Assert.assertEquals(node.toString(), "[4, 1, 9]");
    }

}
