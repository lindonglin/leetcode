package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 135. 分发糖果
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *
 * 示例 2:
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 * @author donglin
 * @since 2020-12-24
 */
public class Candy {

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1);
        for (int i = 1, size = ratings.length; i < size; i++) {
            if (ratings[i] > ratings[i - 1] && candy[i] <= candy[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i] && candy[i - 1] <= candy[i]) {
                candy[i - 1] = candy[i] + 1;
            }
        }
        int result = 0;
        for (int v : candy) {
            result += v;
        }
        return result;
    }

    @Test
    public void case1() {
        int[] ratings = {1, 0, 2};
        Assert.assertEquals(5, candy(ratings));
    }

    @Test
    public void case2() {
        int[] ratings = {1, 2, 2};
        Assert.assertEquals(4, candy(ratings));
    }

    @Test
    public void case3() {
        int[] ratings = {1, 2, 3, 4, 5};
        Assert.assertEquals(15, candy(ratings));
    }


    @Test
    public void case4() {
        int[] ratings = {5, 4, 3, 2, 1};
        Assert.assertEquals(15, candy(ratings));
    }


    @Test
    public void case5() {
        int[] ratings = {1, 0, 1, 0, 1, 0, 1};
        Assert.assertEquals(11, candy(ratings));
    }

    @Test
    public void case7() {
        int[] ratings = {1, 2, 3, 4, 4, 5};
        Assert.assertEquals(13, candy(ratings));
    }

    @Test
    public void case8() {
        int[] ratings = {1, 6, 10, 8, 7, 3, 2};
        Assert.assertEquals(18, candy(ratings));
    }
}
