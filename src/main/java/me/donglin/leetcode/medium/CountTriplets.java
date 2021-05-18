package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 1442. 形成两个异或相等数组的三元组数目
 * 给你一个整数数组 arr 。
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * <p>
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * <p>
 * 示例 1：
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 * <p>
 * 示例 2：
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 * <p>
 * 示例 3：
 * 输入：arr = [2,3]
 * 输出：0
 * <p>
 * 示例 4：
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 * <p>
 * 示例 5：
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 * <p>
 * 提示：
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 *
 * @author donglin
 * @version 1.0
 * @since 2021/05/18
 */
public class CountTriplets {

    public int countTriplets1(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] ^ arr[i - 1];
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                // (dp[j] ^ dp[i]) == (dp[k + 1] ^ dp[j])
                // => dp[i] == dp[k + 1]
                if (dp[i] == dp[k + 1]) {
                    count += (k - i);
                }
            }
        }
        return count;
    }

    public int countTriplets2(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> countMap = new HashMap<>(arr.length);
        Map<Integer, Integer> totalMap = new HashMap<>(arr.length);
        int count = 0, xor = 0;
        for (int k = 0; k < n; k++) {
            int t = xor ^ arr[k];
            if (countMap.containsKey(t)) {
                count += k * countMap.get(t) - totalMap.get(t);
            }
            countMap.put(xor, countMap.getOrDefault(xor, 0) + 1);
            totalMap.put(xor, totalMap.getOrDefault(xor, 0) + k);
            xor = t;
        }
        return count;
    }

    @Test
    public void case1() {
        int[] arr = {2, 3, 1, 6, 7};
        Assert.assertEquals(4, countTriplets1(arr));
        Assert.assertEquals(4, countTriplets2(arr));
    }


    @Test
    public void case2() {
        int[] arr = {1, 1, 1, 1, 1};
        Assert.assertEquals(10, countTriplets1(arr));
        Assert.assertEquals(10, countTriplets2(arr));
    }

    @Test
    public void case3() {
        int[] arr = {2, 3};
        Assert.assertEquals(0, countTriplets1(arr));
        Assert.assertEquals(0, countTriplets2(arr));
    }

    @Test
    public void case4() {
        int[] arr = {1, 3, 5, 7, 9};
        Assert.assertEquals(3, countTriplets1(arr));
        Assert.assertEquals(3, countTriplets2(arr));
    }

    @Test
    public void case5() {
        int[] arr = {7, 11, 12, 9, 5, 2, 7, 17, 22};
        Assert.assertEquals(8, countTriplets1(arr));
        Assert.assertEquals(8, countTriplets2(arr));
    }
}
