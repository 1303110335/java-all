/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.queue;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Lists;
import com.redis.example.demo.domain.User;
import lombok.Data;

import java.util.*;

/**
 * 模拟shardingjdbc，利用优先队列的优点从多个结果集中合并筛选出需要的数据
 * @author xuleyan
 * @version Holder.java, v 0.1 2021-08-24 3:49 下午
 */
@Data
public class Holder implements Comparable<Holder> {

    /**
     * 数据集
     */
    private LinkedList<Integer> queryResult;

    /**
     * 排序 asc, desc
     */
    private List<OrderItem> orderItems;

    /**
     * 最新的值
     */
    private List<Comparable<?>> orderValues;

    public static void main(String[] args) {
        // 这个优先队列，始终会将orderValues最小的那个Holder放在堆顶的位置，所以在priorityQueue中peek的时候，始终取到的是最小的那个Holder
        PriorityQueue<Holder> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(makeOrder());
        priorityQueue.offer(makeOrder());

        // 拿到堆顶的holder并移除
        List<User> result = new ArrayList<>();
        for (int i = 0; i< 5; i++) {
            Holder peek = priorityQueue.poll();
            result.add((User) peek.getOrderValues().get(0));
            // 如果该holder还有数据，则添加回优先队列中，并将最新的数据加入到orderValues中
            if (peek.hasNext()) {
                priorityQueue.offer(peek);
            }
        }

        System.out.println(JSON.toJSONString(result));
    }

    private static Holder makeOrder() {
        Random random = new Random();
        int number = random.nextInt(10) + 10;
        System.out.println("number = " + number);

        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderBy(OrderEnum.DESC.name());
        orderItems.add(orderItem);

        LinkedList<Integer> queryList = Lists.newLinkedList();
        for (int i = number - 10; i < number; i+= 2) {
            queryList.add(i);
        }
        queryList.sort((o1, o2) -> o2 - o1);
        System.out.println("queryList =" + queryList);

        List<Comparable<?>> orderValues = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setMobile("183");
        user.setAge(queryList.pollFirst());
        orderValues.add(user);

        Holder holder = new Holder();
        holder.setOrderItems(orderItems);
        holder.setOrderValues(orderValues);
        holder.setQueryResult(queryList);
        return holder;
    }

    private boolean hasNext() {
        Integer first = queryResult.pollFirst();
        if (first != null) {
            User user = new User();
            user.setId(1);
            user.setMobile("183");
            user.setAge(first);
            this.orderValues.set(0, user);
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Holder o) {
        for (int i = 0; i < orderItems.size(); i++) {
            Comparable thisOrder = orderValues.get(i);
            Comparable otherOrder = o.getOrderValues().get(i);
            if (OrderEnum.DESC.name().equals(orderItems.get(i).getOrderBy())) {
                // 大顶堆
                return otherOrder.compareTo(thisOrder);
            } else {
                // 小顶堆
                return thisOrder.compareTo(otherOrder);
            }
        }
        return 0;
    }
}