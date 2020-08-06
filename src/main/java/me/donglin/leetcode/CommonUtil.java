package me.donglin.leetcode;

import java.util.List;

/**
 * @author donglin
 * @since 2020-08-06
 */
public class CommonUtil {

    public static <T> boolean isEquals(List<T> list1, List<T> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        return list1.containsAll(list2) && list2.containsAll(list1);
    }
}
