package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 1305. 两棵二叉搜索树中的所有元素
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 *
 * 示例 1：
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 *
 * 示例 2：
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 *
 * 提示：
 * 每棵树的节点数在 [0, 5000] 范围内
 * -10^5 <= Node.val <= 10^5
 *
 * @author donglin
 * @since 2022-05-01
 */
public class GetAllElements {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> nums1 = new ArrayList<>();
        List<Integer> nums2 = new ArrayList<>();
        inorder(root1, nums1);
        inorder(root2, nums2);
        return merge(nums1, nums2);
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    private List<Integer> merge(List<Integer> nums1, List<Integer> nums2) {
        int total = nums1.size() + nums2.size();
        List<Integer> result = new ArrayList<>(total);
        nums1.add(Integer.MAX_VALUE);
        nums2.add(Integer.MAX_VALUE);
        int i = 0, j = 0;
        while (result.size() < total) {
            if (nums1.get(i) <= nums2.get(j)) {
                result.add(nums1.get(i));
                i++;
            } else {
                result.add(nums2.get(j));
                j++;
            }
        }
        return result;
    }

    @Test
    public void case1() {
        List<Integer> expect = Arrays.asList(0, 1, 1, 2, 3, 4);
        TreeNode root1 = TreeNode.from(2, 1, 4);
        TreeNode root2 = TreeNode.from(1, 0, 3);
        Assert.assertTrue(CommonUtil.isEquals(expect, getAllElements(root1, root2)));
    }

    @Test
    public void case2() {
        List<Integer> expect = Arrays.asList(1, 1, 8, 8);
        TreeNode root1 = TreeNode.from(1, null, 8);
        TreeNode root2 = TreeNode.from(8, 1, null);
        Assert.assertTrue(CommonUtil.isEquals(expect, getAllElements(root1, root2)));
    }

}
