package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1705 吃苹果的最大数目
 *
 * @author donglin
 * @since 2021-12-24
 */
public class EatenApples {

    public int eatenApples(int[] apples, int[] days) {
        int count = 0;
        int n = apples.length;
        int i = 0;
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        while (i < n || !queue.isEmpty()) {
            if (i < n && apples[i] != 0) {
                queue.offer(new int[]{apples[i], days[i] + i});
            }
            i++;
            int[] arr;
            while ((arr = queue.peek()) != null && (arr[0] == 0 || arr[1] < i)) {
                queue.remove();
            }
            if (arr == null) {
                continue;
            }
            arr[0]--;
            count++;
        }
        return count;
    }

    @Test
    public void case1() {
        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};
        Assert.assertEquals(7, eatenApples(apples, days));
    }

    @Test
    public void case2() {
        int[] apples = {3, 0, 0, 0, 0, 2};
        int[] days = {3, 0, 0, 0, 0, 2};
        Assert.assertEquals(5, eatenApples(apples, days));
    }

    @Test
    public void case3() {
        int[] apples = {2, 1, 10};
        int[] days = {2, 10, 1};
        Assert.assertEquals(4, eatenApples(apples, days));
    }

    @Test
    public void case4() {
        int[] apples = {3, 1, 1, 0, 0, 2};
        int[] days = {3, 1, 1, 0, 0, 2};
        Assert.assertEquals(5, eatenApples(apples, days));
    }

}
