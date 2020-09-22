package me.donglin.leetcode.hard;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 968. 监控二叉树
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-cameras/
 *
 * @author donglin
 * @since 2020-09-22
 */
public class MinCameraCover {

    public int minCameraCover(TreeNode root) {
        int[] array = dfs(root);
        return array[1];
    }

    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] leftArray = dfs(root.left);
        int[] rightArray = dfs(root.right);
        int[] array = new int[3];
        array[0] = leftArray[2] + rightArray[2] + 1;
        array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], rightArray[0] + leftArray[1]));
        array[2] = Math.min(array[0], leftArray[1] + rightArray[1]);
        return array;
    }
//
//
//    public int minCameraCover(TreeNode root) {
//        Map<String, Integer> map = new HashMap<>();
//        return minCameraCover(root, false, false, map);
//    }
//
//    public int minCameraCover(TreeNode node, boolean monitor, boolean must, Map<String, Integer> map) {
//        if (node == null) {
//            return 0;
//        }
//        String key = String.valueOf(node.hashCode()) + monitor + must;
//        Integer result = map.get(key);
//        if (result != null) {
//            return result;
//        }
//        int num1 = 1 + minCameraCover(node.left, true, false, map) + minCameraCover(node.right, true, false, map);
//        if (must) {
//            result = num1;
//        } else if (monitor) {
//            result = Math.min(num1, minCameraCover(node.left, false, false, map) + minCameraCover(node.right, false, false, map));
//        } else {
//            int num2 = node.left == null ? Integer.MAX_VALUE :
//                    minCameraCover(node.left, false, true, map) + minCameraCover(node.right, false, false, map);
//            int num3 = node.right == null ? Integer.MAX_VALUE :
//                    minCameraCover(node.left, false, false, map) + minCameraCover(node.right, false, true, map);
//            result = Math.min(num1, Math.min(num2, num3));
//        }
//        map.put(key, result);
//        return result;
//    }

    @Test
    public void case1() {
        TreeNode root = TreeNode.from(0, 0, null, 0, 0);
        Assert.assertEquals(1, minCameraCover(root));
    }

    @Test
    public void case2() {
        TreeNode root = TreeNode.from(0, 0, null, 0, null, 0, null, null, 0);
        Assert.assertEquals(2, minCameraCover(root));
    }

    @Test
    public void case3() {
        TreeNode root = TreeNode.from(0, null, 0, null, 0, null, 0, null, 0, 0, 0, null, null, 0, 0);
        Assert.assertEquals(3, minCameraCover(root));
    }
}
