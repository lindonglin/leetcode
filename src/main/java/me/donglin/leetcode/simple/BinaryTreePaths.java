package me.donglin.leetcode.simple;

import me.donglin.leetcode.CommonUtil;
import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * @author donglin
 * @since 2020-09-04
 */
public class BinaryTreePaths {

    @Test
    public void case1() {
        TreeNode root = TreeNode.fullOrder(1, 2, 3, null, 5);
        List<String> expected = Arrays.asList("1->2->5", "1->3");
        Assert.assertTrue(CommonUtil.isEquals(expected, binaryTreePaths(root)));
    }

    @Test
    public void case2() {
        TreeNode root = TreeNode.fullOrder(1, 2, 3);
        List<String> expected = Arrays.asList("1->2", "1->3");
        Assert.assertTrue(CommonUtil.isEquals(expected, binaryTreePaths(root)));
    }

    @Test
    public void case3() {
        TreeNode root = TreeNode.fullOrder(1, 2, null, 3, null, null, null, 4);
        List<String> expected = Collections.singletonList("1->2->3->4");
        Assert.assertTrue(CommonUtil.isEquals(expected, binaryTreePaths(root)));
    }

    @Test
    public void case4() {
        TreeNode root = TreeNode.fullOrder(1);
        List<String> expected = Collections.singletonList("1");
        Assert.assertTrue(CommonUtil.isEquals(expected, binaryTreePaths(root)));
    }

    @Test
    public void case5() {
        TreeNode root = TreeNode.from(37, -34, -48, null, -100, -100, 48, null, null, null, null, -54, null, -71, -22, null, null, null, 8);
        List<String> expected = Arrays.asList("37->-34->-100", "37->-48->-100", "37->-48->48->-54->-71", "37->-48->48->-54->-22->8");
        Assert.assertTrue(CommonUtil.isEquals(expected, binaryTreePaths(root)));
    }
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        binaryTreePaths(root, result, builder);
        return result;
    }

    public void binaryTreePaths(TreeNode node, List<String> result, StringBuilder builder) {
        if (node == null) {
            return;
        }
        builder.append("->").append(node.val);
        if (node.left == null && node.right == null) {
            result.add(builder.substring(2));;
        } else {
            binaryTreePaths(node.left, result, builder);
            binaryTreePaths(node.right, result, builder);
        }
        builder.delete(builder.lastIndexOf("->"), builder.length());
    }
}
