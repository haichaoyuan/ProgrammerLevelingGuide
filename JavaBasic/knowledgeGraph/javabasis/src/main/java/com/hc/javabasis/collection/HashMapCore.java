package com.hc.javabasis.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 以下分析基于 JDK1.7
 * todo 预留红黑树代码
 * [1] HashMap 底层是基于数组和链表实现的，其中有两个重要的参数：容量和负载因子
 * 1. 容量默认大小是16，负载因子是 0.75，当HashMap 的 size > 16* 0.75 = 12 就会发生扩容
 * 2. 扩容涉及到 rehash, 重新取模, 复制数据操作，比较耗时
 * Author: hc
 * DATE: 2019/1/28 = 5:20 PM
 */
public class HashMapCore<K, V> {
    HashMap<String, Integer> hashMap = new HashMap<>();

    /**
     * [2] put 方法
     * 1. 将传入的 key 做 hash 运算，计算出 hashcode
     * 2. 根据数组长度取模计算出在数组的索引 -> index（使数据均匀）
     * 3. 由于取模运算计算耗时，这里有个讨巧的方式，规定数组长度为 2^n, 这样可以使用 2^n -1做位运算，其效果等同于取模
     * 4. 由于数组长度有限，出现不同key 得到的 index 相同，因此引入链表,更多的话，超过8个就会转换成红黑树
     * 5. 转成红黑树，时间复杂度是O(n) -> O(LOGn)
     */
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * [3]  获取 key 的hash值，让前16位和后16位做异或操作XOR
     * 在效率、效能和质量间做权衡(tradeoff between speed, utility, and quality of bit-spreading)
     *
     * @param key key
     * @return hash 值
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    transient Node<K, V>[] table;//数据桶
    transient int size;
    transient int modCount;//结构修改计数
    final float loadFactor = DEFAULT_LOAD_FACTOR;//增加因素，可以设置，此处方便直接赋值
    int threshold;//resize 扩容的值 (capacity * load factor)

    static final int MAXIMUM_CAPACITY = 1 << 30;//最大容量
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;//2^4 = 16,默认尺寸
    static final float DEFAULT_LOAD_FACTOR = 0.75f;//默认增长
    static final int TREEIFY_THRESHOLD = 8;//红黑树的 threshold，超过8个转换成树

    /**
     * 实现 Map.put 和向关联方法
     *
     * @param hash         key 的hash 值
     * @param key          key
     * @param value        value
     * @param onlyIfAbsent 如果设为true,不会改变已经存在的值
     * @param evict        如果false,这个 table 是创建模式
     * @return 先前的值，或者 null
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n;
        if ((tab = table) == null || (n = tab.length) == 0) {
            //初始化 table 数组，容量是16
            n = (tab = resize()).length;
        }
        //取模
        int i = (n - 1) & hash;
        if ((p = tab[i]) == null) {
            // 索引值上没数据
            tab[i] = newNode(hash, key, value, null);
        } else {
            // 索引值上存在数据
            Node<K, V> e;
            K k;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k)))) {
                //索引上的值就是自己
                e = p;
            } else if (p instanceof TreeNode) {
                //todo 红黑树
                // 往红黑树上存储数据
                e = null;
            } else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        //找到尾巴，添加新的
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) {
                            //treeifyBin(tab, hash); todo 转红黑树
                            //假设 hash 冲突非常严重，一个数组后面接了很长的链表，此时重新的时间复杂度就是 O(n) 。
                            //如果是红黑树，时间复杂度就是 O(logn)
                        }
                        break;
                    }
                    //找到
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    //移到下一个
                    p = e;
                }
            }
            if (e != null) {
                // 存在 key 的映射值
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null) {
                    e.value = value;
                }
//                afterNodeAccess(e);
                return oldValue;
            }
        }
        //新的索引中插入
        ++modCount;
        if (++size > threshold) {
            resize();
        }
        //afterNodeInsertion(evict);
        return null;
    }

    /** 尺寸扩容
     * @return 尺寸扩容
     */
    final Node<K, V>[] resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                //1. old capacity 到达上限，直接返回 Integer.MAX_VALUE
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY) {
                //2. 可以放大的条件。old capacity 可以扩大两倍也没达到上限，并且比 16 大
                // new threshold 也变大两倍
                newThr = oldThr << 1;//变大两倍
            }
        } else if (oldThr > 0) {
            // 3. old capacity <= 0 && old threshold > 0
            newCap = oldThr;
        } else {//初始化操作
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ? (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
        table = newTab;
        // todo 扩容后原数据的迁移
        return newTab;
    }

    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
        return new Node<>(hash, key, value, next);
    }

    /**
     * 基础 hash 节点
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> implements Map.Entry<K, V> {

        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key) ^ Objects.hash(value);
        }
    }

    static final class TreeNode<K, V> extends Node<K, V> {

        TreeNode(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }
    }
}
