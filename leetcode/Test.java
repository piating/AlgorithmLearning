package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Test {

    public static void main(String[] args) {
        // String[] cmdArr1 = {"cd /aa/bb/cc/dd/", "cd ./ee/ff", "cd gg/../hh"};
        // String[] cmdArr2 = {"cd", "cd .."};
        // String[] cmdArr3 = {"cd /aa/bb//", "cd ..", "cd -", "cd -"};
        // String[] cmdArr4 = {"cd /"};
        // int[] cmdArr5 = {1, 3, 2, 4, 5};
        // int[][] cmdArr6 = {{1, -1, 0}, {0, 1, 1}, {1, -1, 1}};
        System.out.println(simplifyPath("/home//foo/"));
    }
    public static String simplifyPath(String path) {
        path = path.replaceAll("//", "/");
        String[] cmds = path.split("/");
        Deque<String> deque = new LinkedList<>();
        for (String cmd : cmds) {
            switch (cmd) {
                case ".":
                case "":
                    continue;
                case "..":
                    if (!deque.isEmpty()) {
                        deque.pop();
                    }
                    break;
                default:
                    deque.push(cmd);
                    break;
            }
        }
        if (deque.size() == 0) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/").append(deque.getLast());
            deque.removeLast();
        }
        return sb.toString();
    }

    /**
     * 某简易的linux目录系统cd命令
     * @param cmds
     * @return
     */
    private static String getCurrentDirectory(String[] cmds) {
        HashMap<Integer, LinkedList<String>> mapPath = new HashMap<>(16);
        LinkedList<String> pathList = new LinkedList<>();
        String tempStr = "/";
        for (int i = 0; i < cmds.length; i++) {
            if (cmds[i].contains("//")) {
                cmds[i] = cmds[i].replace("//", tempStr);
            }
            switch (cmds[i]) {
                case "cd":
                    pathList = new LinkedList<>(Arrays.asList("home", "user"));
                    break;
                case "cd -":
                    pathList = mapPath.get((i - 1) % 3);
                    break;
                case "cd ..":
                    pathList.removeLast();
                    break;
                case "cd /":
                    pathList = (LinkedList<String>) Collections.singletonList(tempStr);
                    break;
                default:
                    getCmdPath(cmds[i].substring(3), pathList, tempStr);
                    break;
            }
            mapPath.put((i + 1) % 3, new LinkedList<>(pathList));
        }
        String res = String.join(tempStr, pathList);
        if (!res.startsWith(tempStr)) {
            return tempStr + res;
        }
        return res;
    }
    private static void getCmdPath(String cmd, LinkedList<String> pathList, String tempStr) {
        if (cmd == null || "".equals(cmd)) {
            pathList.add("home");
            pathList.add("user");
        }
        String[] cmdArr = cmd.split(tempStr);
        for (String str : cmdArr) {
            switch (str) {
                case ".":
                    break;
                case "..":
                    pathList.removeLast();
                    break;
                default:
                    pathList.add(str);
                    break;
            }
        }
    }

    /**
     * 著名的快速排序算法里有一个经典的划分过程：通常
     * 给定一个划分后的正整数序列，请问有多少个元素可能是划分过程中选取的主元
     *
     * @param numbers
     * @return
     */
    private static int[] quickSort(int[] numbers) {
        int left = 0;
        int right = 0;
        int len = numbers.length;
        Map<Integer, Integer> mapLeft = new HashMap<>();
        mapLeft.put(len - 1, numbers[len - 1]);
        while (left < len - 1) {
            right = left + 1;
            while (numbers[right] > numbers[left]) {
                if (right == len - 1) {
                    mapLeft.put(left, numbers[left]);
                    break;
                }
                right++;
            }
            left++;
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = mapLeft.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (entry.getKey() == 0) {
                continue;
            }
            left = entry.getKey() - 1;
            while (numbers[left] < entry.getValue()) {
                left--;
                if (left < 0) {
                    break;
                }
            }
            if (left >= 0) {
                iterator.remove();
            }
        }
        return mapLeft.values().stream().sorted().mapToInt(Integer::intValue).toArray();
    }
    private static int[] quickSort1(int[] array) {
        int index = 0;
        int[] re = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            boolean b = true;
            //1 3 2 4 5
            int nowNum = array[i];
            //使该元素比较左边的
            for (int j = 0; j < i; j++) {
                if (nowNum < array[j]) {
                    b = false;
                    break;
                }
            }
            //使该元素比较右边的
            for (int k = i + 1; k < array.length; k++) {
                if (nowNum > array[k]) {
                    b = false;
                    break;
                }
            }
            if (b) {
                re[index] = array[i];
                index++;
            }
        }
        return Arrays.stream(re).limit(index).sorted().toArray();
    }
    private static int[] quickSort2(int[] array) {
        int index = 0;
        int[] re = new int[array.length];
        // 保留原来数组 23
        int[] temp = array.clone();
        // 利用java自己排序（目的：找到索引相同值相同的 元素-->可能是主元素）
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            // 找到相同索引&值的元素 主元素判断条件1
            if (array[i] == temp[i]) {
                boolean flag = true;
                for (int j = 0; j < i; j++) {
                    // 判断 主元之前的最大数。主元素判断条件2
                    if (temp[j] > temp[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    // 加入结果数组中
                    re[index] = temp[i];
                    index++;
                }
            }
        }
        return Arrays.stream(re).limit(index).sorted().toArray();
    }


    /**
     * 终端公司的零售店需要定期去仓库提取货物，假设零售店和仓库在一个矩阵上
     *
     * @param grid
     * @return
     */
    private static int nearestWareHouse(int[][] grid) {

        int len = grid.length;
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            int[] cols = grid[i];

            for (int j = 0; j < cols.length; j++) {
                if (cols[j] == 1) {
                    // while () {
                    //
                    // }
                }
            }
        }
        // 在此补充你的代码
        return 0;
    }
    private int getLen(int row, int col, int[][] grid, int len) {
        int[] rows = grid[row];
        int[] cols = getColArr(col, grid, len);
        for (int i = 0; i < rows.length; i++) {

        }
        return 0;
    }
    private int[] getColArr(int col, int[][] grid, int len) {
        int[] resArr = new int[len];
        for (int i = 0; i < grid.length; i++) {
            int[] cols = grid[i];
            for (int j = 0; j < cols.length; j++) {
                if (j == col) {
                    resArr[i] = cols[j];
                }
            }
        }
        return resArr;
    }
    public int solve(int[][] metrics) {
        int rowNumb = metrics.length;
        int columnNumb = metrics[0].length;

        int totalMinDistance = 0;
        for (int i = 0; i < rowNumb; i++) {
            for (int j = 0; j < columnNumb; j++) {
                if (metrics[i][j] == 1) {
                    totalMinDistance += getMinDistance(metrics, i, j);
                }
            }
        }
        return totalMinDistance;
    }

    /**
     * bfs
     *
     * @param metrics
     * @param row
     * @param column
     * @return
     */
    private static int getMinDistance(int[][] metrics, int row, int column) {
        int rowNumb = metrics.length;
        int columnNumb = metrics[0].length;

        int[][] moveSteps = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // u d l r
        Queue<int[]> readyQueue = new ArrayDeque<>();
        int[][] visited = new int[rowNumb][columnNumb];
        int[] startLocation = {row, column};
        readyQueue.offer(startLocation);
        visited[row][column] = 1;

        int stepCnt = 0;
        while (!readyQueue.isEmpty()) {
            int queueSize = readyQueue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] currentLocation = readyQueue.poll();
                int currentValue = metrics[currentLocation[0]][currentLocation[1]];
                if (currentValue == 0) {
                    return stepCnt;
                }
                for (int[] moveStep : moveSteps) {
                    int newRow = currentLocation[0] + moveStep[0];
                    int newColumn = currentLocation[1] + moveStep[1];
                    if (newRow < 0 || newRow >= rowNumb || newColumn < 0 || newColumn >= columnNumb) {
                        continue;
                    }
                    int[] newLocation = {newRow, newColumn};
                    if (metrics[newRow][newColumn] == -1) {
                        continue;
                    }
                    if (visited[newRow][newColumn] == 0) {
                        readyQueue.offer(newLocation);
                        visited[newRow][newColumn] = 1;
                    }
                }
            }
            stepCnt++;
        }
        return 0;
    }


}
