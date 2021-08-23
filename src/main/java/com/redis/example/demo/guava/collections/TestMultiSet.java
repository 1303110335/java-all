/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.guava.collections;

import com.google.common.collect.*;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections4.multiset.HashMultiSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author xuleyan
 * @version TestMultiSet.java, v 0.1 2021-08-23 4:14 下午
 */
public class TestMultiSet {

    /**
     * 在介绍Multimap之前我们先说一下Map,Map是一个key、value类型的键值对集合，集合中key不可以重复，但value可以重复，
     * 如果我们想在Map集合中存入一个相同的key，不同的value值得时候就必须使用Map<Integer,List<Object>>
     *     Map<Integer,List<Object>>模式来存数据，这样做很麻烦，而且编程效率又很低；
     *
     * @param args
     */
    public static void main(String[] args) {
//        testHashMultiMap();
//        testLinkedHashMultiMap();
//        testTreeMultiMap();

        testBiMap();
    }

    /**
     * BiMap<K, V>是特殊的Map：
     *
     * 可以用 inverse()反转BiMap<K, V>的键值映射
     * 保证值是唯一的，因此 values()返回Set而不是普通的Collection
     */
    private static void testBiMap() {
        BiMap<String, Integer> name2UserId = HashBiMap.create();
        name2UserId.put("xly", 27);
        name2UserId.put("wy", 28);
        name2UserId.put("xq", 30);
        System.out.println(name2UserId);

        String username = name2UserId.inverse().get(27);
        System.out.println(username);

    }

    /**
     * 具有排序
     */
    private static void testTreeMultiMap() {
        TreeMultimap<Integer, Integer> map = TreeMultimap.<Integer, Integer>create();
        map.putAll(4, Arrays.asList(5,3,4,2,1,56));
        map.putAll(3, Arrays.asList(3,4,2,6,8,7));
        map.put(1, 2);
        System.out.println(map);
    }

    /**
     * key不可以重复，相同key的key-value pair 的value值是放在同一个数组中，相同的value会去重。
     */
    private static void testHashMultiMap() {
        Multimap<Integer, Integer> map = HashMultimap.<Integer, Integer>create();

        map.put(1, 2);
        map.put(1, 2);
        map.put(1, 3);
        map.put(1, 4);
        map.put(2, 3);
        map.put(3, 3);
        map.put(4, 3);
        map.put(5, 3);

        // {1=[4, 2, 3], 2=[3], 3=[3], 4=[3], 5=[3]}
        System.out.println(map);

        //判断集合中是否存在key-value为指定值得元素
        System.out.println(map.containsEntry(1, 2));
        System.out.println(map.containsEntry(1, 6));
        //获取key为1的value集合
        Collection<Integer> list = map.get(1);
        System.out.println(list);
        //返回集合中所有key的集合，重复的key将会用key * num的方式来表示
        Multiset<Integer> set = map.keys();
        System.out.println(set);
        //返回集合中所有不重复的key的集合
        Set<Integer> kset = map.keySet();
        System.out.println(kset);

    }

    /**
     * 保存插入时的顺序
     */
    private static void testLinkedHashMultiMap() {
        Multimap<Integer, Integer> map = LinkedHashMultimap.<Integer, Integer>create();
        map.putAll(4, Arrays.asList(5,3,4,2,1,56));
        map.putAll(3, Arrays.asList(3,4,2,6,8,7));
        map.put(1, 2);
        // {4=[5, 3, 4, 2, 1, 56], 3=[3, 4, 2, 6, 8, 7], 1=[2]}
        System.out.println(map);
    }


}