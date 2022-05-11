package me.donglin.leetcode.medium;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 449. 序列化和反序列化二叉搜索树
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 *
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 *
 * 编码的字符串应尽可能紧凑。
 *
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：[2,1,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点数范围是 [0, 10^4]
 * 0 <= Node.val <= 10^4
 * 题目数据 保证 输入的树是一棵二叉搜索树。
 *
 * @author donglin
 * @since 2022-05-11
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder builder = new StringBuilder();
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node == null) {
                builder.append(',');
            } else {
                builder.append(node.val).append(',');
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return builder.toString().trim();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] nums = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nums[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        TreeNode node, left, right;
        while (i < nums.length && !queue.isEmpty()) {
            if (!nums[i].isEmpty()) {
                left = new TreeNode(Integer.parseInt(nums[i]));
                queue.offer(left);
            } else {
                left = null;
            }
            i++;
            if (i < nums.length && !nums[i].isEmpty()) {
                right = new TreeNode(Integer.parseInt(nums[i]));
                queue.offer(right);
            } else {
                right = null;
            }
            node = queue.poll();
            //noinspection ConstantConditions
            node.left = left;
            node.right = right;
            i++;
        }
        return root;
    }


    @Test
    public void case1() {
        TreeNode root = TreeNode.from(2, 1, 3);
        Assert.assertEquals(root, deserialize(serialize(root)));
    }

    @Test
    public void case2() {
        TreeNode root = TreeNode.from();
        Assert.assertEquals(root, deserialize(serialize(root)));
    }

    @Test
    public void case3() {
        TreeNode root = TreeNode.from(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1);
        Assert.assertEquals(root, deserialize(serialize(root)));
    }

}
