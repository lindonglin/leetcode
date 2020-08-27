package me.donglin.leetcode.medium;

import me.donglin.leetcode.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 332. 重新安排行程
 *
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，
 * 对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 *
 * 说明:
 * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 *
 * 示例 1:
 * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * 示例 2:
 * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 *
 * @author donglin
 * @since 2020-08-27
 */
public class FindItinerary {

    @Test
    public void case1() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("MUC", "LHR"), Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO")
        );
        List<String> expected = Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC");
        Assert.assertTrue(CommonUtil.isEquals(expected, findItinerary(tickets)));
    }

    @Test
    public void case2() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "SFO"), Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"), Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO")
        );
        List<String> expected = Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO");
        Assert.assertTrue(CommonUtil.isEquals(expected, findItinerary(tickets)));
    }

    @Test
    public void case3() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "KUL"), Arrays.asList("JFK", "NRT"), Arrays.asList("NRT", "JFK")
        );
        List<String> expected = Arrays.asList("JFK", "NRT", "JFK", "KUL");
        Assert.assertTrue(CommonUtil.isEquals(expected, findItinerary(tickets)));
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> pairs = new HashMap<>();
        for (List<String> pair : tickets) {
            if (!pairs.containsKey(pair.get(0))) {
                pairs.put(pair.get(0), new ArrayList<>());
            }
            pairs.get(pair.get(0)).add(pair.get(1));
        }
        for (List<String> destinations : pairs.values()) {
            destinations.sort(Comparator.comparing(String::toString));
        }
        // 显然，最终结果里会有 tickets.size() + 1 个元素
        int successFlag = tickets.size() + 1;
        String departure = "JFK";
        List<String> result = new ArrayList<>();
        result.add(departure);
        if (findItinerary(pairs, departure, result, successFlag)) {
            return result;
        }
        return Collections.emptyList();
    }

    public boolean findItinerary(Map<String, List<String>> pairs, String departure, List<String> result, int successFlag) {
        if (result.size() == successFlag) {
            return true;
        }
        List<String> destinations = pairs.get(departure);
        if (destinations == null || destinations.isEmpty()) {
            // 没有目的地了，说明走不通了
            return false;
        }
        for (int i = 0, size = destinations.size(); i < size; i++) {
            String dest = destinations.get(i);
            result.add(dest);
            destinations.remove(i);
            if (findItinerary(pairs, dest, result, successFlag)) {
                return true;
            }
            destinations.add(i, dest);
            result.remove(result.size() - 1);
        }
        return false;
    }
}
