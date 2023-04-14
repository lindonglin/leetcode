package me.donglin.leetcode.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.donglin.leetcode.util.LoopQueue.Element;

/**
 * 这不是线程安全的，但是同个队列同一时刻最多只有一个线程会有写操作，因此没事
 *
 * @author lindonglin
 * @since 2023/04/14
 */
@Slf4j
public class LoopQueue<T extends Element> {

    private static final int DEFAULT_CAPACITY = 102400;
    private static final int DEFAULT_REDUCE_BY = 8;

    private final String name;
    private final int capacity;
    private final int reduceBy;
    private final Object[] data;

    private int headIndex = 0;
    private int position = -1;
    private int size = 0;

    public LoopQueue(String name) {
        this(name, DEFAULT_CAPACITY, DEFAULT_REDUCE_BY);
    }

    public LoopQueue(String name, int capacity) {
        this(name, capacity, DEFAULT_REDUCE_BY);
    }
    public LoopQueue(String name, int capacity, int reduceBy) {
        this.name = name;
        this.capacity = capacity;
        this.reduceBy = reduceBy;
        this.data = new Object[capacity];
    }

    public String name() {
        return name;
    }

    public int size() {
        return size;
    }

    public void add(T t) {
        if (size >= capacity) {
            reduce();
        }
        position = (position + 1) % capacity;
        data[position] = t;
        size++;
    }

    public int countAfter(long timestamp) {
        if (size == 0) {
            return 0;
        }
        int firstIndex = getFirstIndexGreaterThan(timestamp);
        if (firstIndex < 0) {
            return 0;
        }
        return firstIndex <= position ? position - firstIndex + 1
            : position + capacity - firstIndex + 1;
    }

    public List<T> after(long timestamp) {
        int count = countAfter(timestamp);
        if (count == 0) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(count);
        for (int i = position, c = 0; c < count; c++) {
            list.add(get(i--));
        }
        return list;
    }

    /**
     * 获取当前元素中第一个时间戳大于timestamp的下标
     */
    private int getFirstIndexGreaterThan(long timestamp) {
        if ((get(headIndex).getTimestamp() >= timestamp)) {
            return headIndex;
        }
        if (get(position).getTimestamp() <= timestamp) {
            return -1;
        }
        int left = headIndex;
        int right = position > headIndex ? position : position + capacity;
        while (left < right) {
            int mid = (left + right) / 2;
            long value = get(mid).getTimestamp();
            if (value <= timestamp) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right >= capacity ? right - capacity : right;
    }

    /**
     * 队列满了后舍弃前 1/reduceBy 的数据
     */
    private void reduce() {
        if (log.isDebugEnabled()) {
            log.info("{} start reduce head timestamp is {}", name, get(headIndex).getTimestamp());
        }
        int reduce = capacity / reduceBy;
        if (reduce > size) {
            reduce = size;
        }
        if (reduce < 1) {
            reduce = 1;
        }
        size -= reduce;
        headIndex = (headIndex + reduce) % capacity;
        if (log.isDebugEnabled()) {
            log.info("{} finished reduce head timestamp is {}", name, get(headIndex).getTimestamp());
        }
    }

    private T get(int i) {
        if (i < 0) {
            i += capacity;
        } else if (i >= capacity) {
            i -= capacity;
        }
        //noinspection unchecked
        return (T) data[i];
    }

    public interface Element {
        long getTimestamp();
    }

}
