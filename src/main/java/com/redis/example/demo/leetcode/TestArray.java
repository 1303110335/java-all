/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version TestArray.java, v 0.1 2020-09-07 9:47 上午
 */
public class TestArray {

    public static void main(String[] args) {
        TestArray testArray = new TestArray();
        int[] nums = new int[] {3,5,2,1,6,4};
        int kthLargest = testArray.findKthLargest(nums);
        System.out.println(kthLargest);
    }

    /**
     * 1). 先从数列中取出一个数作为基准数。
     * 2). 分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
     * 3). 再对左右区间重复第二步，直到各区间只有一个数。
     *
     * 找到排序后第 k 个最大的元素
     * [3,2,1,5,6,4] 和 k = 2
     * @param nums
     * @return
     */
    public int findKthLargest(int[] nums) {

        int len = nums.length;
        qsort(nums, 0, len-1);
        System.out.println(arrayToString(nums));
        return nums[len - 1];
    }

    private void qsort(int[] nums, int start, int end) {
        if (nums.length < 0) {
            return;
        }

        if (start >= end) {
            return;
        }

        int left = start;
        int right = end;
        int key = nums[left];
        while (left < right) {
            // right 向左移动，直到遇到比自己小的
            while (nums[right] >= key && left < right) {
                right --;
            }

            // left 向右移动，直到遇到比自己大的
            while (nums[left] <= key && left < right) {
                left ++;
            }

            // 交换左右
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        nums[start] = nums[left];
        nums[left] = key;

        qsort(nums, start, left - 1);
        qsort(nums, left + 1, end);
    }

    /**
     * 将一个int类型数组转化为字符串
     * @param arr
     * @return
     */
    private static String arrayToString(int[] arr) {
        StringBuilder str = new StringBuilder();
        for(int a : arr) {
            str.append(a).append("\t");
        }
        return str.toString();
    }

}