/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.leetcode;

import java.util.Arrays;

/**
 * 击败8.21%	43 ms
 * @author xuleyan
 * @version Solution.java, v 0.1 2020-09-16 10:57 上午
 */
class Solution3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] nums = new int[]{1, 3, 7, 2, 5, 4};

        int kthLargest = solution.findKthLargest(nums, 2);
        System.out.println(kthLargest);
    }

    public int findKthLargest(int[] nums, int index) {
        int len = nums.length;
        return quickSort(nums, 0, len-1, len - index);
    }

    public Integer quickSort(int[] nums, int start, int end, int index) {

        int p = partition(nums, start, end);
        if (p == index) {
            return nums[p];
        } else {
            return p > index ? quickSort(nums, start, p - 1, index) : quickSort(nums, p + 1, end, index);
        }
    }

    private int partition(int[] nums, int left, int right) {
        // 将右边的数作为key
        // 从左边开始和key进行对比，若比他大则放入他的位置

        int key = nums[right];
        int i = left - 1;
        for (int j = left; j < right ; ++j) {
            // i + 1 用来定位大于key的index值
            // 如果找到了大于key的值，则i不变，等待下次找到小于key的值再进行交换
            if (nums[j] <= key) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i+1, right);
        return i+1;
    }

    public void swap(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;

//        Arrays.stream(nums).forEach(System.out::print);
//        System.out.println();
    }


}