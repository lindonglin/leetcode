package me.donglin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 *
 * 注意: 允许出现重复元素。
 *
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * 示例:
 *
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 *
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 *
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 *
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 *
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 *
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 *
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 *
 * @author donglin
 * @since 2020-10-31
 */
public class RandomizedCollection {

    private final List<Integer> nums = new ArrayList<>();
    private final Map<Integer, Node> indexMap = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {

    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        Node node = new Node(nums.size());
        node.next = indexMap.get(val);
        indexMap.put(val, node);
        nums.add(val);
        return node.next == null;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        Node node = indexMap.get(val);
        if (node == null) {
            return false;
        }
        int maxIndex = nums.size() - 1;
        int lastNum = nums.get(maxIndex);

        // 如果要删除的数不等于最后一个数的话将最后一个数赋值到要删除的这个数上，这样就相当于这个数被删了，然后删除最后一个数
        Node p = indexMap.get(lastNum);
        // 更新移动后的下标
        // 虽然有循环，但是用摊还分析平均时间复杂度是O(1)
        while (p.index != maxIndex) {
            p = p.next;
        }
        p.index = node.index;
        nums.set(node.index, lastNum);
        nums.remove(maxIndex);

        //更新被删除的数的下标
        indexMap.put(val, node.next);
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return nums.get(new Random().nextInt(nums.size()));
    }

    private static class Node {
        private int index;
        private Node next;

        public Node(int index) {
            this.index = index;
        }
    }

    @Test
    public void case1() {
        RandomizedCollection collection = new RandomizedCollection();
        Assert.assertFalse(collection.remove(1));
        Assert.assertTrue(collection.insert(1));
        Assert.assertFalse(collection.insert(1));
        Assert.assertEquals(1, collection.getRandom());
        collection.insert(2);
        Assert.assertTrue(collection.remove(1));
        Assert.assertTrue(collection.remove(1));
        Assert.assertEquals(2, collection.getRandom());
    }

    @Test
    public void case2() {
        RandomizedCollection collection = new RandomizedCollection();
        Assert.assertTrue(collection.insert(1));
        Assert.assertFalse(collection.insert(1));
        Assert.assertTrue(collection.remove(1));
        Assert.assertEquals(1, collection.getRandom());
    }

    @Test
    public void case3() {
        RandomizedCollection collection = new RandomizedCollection();
        Assert.assertTrue(collection.insert(0));
        Assert.assertTrue(collection.insert(1));
        Assert.assertTrue(collection.remove(0));
        Assert.assertTrue(collection.insert(2));
        Assert.assertTrue(collection.remove(1));
    }

    @Test
    public void case4() {
        RandomizedCollection collection = new RandomizedCollection();
        Assert.assertTrue(collection.insert(4));
        Assert.assertTrue(collection.insert(3));
        Assert.assertFalse(collection.insert(4));
        Assert.assertTrue(collection.insert(2));
        Assert.assertFalse(collection.insert(4));
        Assert.assertTrue(collection.remove(4));
        Assert.assertTrue(collection.remove(3));
        Assert.assertTrue(collection.remove(4));
        Assert.assertTrue(collection.remove(4));
    }
}
