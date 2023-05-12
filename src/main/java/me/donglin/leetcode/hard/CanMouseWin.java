package me.donglin.leetcode.hard;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 1728. 猫和老鼠 II
 * 一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。
 *
 * 它们所处的环境设定是一个 rows x cols 的方格 grid ，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。
 *
 * 玩家由字符 'C' （代表猫）和 'M' （代表老鼠）表示。
 * 地板由字符 '.' 表示，玩家可以通过这个格子。
 * 墙用字符 '#' 表示，玩家不能通过这个格子。
 * 食物用字符 'F' 表示，玩家可以通过这个格子。
 * 字符 'C' ， 'M' 和 'F' 在 grid 中都只会出现一次。
 * 猫和老鼠按照如下规则移动：
 *
 * 老鼠 先移动 ，然后两名玩家轮流移动。
 * 每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出 grid 。
 * catJump 和 mouseJump 是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。
 * 它们可以停留在原地。
 * 老鼠可以跳跃过猫的位置。
 * 游戏有 4 种方式会结束：
 *
 * 如果猫跟老鼠处在相同的位置，那么猫获胜。
 * 如果猫先到达食物，那么猫获胜。
 * 如果老鼠先到达食物，那么老鼠获胜。
 * 如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。
 * 给你 rows x cols 的矩阵 grid 和两个整数 catJump 和 mouseJump ，双方都采取最优策略，如果老鼠获胜，那么请你返回 true ，否则返回 false 。
 *
 * 提示：
 * rows == grid.length
 * cols = grid[i].length
 * 1 <= rows, cols <= 8
 * grid[i][j] 只包含字符 'C' ，'M' ，'F' ，'.' 和 '#' 。
 * grid 中只包含一个 'C' ，'M' 和 'F' 。
 * 1 <= catJump, mouseJump <= 8
 *
 * @author donglin
 * @since 2022-05-10
 */
public class CanMouseWin extends BaseTest {

    private static final int MOUSE_TURN = 0, CAT_TURN = 1;
    private static final int UNKNOWN = 0, MOUSE_WIN = 1, CAT_WIN = 2;
    private static final int MAX_MOVES = 1000;
    /**
     * 上下左右四个方向
     */
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    /**
     * degrees[mouse][cat][turn]  表示每个状态的度 即猫在mouse,老鼠在cat,轮到turn跳时,还有多少种方向可以选择
     * 比如可以停留原地、向上跳1格、向右跳1和2格，则为4
     */
    private int[][][] degrees;
    /**
     * 前三维下标含义与 三维数组 degrees的一样
     * 第四维0表示该状态结果，1表示到达该状态需要经过多少轮游戏
     */
    private int[][][][] results;
    private int rows, cols;
    private char[][] graph;
    private int catJump;
    private int mouseJump;
    private int food;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        this.rows = grid.length;
        this.cols = grid[0].length();
        this.graph = new char[rows][cols];
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        int startMouse = -1, startCat = -1;
        for (int i = 0; i < rows; i++) {
            graph[i] = grid[i].toCharArray();
            for (int j = 0; j < cols; j++) {
                char c = grid[i].charAt(j);
                if (c == 'M') {
                    startMouse = getPos(i, j);
                } else if (c == 'C') {
                    startCat = getPos(i, j);
                } else if (c == 'F') {
                    food = getPos(i, j);
                }
            }
        }
        Queue<int[]> queue = initData();
        // 拓扑排序
        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int mouse = state[0], cat = state[1], turn = state[2];
            int result = results[mouse][cat][turn][0];
            int moves = results[mouse][cat][turn][1];
            List<int[]> prevStates = getPrevStates(mouse, cat, turn);
            for (int[] prevState : prevStates) {
                int prevMouse = prevState[0], prevCat = prevState[1], prevTurn = prevState[2];
                if (results[prevMouse][prevCat][prevTurn][0] == UNKNOWN) {
                    boolean canWin = (result == MOUSE_WIN && prevTurn == MOUSE_TURN) || (result == CAT_WIN && prevTurn == CAT_TURN);
                    if (canWin) {
                        results[prevMouse][prevCat][prevTurn][0] = result;
                        results[prevMouse][prevCat][prevTurn][1] = moves + 1;
                        queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                    } else {
                        degrees[prevMouse][prevCat][prevTurn]--;
                        if (degrees[prevMouse][prevCat][prevTurn] == 0) {
                            int loseResult = prevTurn == MOUSE_TURN ? CAT_WIN : MOUSE_WIN;
                            results[prevMouse][prevCat][prevTurn][0] = loseResult;
                            results[prevMouse][prevCat][prevTurn][1] = moves + 1;
                            queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                        }
                    }
                }
            }
        }
        return results[startMouse][startCat][MOUSE_TURN][0] == MOUSE_WIN && results[startMouse][startCat][MOUSE_TURN][1] <= MAX_MOVES;
    }

    private Queue<int[]> initData() {
        int total = rows * cols;
        degrees = new int[total][total][2];
        results = new int[total][total][2][2];
        Queue<int[]> queue = new ArrayDeque<>();
        // 计算每个状态的度
        for (int mouse = 0; mouse < total; mouse++) {
            int mouseRow = mouse / cols, mouseCol = mouse % cols;
            if (graph[mouseRow][mouseCol] == '#') {
                continue;
            }
            for (int cat = 0; cat < total; cat++) {
                int catRow = cat / cols, catCol = cat % cols;
                if (graph[catRow][catCol] == '#') {
                    continue;
                }
                degrees[mouse][cat][MOUSE_TURN]++;
                degrees[mouse][cat][CAT_TURN]++;
                for (int[] dir : DIRS) {
                    for (int row = mouseRow + dir[0], col = mouseCol + dir[1], jump = 1;
                         row >= 0 && row < rows && col >= 0 && col < cols && graph[row][col] != '#' && jump <= mouseJump;
                         row += dir[0], col += dir[1], jump++) {
                        int nextMouse = getPos(row, col);
                        // 显然，状态(nextMouse,cat,MOUSE_TURN) 可以由老鼠往dir方向一次性跳jump到达 (mouse,cat,CAT_TURN)，因此
                        degrees[nextMouse][cat][MOUSE_TURN]++;
                    }
                    for (int row = catRow + dir[0], col = catCol + dir[1], jump = 1;
                         row >= 0 && row < rows && col >= 0 && col < cols && graph[row][col] != '#' && jump <= catJump;
                         row += dir[0], col += dir[1], jump++) {
                        int nextCat = getPos(row, col);
                        degrees[mouse][nextCat][CAT_TURN]++;
                    }
                }
            }
        }
        // 猫和老鼠在同一个单元格，猫获胜
        for (int pos = 0; pos < total; pos++) {
            int row = pos / cols, col = pos % cols;
            if (graph[row][col] == '#') {
                continue;
            }
            results[pos][pos][MOUSE_TURN][0] = CAT_WIN;
            results[pos][pos][MOUSE_TURN][1] = 0;
            results[pos][pos][CAT_TURN][0] = CAT_WIN;
            results[pos][pos][CAT_TURN][1] = 0;
            queue.offer(new int[]{pos, pos, MOUSE_TURN});
            queue.offer(new int[]{pos, pos, CAT_TURN});
        }
        // 猫和食物在同一个单元格，猫获胜
        for (int mouse = 0; mouse < total; mouse++) {
            int mouseRow = mouse / cols, mouseCol = mouse % cols;
            if (graph[mouseRow][mouseCol] == '#' || mouse == food) {
                continue;
            }
            results[mouse][food][MOUSE_TURN][0] = CAT_WIN;
            results[mouse][food][MOUSE_TURN][1] = 0;
            results[mouse][food][CAT_TURN][0] = CAT_WIN;
            results[mouse][food][CAT_TURN][1] = 0;
            queue.offer(new int[]{mouse, food, MOUSE_TURN});
            queue.offer(new int[]{mouse, food, CAT_TURN});
        }
        // 老鼠和食物在同一个单元格且猫和食物不在同一个单元格，老鼠获胜
        for (int cat = 0; cat < total; cat++) {
            int catRow = cat / cols, catCol = cat % cols;
            if (graph[catRow][catCol] == '#' || cat == food) {
                continue;
            }
            results[food][cat][MOUSE_TURN][0] = MOUSE_WIN;
            results[food][cat][MOUSE_TURN][1] = 0;
            results[food][cat][CAT_TURN][0] = MOUSE_WIN;
            results[food][cat][CAT_TURN][1] = 0;
            queue.offer(new int[]{food, cat, MOUSE_TURN});
            queue.offer(new int[]{food, cat, CAT_TURN});
        }
        return queue;
    }

    public List<int[]> getPrevStates(int mouse, int cat, int turn) {
        List<int[]> prevStates = new ArrayList<>();
        int mouseRow = mouse / cols, mouseCol = mouse % cols;
        int catRow = cat / cols, catCol = cat % cols;
        int prevTurn = turn == MOUSE_TURN ? CAT_TURN : MOUSE_TURN;
        int maxJump = prevTurn == MOUSE_TURN ? mouseJump : catJump;
        int startRow = prevTurn == MOUSE_TURN ? mouseRow : catRow;
        int startCol = prevTurn == MOUSE_TURN ? mouseCol : catCol;
        prevStates.add(new int[]{mouse, cat, prevTurn});
        for (int[] dir : DIRS) {
            for (int i = startRow + dir[0], j = startCol + dir[1], jump = 1; i >= 0 && i < rows && j >= 0 && j < cols && graph[i][j] != '#' && jump <= maxJump; i += dir[0], j += dir[1], jump++) {
                int prevMouseRow = prevTurn == MOUSE_TURN ? i : mouseRow;
                int prevMouseCol = prevTurn == MOUSE_TURN ? j : mouseCol;
                int prevCatRow = prevTurn == MOUSE_TURN ? catRow : i;
                int prevCatCol = prevTurn == MOUSE_TURN ? catCol : j;
                int prevMouse = getPos(prevMouseRow, prevMouseCol);
                int prevCat = getPos(prevCatRow, prevCatCol);
                prevStates.add(new int[]{prevMouse, prevCat, prevTurn});
            }
        }
        return prevStates;
    }

    public int getPos(int row, int col) {
        return row * cols + col;
    }

    @Override
    public void case1() {
        String[] grid = {"####F", "#C...", "M...."};
        int catJump = 1, mouseJump = 2;
        Assert.assertTrue(canMouseWin(grid, catJump, mouseJump));
    }

    @Override
    public void case2() {
        String[] grid = {"M.C...F"};
        int catJump = 1, mouseJump = 4;
        Assert.assertTrue(canMouseWin(grid, catJump, mouseJump));
    }

    @Test
    public void case3() {
        String[] grid = {"M.C...F"};
        int catJump = 1, mouseJump = 3;
        Assert.assertFalse(canMouseWin(grid, catJump, mouseJump));
    }

    @Test
    public void case4() {
        String[] grid = {"C...#", "...#F", "....#", "M...."};
        int catJump = 1, mouseJump = 5;
        Assert.assertFalse(canMouseWin(grid, catJump, mouseJump));
    }

    @Test
    public void case5() {
        String[] grid = {".M...", "..#..", "#..#.", "C#.#.", "...#F"};
        int catJump = 3, mouseJump = 1;
        Assert.assertTrue(canMouseWin(grid, catJump, mouseJump));
    }

}
