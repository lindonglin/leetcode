package me.donglin.leetcode;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 * @author donglin
 * @since 2020-09-28
 */
public class Node {

    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right, Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }


    public static Node from(Integer... nums) {
        if (nums == null) {
            return null;
        }
        Node root = new Node(nums[0]);
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        Node node, left, right;
        while (i < nums.length && !queue.isEmpty()) {
            if (nums[i] != null) {
                left = new Node(nums[i]);
                queue.offer(left);
            } else {
                left = null;
            }
            i++;
            if (i < nums.length && nums[i] != null) {
                right = new Node(nums[i]);
                queue.offer(right);
            } else {
                right = null;
            }
            node = queue.poll();
            assert node != null;
            node.left = left;
            node.right = right;
            i++;
        }
        return root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return val == node.val &&
                Objects.equals(left, node.left) &&
                Objects.equals(right, node.right) &&
                Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right, next);
    }
}
