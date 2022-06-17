package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1089. 复写零
 * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 * 注意：请不要在超过该数组长度的位置写入元素。
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 *
 *
 * 示例 1：
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 *
 * 示例 2：
 * 输入：[1,2,3]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 *
 * 提示：
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 *
 * @author donglin
 * @since 2022-06-17
 */
public class DuplicateZeros {

    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int i = 0, count = 0;
        while (count < n) {
            if (arr[i++] == 0) {
                count += 2;
            } else {
                count++;
            }
        }
        int index = n - 1;
        i--;
        if (count == n + 1) {
            // 说明最后一个是0且不用复制
            arr[index--] = arr[i--];
        }
        for (; i >= 0; i--) {
            arr[index--] = arr[i];
            if (arr[i] == 0) {
                arr[index--] = 0;
            }
        }
    }

    @Test
    public void case1() {
        int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        int[] expected = {1, 0, 0, 2, 3, 0, 0, 4};
        duplicateZeros(arr);
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void case2() {
        int[] arr = {1, 2, 3};
        int[] expected = {1, 2, 3};
        duplicateZeros(arr);
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void case3() {
        int[] arr = {1, 2, 0, 0, 1, 0, 3, 1};
        int[] expected = {1, 2, 0, 0, 0, 0, 1, 0};
        duplicateZeros(arr);
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void case4() {
        int[] arr = {1, 5, 2, 0, 6, 8, 0, 6, 0};
        int[] expected = {1, 5, 2, 0, 0, 6, 8, 0, 0};
        duplicateZeros(arr);
        Assert.assertArrayEquals(expected, arr);
    }

}
