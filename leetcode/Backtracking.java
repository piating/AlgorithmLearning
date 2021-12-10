package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Backtracking {


    public static void main(String[] args) {
        Backtracking backtracking = new Backtracking();
        int[] ints = {1,1,2};
        int[] ints1 = {4,6,7,7};
        System.out.println(backtracking.permuteUnique(ints));
        System.out.println(1);
    }

    List<String> listStr = new ArrayList<>();
    LinkedList<String> resStrList = new LinkedList<>();
    List<List<String>> resStr = new ArrayList<>();
    List<List<Integer>> resList = new ArrayList<>();
    LinkedList<Integer> list = new LinkedList<>();
    int targetSum = 0;

    /**
     * 47, 全排列 II   https://leetcode-cn.com/problems/permutations-ii/
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] judge = new boolean[nums.length];
        back10(nums, judge);
        return resList;
    }
    private void back10(int[] nums, boolean[] judge) {
        if (list.size() == nums.length) {
            resList.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if ((i > 0 && nums[i] == nums[i - 1] && !judge[i - 1]) || judge[i]) {
                continue;
            }
            list.add(nums[i]);
            judge[i] = true;
            back10(nums, judge);
            list.removeLast();
            judge[i] = false;
        }
    }

    /**
     * 46 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        boolean[] judge = new boolean[nums.length];
        back9(nums, judge);
        return resList;
    }
    private void back9(int[] nums, boolean[] judge) {
        if (list.size() == nums.length) {
            resList.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (judge[i]) {
                continue;
            }
            list.add(nums[i]);
            judge[i] = true;
            back9(nums, judge);
            list.removeLast();
            judge[i] = false;
        }
    }

    /**
     * 491 递增子序列 https://leetcode-cn.com/problems/increasing-subsequences/
     * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
     * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        back8(nums, 0);
        return resList;
    }
    private void back8(int[] nums, int startIndex) {
        if (list.size() > 1) {
            resList.add(new ArrayList<>(list));
        }
        int[] used = new int[201];
        for (int i = startIndex; i < nums.length; i++) {
            if (!list.isEmpty() && nums[i] < list.getLast() || (used[nums[i] + 100] == 1)) {
                continue;
            }
            used[nums[i] + 100] = 1;
            list.add(nums[i]);
            back8(nums, i + 1);
            list.removeLast();
        }
    }

    /**
     * 90 子集II https://leetcode-cn.com/problems/subsets-ii/
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        boolean[] flag = new boolean[nums.length];
        back7(nums, 0, flag);
        return resList;
    }
    private void back7(int[] nums, int startIndex, boolean[] flag) {
        resList.add(new ArrayList<>(list));
        if (list.size() >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1] && !flag[i - 1]) {
                continue;
            }
            flag[i] = true;
            list.add(nums[i]);
            back7(nums, i + 1, flag);
            flag[i] = false;
            list.removeLast();
        }
    }

    /**
     * 78 子集 https://leetcode-cn.com/problems/subsets/
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        back6(nums, 0);
        return resList;
    }
    private void back6(int[] nums, int startIndex) {
        resList.add(new ArrayList<>(list));
        if (list.size() >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            back6(nums, i + 1);
            list.removeLast();
        }
    }

    /**
     * 93 复原 IP 地址
     * https://leetcode-cn.com/problems/restore-ip-addresses/
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        back5(s, 0);
        return listStr;
    }
    private void back5(String s, int startIndex) {
        if (resStrList.size() > 4) {
            return;
        }
        if (startIndex >= s.length() && resStrList.size() == 4) {
            String temp = String.join(".", resStrList);
            listStr.add(temp);
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (judgeIp(s, startIndex, i)) {
                String temp = s.substring(startIndex, i + 1);
                resStrList.add(temp);
            } else {
                continue;
            }
            back5(s, i + 1);
            resStrList.removeLast();
        }
    }
    private boolean judgeIp(String s, int a, int b) {
        if (b - a > 2) {
            return false;
        }
        String temp = s.substring(a, b + 1);
        if (temp.length() > 1 && temp.charAt(0) == '0') {
            return false;
        }
        if (Integer.parseInt(temp) < 0  || Integer.parseInt(temp) > 255) {
            return false;
        }
        return true;
    }

    /**
     * 131
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * 回文串 是正着读和反着读都一样的字符串。
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        back4(s, 0);
        return resStr;
    }
    private void back4(String s, int startIndex) {
        if (startIndex >= s.length()) {
            resStr.add(new ArrayList<>(resStrList));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (judge(s, startIndex, i)) {
                String temp = s.substring(startIndex, i + 1);
                resStrList.add(temp);
            } else {
                continue;
            }
            back4(s, i + 1);
            resStrList.removeLast();
        }
    }
    private boolean judge(String s, int a, int b) {
        for (int i = a, j = b; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 40 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        boolean[] flag = new boolean[candidates.length];
        back4(target, 0, candidates, flag);
        return resList;
    }
    public void back4(int target, int startIndex, int[] candidates, boolean[] flag) {
        if (targetSum == target) {
            resList.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (targetSum + candidates[i] > target) {
                continue;
            }
            if (i > 0 && candidates[i] == candidates[i - 1] && !flag[i - 1]) {
                continue;
            }
            flag[i] = true;
            targetSum += candidates[i];
            list.add(candidates[i]);
            back4(target, i + 1, candidates, flag);
            list.removeLast();
            flag[i] = false;
            targetSum -= candidates[i];
        }
    }

    /**
     * 39  给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，
     * 找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        back3(target, 0, candidates);
        return resList;
    }
    public void back3(int target, int startIndex, int[] candidates) {
        if (targetSum == target) {
            resList.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (targetSum + candidates[i] > target) {
                continue;
            }
            targetSum += candidates[i];
            list.add(candidates[i]);
            back3(target, i, candidates);
            list.removeLast();
            targetSum -= candidates[i];
        }
    }

    /**
     * 17 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (int i = 0; i < digits.length(); i++) {
            int index = Integer.parseInt(String.valueOf(digits.charAt(i)));
            listStr.add(numString[index]);
        }
        if (listStr.size() == 0) {
            return resStrList;
        }
        back2(digits.length(), 0);
        return resStrList;
    }
    StringBuilder temp = new StringBuilder();
    public void back2(int k, int startIndex) {
        if (temp.length() > k) {
            return;
        }
        if (temp.length() == k) {
            resStrList.add(temp.toString());
            return;
        }
        String tempStr = listStr.get(startIndex);
        for (int j = 0; j < tempStr.length(); j++) {
            temp.append(tempStr.charAt(j));
            back2(k, startIndex + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }


    /**
     * 216.找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        back1(k, n, 1);
        return resList;
    }
    public void back1(int k, int n, int startIndex) {
        if (list.size() > k || targetSum > n) {
            return;
        }
        if (list.size() == k && targetSum == n) {
            resList.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i <= 9 - (k - list.size()) + 1; i++) {
            list.add(i);
            targetSum += i;
            back1(k, n, i + 1);
            list.removeLast();
            targetSum -= i;
        }
    }
    

    /**
     * 77 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        backList(n, k, 1);
        return resList;
    }
    public void backList(int n, int k, int startIndex) {
        if (list.size() == k) {
            resList.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            backList(n, k, i + 1);
            list.removeLast();
        }
    }
}
