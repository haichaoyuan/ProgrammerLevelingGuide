package com.hc.javabasis.collection;

import java.util.HashMap;
import java.util.HashSet;

/**
 * [1] HashSet 是一个不允许存储重复元素的集合。实现比较简单，内部通过HashMap 来存储
 * Author: hc
 * DATE: 2019/2/3 = 6:20 PM
 */
public class HashSetCore<E> {
    //========================== add in 2019/2/3 : 成员变量

    private transient HashMap<E, Object> map;

    /**
     * 写入 map 的值
     */
    private static final Object PRESENT = new Object();

    //========================== add in 2019/2/3 : add 方法

    /**[1] HashSet 是一个不允许存储重复元素的集合。实现比较简单，内部通过HashMap 来存储
     * [2] 利用put 数据到 HashMap，存在相同key 会返回旧的数据的特性来返回是否存在数据
     * @param e e
     * @return 是否存在数据
     */
    public boolean add(E e){
        return map.put(e, PRESENT) == null;
    }
}
