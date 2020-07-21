package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 * 示例：
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 提示：
 * 0 <= n <= 8
 *
 * @author donglin
 * @since 2020/07/21
 */
public class GenerateTrees {

    @Test
    public void case1() {
        Set<TreeNode> expected = new HashSet<>();
        expected.add(new TreeNode(1, null, new TreeNode(3, new TreeNode(2), null)));
        expected.add(new TreeNode(3, new TreeNode(2, new TreeNode(1), null), null));
        expected.add(new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), null));
        expected.add(new TreeNode(2, new TreeNode(1), new TreeNode(3)));
        expected.add(new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3))));
        assertEquals(expected, generateTrees(3));
    }

    @Test
    public void case2() {
        Set<TreeNode> expected = new HashSet<>();
        assertEquals(expected, generateTrees(0));
    }

    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return Collections.emptyList();
        }
        List<TreeNode>[][] cache = new ArrayList[n + 1][n + 1];
        return generateTrees(1, n, cache);
    }

    public List<TreeNode> generateTrees(int start, int end, List<TreeNode>[][] cache) {
        if (start > end || start <= 0 || end >= cache.length) {
            return Collections.singletonList(null);
        }
        if (cache[start][end] != null) {
            return cache[start][end];
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generateTrees(start, i - 1, cache);
            List<TreeNode> rights = generateTrees(i + 1, end, cache);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    treeNodes.add(new TreeNode(i, left, right));
                }
            }
        }
        cache[start][end] = treeNodes;
        return treeNodes;
    }

    private void assertEquals(Set<TreeNode> expected, List<TreeNode> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Set<TreeNode> actualSet = new HashSet<>(actual);
        Assert.assertTrue(expected.containsAll(actualSet));
        Assert.assertTrue(actualSet.containsAll(expected));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
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
}
