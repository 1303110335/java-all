/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo;

/**
 *
 * @author xuleyan
 * @version TestBitMap.java, v 0.1 2020-12-23 2:21 下午
 */
public class TestBitMap {

    public static void main(String[] args) {
        int n = 100;
        new TestBitMap().create(n);
    }

    public byte[] create(int n) {
        byte[] bits = new byte[getIndex(n) + 1];

        for (int i = 0; i < n; i++) {
            add(bits, i);
        }

        System.out.println(contains(bits, 11));

        int index = 1;
        for (byte bit : bits) {
            System.out.println("-------" + index++ + "-------");
            showByte(bit);
        }
        return bits;
    }

    /**
     * 打印byte类型的变量<br/>
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     * @param b
     */
    private void showByte(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte)(b & 1);
            b = (byte)(b >> 1);
        }

        for (byte b1 : array) {
            System.out.print(b1 + " ");
        }

        System.out.println("");
    }

    /**
     * 判断指定数字num是否存在<br/>
     * 将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
     * @param bits
     * @param num
     * @return
     */
    public boolean contains(byte[] bits, int num) {
        return (bits[getIndex(num)] & (1 << getPosition(num))) != 0;
    }

    /**
     * num/8得到byte[]的index
     * @param num
     * @return
     */
    private int getIndex(int num) {
        return num >> 3;
    }

    /**
     * 标记指定数字（num）在bitmap中的值，标记其已经出现过<br/>
     * 将1左移position后，那个位置自然就是1，然后和以前的数据做|，这样，那个位置就替换成1了
     * @param bits
     * @param num
     */
    public void add(byte[] bits, int num) {
        bits[getIndex(num)] |= 1 << getPosition(num);
    }

    /**
     * num%8得到在byte[index]的位置
     * @param num
     * @return
     */
    private int getPosition(int num) {
        return num & 0x07;
    }
}