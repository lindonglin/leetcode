package me.donglin.leetcode.simple;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * 832. 翻转图像
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 *
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 *
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 *
 * 示例 1:
 * 输入: [[1,1,0],[1,0,1],[0,0,0]]
 * 输出: [[1,0,0],[0,1,0],[1,1,1]]
 * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * 示例 2:
 * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * 说明:
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 *
 * @author donglin
 * @version 1.0
 * @since 2021/02/24
 */
public class FlipAndInvertImage {

    public int[][] flipAndInvertImage(int[][] A) {
        int m = A[0].length - 1;
        int mid = A[0].length / 2;
        boolean flag = A[0].length % 2 == 1;
        for (int[] nums : A) {
            for (int i = 0; i < mid; i++) {
                int tmp = nums[i];
                nums[i] = 1 - nums[m - i];
                nums[m - i] = 1 - tmp;
            }
            if (flag) {
                nums[mid] = 1 - nums[mid];
            }
        }
        return A;
    }

    @Test
    public void case1() {
        int[][] nums = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] expected = {{1, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        Assert.assertTrue(CommonUtil.isEquals(expected, flipAndInvertImage(nums)));

    }

    @Test
    public void case2() {
        int[][] nums = {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}};
        int[][] expected = {{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}, {1, 0, 1, 0}};
        Assert.assertTrue(CommonUtil.isEquals(expected, flipAndInvertImage(nums)));
    }

}
