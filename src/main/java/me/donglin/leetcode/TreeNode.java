package me.donglin.leetcode;

import java.util.Objects;

/**
 * @author donglin
 * @since 2020-08-05
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode fullOrder(Integer... nums) {
        if (nums == null) {
            return null;
        }
        return build(nums, 0);
    }

    private static TreeNode build(Integer[] nums, int i) {
        if (i >= nums.length || nums[i] == null) {
            return null;
        }
        TreeNode node = new TreeNode(nums[i]);
        node.left = build(nums, i * 2 + 1);
        node.right = build(nums, i * 2 + 2);
        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreeNode node = (TreeNode) o;
        return val == node.val &&
                Objects.equals(left, node.left) &&
                Objects.equals(right, node.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

}
