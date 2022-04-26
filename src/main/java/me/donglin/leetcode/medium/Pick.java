package me.donglin.leetcode.medium;

import java.util.*;

/**
 * 398 随机数索引
 * @author donglin
 * @since 2022-04-25
 */
public class Pick {

    private final Map<Integer, List<Integer>> map;
    private final Random random = new Random();
    public Pick(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(random.nextInt(list.size()));
    }

}
