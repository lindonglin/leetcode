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


    public static <T> boolean isEqualListLists(List<List<T>> list1, List<List<T>> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!isEquals(list1.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }
    public static  boolean isEquals(char[][] chars1, char[][] chars2) {
        if (chars1.length != chars2.length) {
            return false;
        }
        for (int i = 0, size = chars1.length; i < size; i++) {
            if (!isEquals(chars1[i], chars2[i])) {
                return false;
            }
        }
        return true;
    }

    public static  boolean isEquals(char[] chars1, char[] chars2) {
        if (chars1.length != chars2.length) {
            return false;
        }
        for (int i = 0, size = chars1.length; i < size; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }
}
