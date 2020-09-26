/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.leetcode;

/**
 * 击败8.21%	43 ms
 * @author xuleyan
 * @version Solution.java, v 0.1 2020-09-16 10:57 上午
 */
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int kthLargest = solution.findKthLargest(new int[]{1, 3, 7, 2, 5, 4}, 2);
        System.out.println(kthLargest);
    }
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        quickSort(nums, 0, len-1);
        return nums[len - k];
    }

    public void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }

        if (nums.length <= 1) {
            return;
        }
        int left = start;
        int right = end;
        int key = nums[left];
        while (left < right) {
            while (nums[right] >= key && left < right) {
                right --;
            }

            while (nums[left] <= key && left < right) {
                left ++;
            }

            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        nums[start] = nums[left];
        nums[left] = key;

        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }


}