/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.test;

import com.redis.example.demo.domain.User;

import java.util.*;

/**
 *
 * @author xuleyan
 * @version TestMap.java, v 0.1 2020-12-28 9:54 下午
 */
public class TestMap {

    public static void main(String[] args) {
//        Map<String, Integer> map = new HashMap<>(16);
//        for (int i = 0; i < 10; i++) {
//            map.put("key" + i, i);
//        }

//        Comparator<Integer> comparator = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                if (o1.equals(o2)) {
//                    return 0;
//                }
//                return o1 > o2 ? -1 : 1;
//            }
//        };
//

//        PriorityQueue<Integer> queue = new PriorityQueue<>(11);
//
//        queue.add(2);
//        queue.add(4);
//        queue.offer(6);
//        queue.add(3);

//        Integer first = queue.poll();
//        System.out.println(first);

//        Integer first = queue.peek();
//        System.out.println(first);
//        System.out.println(queue);

        PriorityQueue<User> userList = new PriorityQueue<>();
        userList.add(getUser(1));
        userList.add(getUser(4));
        userList.add(getUser(3));
        userList.add(getUser(2));

        System.out.println(userList);
    }

    private static User getUser(Integer age) {
        User user = new User();
        user.setAge(age);
        return user;
    }
}