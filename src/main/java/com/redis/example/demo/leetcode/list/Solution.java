/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.leetcode.list;

import java.util.Arrays;
import java.util.Collections;

/**
 * 示例：
 *
 * 输入：
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出：[1,2,2,3,5,6]
 *  
 *
 * 提示：
 *
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1.length == m + n
 * nums2.length == n
 *
 * @author xuleyan
 * @version Solution.java, v 0.1 2020-11-18 5:03 下午
 */
public class Solution {

    public static void main(String[] args) {
       int[] nums1 = {1,2,3,0,0,0};
       int[] nums2 = {2,5,6};
        Solution solution = new Solution();
        solution.merge(nums1, 3, nums2, 3);
    }

    /**
     * 双指针模式
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);

        // nums1Copy的指针
        int p1 = 0;
        // nums2的指针
        int p2 = 0;
        // nums1的指针
        int p = 0;

        while ((p1 < m) && (p2 < n)) {
            nums1[p++] = nums1Copy[p1] < nums2[p2] ? nums1Copy[p1++] : nums2[p2++];
        }

        if (p1 < m) {
            System.arraycopy(nums1Copy, p1, nums1, p1 + p2, m + n - p1 -p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1,p1 + p2, m + n - p1 - p2);
        }

        for (int i : nums1) {
            System.out.println(i);
        }
    }

    /**
     * 时间复杂度 : O((n+m)log(n+m))。
     * 空间复杂度 : O(1)。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
        for (int i : nums1) {
            System.out.println(i);
        }
    }
}