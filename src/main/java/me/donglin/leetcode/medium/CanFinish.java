package me.donglin.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 * 提示：
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 *
 * @author donglin
 * @since 2020-08-04
 */
public class CanFinish {

    @Test
    public void case1() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        Assert.assertTrue(canFinish(numCourses, prerequisites));
    }

    @Test
    public void case2() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};
        Assert.assertFalse(canFinish(numCourses, prerequisites));
    }

    @Test
    public void case4() {
        int numCourses = 7;
        int[][] prerequisites = {{1, 2}, {5, 6}, {4, 5}, {2, 3}, {3, 4}, {6, 1}};
        Assert.assertFalse(canFinish(numCourses, prerequisites));
    }

    @Test
    public void case5() {
        int numCourses = 7;
        int[][] prerequisites = {{1, 2}, {5, 6}, {4, 5}, {2, 3}, {3, 0}};
        Assert.assertTrue(canFinish(numCourses, prerequisites));
    }

    @Test
    public void case6() {
        int numCourses = 7;
        int[][] prerequisites = {{1, 0}, {1, 2}, {0, 1}};
        Assert.assertFalse(canFinish(numCourses, prerequisites));
    }

    private boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> prerequisiteMap = new HashMap<>();
        int[] inDegrees = new int[numCourses];
        for (int[] arr : prerequisites) {
            inDegrees[arr[1]]++;
            Set<Integer> set = prerequisiteMap.computeIfAbsent(arr[0], k -> new HashSet<>());
            set.add(arr[1]);
        }
        // 队列里的课程是现在可以修完的
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }
        int finishCourseNums = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            finishCourseNums++;
            for (int i : prerequisiteMap.getOrDefault(course, Collections.emptySet())) {
                inDegrees[i]--;
                if (inDegrees[i] == 0) {
                    queue.add(i);
                }
            }
        }
        return finishCourseNums == numCourses;
    }
}
