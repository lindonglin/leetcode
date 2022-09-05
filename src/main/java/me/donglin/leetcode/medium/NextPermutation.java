package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;


/**
 * 31. 下一个排列
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 *
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
 * 更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
 * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 * @author donglin
 * @since 2022-09-05
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            // 从后往前遍历
            // 显然，当遍历到i时，(i+1, i+2, ... n-1) 必然是降序排序
            // 因此，若nums[i+1] < nums[i]，那[i,n-1]已经是最大排列了，需要再往前推
            if (nums[i] >= nums[i+1]) {
                continue;
            }
            // 找出nums[i+1, n-1]中比num[i]大的最小值，显然从后往前遍历的第一个就是
            int index = -1;
            for (int k = n - 1; k > i; k--) {
                if (nums[k] > nums[i]) {
                    index = k;
                    break;
                }
            }
            if (index != -1) {
                swap(nums, i, index);
                for (int l = i + 1, r = n - 1; l < r; l++, r--) {
                    swap(nums, l, r);
                }
                return;
            }
        }
        for (int l = 0, r = n - 1; l < r; l++, r--) {
            swap(nums, l, r);
        }
    }

    private void swap(int[] nums, int i, int k) {
        int tmp = nums[i];
        nums[i] = nums[k];
        nums[k] = tmp;
    }

    @Test
    public void case1() {
        int[] nums = {1, 2, 3};
        int[] expected = {1, 3, 2};
        nextPermutation(nums);
        Assert.assertArrayEquals(expected, nums);
    }

    @Test
    public void case2() {
        int[] nums = {3, 2, 1};
        int[] expected = {1, 2, 3};
        nextPermutation(nums);
        Assert.assertArrayEquals(expected, nums);
    }

    @Test
    public void case3() {
        int[] nums = {1, 1, 5};
        int[] expected = {1, 5, 1};
        nextPermutation(nums);
        Assert.assertArrayEquals(expected, nums);
    }

    @Test
    public void case4() {
        int[] nums = {3, 1, 2, 5, 4};
        int[] expected = {3, 1, 4, 2, 5};
        nextPermutation(nums);
        Assert.assertArrayEquals(expected, nums);
    }
}
