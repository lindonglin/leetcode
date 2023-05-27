package me.donglin.leetcode.hard;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;

import java.util.Map;
import java.util.TreeMap;

/**
 * 715. Range 模块
 * Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
 *
 * 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。
 *
 * 实现 RangeModule 类:
 *
 * RangeModule() 初始化数据结构的对象。
 * void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
 * boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true ，否则返回 false 。
 * void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
 *
 *
 * 示例 1：
 * 输入
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * 输出
 * [null, null, null, true, false, true]
 *
 * 解释
 * RangeModule rangeModule = new RangeModule();
 * rangeModule.addRange(10, 20);
 * rangeModule.removeRange(14, 16);
 * rangeModule.queryRange(10, 14); 返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
 * rangeModule.queryRange(13, 15); 返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
 * rangeModule.queryRange(16, 17); 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
 *
 *
 * 提示：
 * 1 <= left < right <= 10^9
 * 在单个测试用例中，对 addRange 、  queryRange 和 removeRange 的调用总数不超过 10^4 次
 *
 * @author donglin
 * @since 2022-06-20
 */
public class RangeModule extends BaseTest {

    private final TreeMap<Integer, Integer> intervals = new TreeMap<>();
    public RangeModule() {}

    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        Map.Entry<Integer, Integer> pre = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
        if (pre != null && pre.getValue() >= right) {
            // 显然 pre已经包含了 [left,right)了
            return;
        }
        if (pre != null && pre.getValue() >= left) {
            // 显然 pre.left < left < pre.right < right => 其并集为 [pre.left, right)
            left = pre.getKey();
            intervals.remove(pre.getKey());
        }
        while (entry != null && entry.getKey() <= right) {
            // 显然 left < entry.left < right/entry.right
            // 所以其并集为 [left, max(right/entry.right))
            right = Math.max(right, entry.getValue());
            intervals.remove(entry.getKey());
            entry = intervals.higherEntry(entry.getKey());
        }
        intervals.put(left, right);
    }


    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        Map.Entry<Integer, Integer> pre = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
        // pre是否已经包含了 [left,right)了
        return pre != null && pre.getValue() >= right;
    }

    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        Map.Entry<Integer, Integer> pre = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
        if (pre != null && pre.getValue() >= right) {
            // 显然 pre包含了 [left,right)了 删除后变成  [pre.left, left) [right, pre.right);
            intervals.put(pre.getKey(), left);
            intervals.put(right, pre.getValue());
            return;
        }
        if (pre != null && pre.getValue() >= left) {
            // 显然 pre.left < left < pre.right < right 删除后变成  [pre.left, left)
            intervals.put(pre.getKey(), left);
        }
        while (entry != null && entry.getKey() <= right) {
            // 显然 left < entry.left < right/entry.right
            // 所以其并集为 [left, max(right/entry.right))
            if (right >= entry.getValue()) {
                // 显然 left < entry.left < entry.right <= right, 需要删除整个entry
                intervals.remove(entry.getKey());
            } else {
                // 显然 left < entry.left < right <= entry.right, 需要删除整个[entry.left, right)
                intervals.remove(entry.getKey());
                intervals.put(right, entry.getValue());
            }
            entry = intervals.higherEntry(entry.getKey());
        }
    }

    @Override
    public void case1() {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(10,20);
        rangeModule.removeRange(14, 16);
        Assert.assertTrue(rangeModule.queryRange(10, 14));
        Assert.assertFalse(rangeModule.queryRange(13, 15));
        Assert.assertTrue(rangeModule.queryRange(16, 17));
    }

}
