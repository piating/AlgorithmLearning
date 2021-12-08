package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HashStudy {

    public static void main(String[] args) {
        // System.out.println(String.valueOf((char) 97));
        String[] arr1 = {"cool","lock","cook"};
        // commonChars(arr1);
        HashStudy hashStudy = new HashStudy();
        int[] arr2 = {0,0,0,1000000000,1000000000,1000000000,1000000000};
        System.out.println(hashStudy.fourSum(arr2, 1000000000));

    }

    /**
     * 1002
     * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），
     * 并以数组形式返回。你可以按 任意顺序 返回答案。
     *
     * @param words
     * @return
     */
    public static List<String> commonChars(String[] words) {
        int[] hashSum = new int[26];
        for (int i = 0; i < words.length; i++) {
            int[] hash = new int[26];
            for (char c : words[i].toCharArray()) {
                hash[c - 'a'] += 1;
            }
            if (i == 0) {
                hashSum = Arrays.copyOf(hash, 26);
            }else {
                for (int j = 0; j < hashSum.length; j++) {
                    hashSum[j] = Math.min(hash[j], hashSum[j]);
                }
            }
        }
        List<String> resList = new ArrayList<>();
        for (int i = 0; i < hashSum.length; i++) {
            for (int j = 0; j < hashSum[i]; j++) {
                resList.add(String.valueOf((char) (97 + i)));
            }
        }
        return resList;
    }

    /**
     * 349 给定两个数组，编写一个函数来计算它们的交集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> numsList1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> numsList2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());
        numsList1.retainAll(numsList2);
        return numsList1.stream().distinct().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 202
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        return judge(new HashSet<>(), n);
    }
    private boolean judge(Set<Integer> set, int n) {
        int m = 0;
        while (n > 0) {
            int temp = n % 10;
            m += Math.pow(temp, 2);
            n = n / 10;
        }
        if (set.contains(m)) {
            return false;
        }else if (m == 1) {
            return true;
        }
        set.add(m);
        return judge(set, m);
    }

    /**
     * 1
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(16);
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[0] = i;
                res[1] = map.get(temp);
                break;
            }else {
                map.put(nums[i], i);
            }
        }
        return res;
    }

    /**
     * 454
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
     *
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>(16);
        int temp;
        int res = 0;
        for (int i : nums1) {
            for (int j : nums2) {
                temp = i + j;
                map.put(temp, map.containsKey(temp) ? map.get(temp) + 1 : 1);
            }
        }
        for (int i : nums3) {
            for (int j : nums4) {
                temp = i + j;
                if (map.containsKey(0 - temp)) {
                    res += map.get(0 - temp);
                }
            }
        }
        return res;
    }

    /**
     * 383. 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] numArr = new int[26];
        for (char c : magazine.toCharArray()) {
            numArr[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            numArr[c - 'a']--;
            if (numArr[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 15. 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = 0;
        List<List<Integer>> resList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return resList;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                }else if (sum < 0) {
                    left++;
                }else {
                    resList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去除重复
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    right--;
                    left++;
                }
            }

        }
        return resList;
    }

    /**
     * 18 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
     *
     * 0 <= a, b, c, d < n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> resList = new ArrayList<>();
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 获取当前最小值,如果最小值比目标值大,说明后面越来越大的值根本没戏
            long n = (long) nums[i] + (long) nums[i + 1] + (long) nums[i + 2] + (long) nums[i + 3];
            if (n > target) {
                break; // 这里使用的break,直接退出此次循环,因为后面的数只会更大
            }
            // 获取当前最大值,如果最大值比目标值小,说明后面越来越小的值根本没戏,忽略
            long u = (long) nums[i] + (long) nums[length - 3] + (long) nums[length - 2] + (long) nums[length - 1];
            if (u < target) {
                continue; // 这里使用continue,继续下一次循环,因为下一次循环有更大的数
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 获取当前最小值,如果最小值比目标值大,说明后面越来越大的值根本没戏
                long m = (long) nums[i] + (long) nums[j] + (long) nums[j + 1] + (long) nums[j + 2];
                if (m > target) {
                    break; // 这里使用的break,直接退出此次循环,因为后面的数只会更大
                }
                // 获取当前最大值,如果最大值比目标值小,说明后面越来越小的值根本没戏,忽略
                long s = (long) nums[i] + (long) nums[j] + (long) nums[length - 2] + (long) nums[length - 1];
                if (s < target) {
                    continue; // 这里使用continue,继续下一次循环,因为下一次循环有更大的数
                }
                int left = j + 1;
                int right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    }else if (sum < target) {
                        left++;
                    }else {
                        resList.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 去除重复
                        left++;
                        while (right > left && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        right--;
                        while (right > left && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    }
                }
            }
        }
        return resList;
    }

}
