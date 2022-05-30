package me.donglin.leetcode.simple;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 1022. 从根到叶的二进制数之和
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 *
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 *
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 *
 * 示例 1：
 * 输入：root = [1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 * 示例 2：
 * 输入：root = [0]
 * 输出：0
 *
 * 提示：
 * 树中的节点数在 [1, 1000] 范围内
 * Node.val 仅为 0 或 1
 *
 * @author donglin
 * @since 2022-05-30
 */
public class SumRootToLeaf {

    public int sumRootToLeaf(TreeNode root) {
        return sumRootToLeaf(root, 0);
    }

    public int sumRootToLeaf(TreeNode node, int sum) {
        sum = (sum << 1) + node.val;
        if (node.left == null && node.right == null) {
            return sum;
        }
        int leftSum = 0, rightSum = 0;
        if (node.left != null) {
            leftSum = sumRootToLeaf(node.left, sum);
        }
        if (node.right != null) {
            rightSum = sumRootToLeaf(node.right, sum);
        }
        return leftSum + rightSum;
    }

    @Test
    public void case1() {
        Assert.assertEquals(22, sumRootToLeaf(TreeNode.from(1, 0, 1, 0, 1, 0, 1)));
    }

    @Test
    public void case2() {
        Assert.assertEquals(0, sumRootToLeaf(TreeNode.from(0)));
    }

}
