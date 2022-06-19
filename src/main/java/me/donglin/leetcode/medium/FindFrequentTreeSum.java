package me.donglin.leetcode.medium;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 508. 出现次数最多的子树元素和
 * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 *
 * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 *
 * 示例 1：
 * 输入: root = [5,2,-3]
 * 输出: [2,-3,4]
 *
 * 示例 2：
 * 输入: root = [5,2,-5]
 * 输出: [2]
 *
 * 提示:
 * 节点数在 [1, 10^4] 范围内
 * -10^5 <= Node.val <= 10^5
 *
 * @author donglin
 * @since 2022-06-19
 */
public class FindFrequentTreeSum {

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        findFrequentTreeSum(root, map);
        int max = 0, i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                i = 1;
            } else if (entry.getValue() == max) {
                i++;
            }
        }
        int[] res = new int[i];
        i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                res[i++] = entry.getKey();
            }
        }
        return res;
    }

    public int findFrequentTreeSum(TreeNode node,  Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }
        int sum = node.val + findFrequentTreeSum(node.left, map) + findFrequentTreeSum(node.right, map);
        int count = map.getOrDefault(sum, 0) + 1;
        map.put(sum, count);
        return sum;
    }

    @Test
    public void case1() {
        TreeNode root = TreeNode.from(5, 2, -3);
        int[] expect = {2, -3, 4};
        Assert.assertArrayEquals(expect, findFrequentTreeSum(root));
    }

    @Test
    public void case2() {
        TreeNode root = TreeNode.from(5, 2, -5);
        int[] expect = {2};
        Assert.assertArrayEquals(expect, findFrequentTreeSum(root));
    }

}
