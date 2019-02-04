package com.hc.javabasis.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 1. 众所周知，HashMap 是一个无序的Map ,每次根据 key 的 hashcode 映射到 Entry 数组，所以遍历出来的顺序不是写入的顺序
 * 2. LinkHashMap 增加双向链表 head、 tail，可根据写入顺序或访问顺序进行排序
 * 3. LinkedHashMapEntry 是在 HashMapEntry 上增加 before, after
 * Author: hc
 * DATE: 2019/2/3 = 6:32 PM
 */
public class LinkedHashMapCore<K, V> {
    static class LinkedHashMapEntry<K,V> extends HashMapCore.Node<K,V> {
        LinkedHashMapEntry<K,V> before, after;
        LinkedHashMapEntry(int hash, K key, V value, HashMapCore.Node<K,V> next) {
            super(hash, key, value, next);
        }
    }

    transient LinkedHashMapEntry<K,V> head;

    transient LinkedHashMapEntry<K,V> tail;
}
