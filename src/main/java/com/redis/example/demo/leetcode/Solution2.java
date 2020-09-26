/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author xuleyan
 * @version Solution2.java, v 0.1 2020-09-16 9:28 下午
 */
class Solution2 {
    Random random = new Random();

    public static void main(String[] args) {
//        Random random = new Random();
//        System.out.println(random.nextInt(10));
        Solution2 solution = new Solution2();
        int kthLargest = solution.findKthLargest(new int[]{1, 3, 2, 7, 5, 4}, 2);
        System.out.println(kthLargest);
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        //System.out.println(Arrays.asList(nums));
    }
}