package me.donglin.leetcode.medium;

import me.donglin.leetcode.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 *
 * 示例：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *
 * 提示：
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 *
 *
 * @author donglin
 * @since 2020-09-28
 */
public class Connect2 {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        if (root.left != null) {
            nodes.add(root.left);
        }
        if (root.right != null) {
            nodes.add(root.right);
        }
        connect(nodes);
        return root;
    }

    public void connect(List<Node> nodes) {
        if (nodes.isEmpty()) {
            return;
        }
        nodes.add(null);
        List<Node> newNodes = new ArrayList<>();
        Node node;
        for (int i = 0; i < nodes.size() - 1; i++) {
            node = nodes.get(i);
            node.next = nodes.get(i + 1);
            if (node.left != null) {
                newNodes.add(node.left);
            }
            if (node.right != null) {
                newNodes.add(node.right);
            }
        }
        connect(newNodes);
    }

    @Test
    public void case1() {
        Node root = Node.from(1, 2, 3, 4, 5, null, 7);
        Node expected = Node.from(1, 2, 3, 4, 5, null, 7);
        expected.left.next = expected.right;
        expected.left.left.next = expected.left.right;
        expected.left.right.next = expected.right.right;
        Assert.assertEquals(expected, connect(root));
    }

    @Test
    public void case2() {
        Node root = Node.from(1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8);
        Node expected = Node.from(1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8);
        expected.left.next = expected.right;
        expected.left.left.next = expected.left.right;
        expected.left.right.next = expected.right.right;
        expected.left.left.left.next = expected.right.right.right;
        Assert.assertEquals(expected, connect(root));
    }

}
