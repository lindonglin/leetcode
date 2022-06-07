package me.donglin.leetcode.hard;

import org.junit.Assert;

import java.util.*;

/**
 * @author fuxin
 * @since 2022-06-07
 */
public class GetMaxNumber {

    public static int getMaxNumber(int n, int[] A) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(A);
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        Set<Integer> numberSet = new HashSet<>();
        for (int a : A) {
            numberSet.add(a);
        }
        int len = list.size();
        boolean equal = true;
        boolean findLess = false;
        List<Integer> result = new ArrayList<>();
        for (int i = len - 1; i >= 0 && i < len; ) {
            int num = list.get(i);
            if (equal) {
                if (findLess) {
                    int maxLessNum = findMaxLessNum(A, num);
                    if (maxLessNum == -1) { // 没找到，需要回退
                        if (result.isEmpty()) {
                            if (list.size() == 1) {
                                return -1;
                            } else {
                                result.add(0);
                                equal = false;
                                i--;
                            }
                        } else {
                            result.remove(result.size() - 1);
                            i++;
                        }
                    } else {
                        result.add(maxLessNum);
                        equal = false;
                        i--;
                    }
                } else if (numberSet.contains(num)) {
                    result.add(num);
                    i--;
                } else {
                    int maxLessNum = findMaxLessNum(A, num);
                    if (maxLessNum == -1) { // 没找到，需要回退
                        if (result.isEmpty()) {
                            if (list.size() == 1) {
                                return -1;
                            } else {
                                result.add(0);
                                equal = false;
                                i--;
                            }
                        } else {
                            result.remove(result.size() - 1);
                            i++;
                            findLess = true;
                        }
                    } else {
                        result.add(maxLessNum);
                        equal = false;
                        i--;
                    }
                }
            } else {
                result.add(A[A.length - 1]);
                i--;
            }
        }
        if (result.isEmpty())
            return -1;
        int ans = 0;
        for (Integer num : result) {
            ans = ans * 10 + num;
        }
        return ans;
    }

    private static int findMaxLessNum(int[] A, int num) {
        int r = -1;
        for (int a : A) {
            if (a >= num) {
                break;
            }
            r = a;
        }
        return r;
    }

    public static void main(String[] args) {
        Assert.assertEquals(22299, getMaxNumber(22313, new int[]{2, 4, 9}));
        Assert.assertEquals(22999, getMaxNumber(23313, new int[]{2, 4, 9}));
        Assert.assertEquals(9999, getMaxNumber(11111, new int[]{2, 4, 9}));
        Assert.assertEquals(-1, getMaxNumber(1, new int[]{2}));
        Assert.assertEquals(-1, getMaxNumber(2, new int[]{2}));
    }

}
