package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * 46. 全排列
 * @author donglin
 * @since 2023-05-14
 */
public class Permute extends BaseTest {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<>(), visited);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    @Override
    public void case1() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> expected = asList(asList(1, 2, 3), asList(1, 3, 2), asList(2, 1, 3), asList(2, 3, 1), asList(3, 1, 2), asList(3, 2, 1));
        Assert.assertTrue(CommonUtil.isEqualListLists(expected, permute(nums)));
    }

}
