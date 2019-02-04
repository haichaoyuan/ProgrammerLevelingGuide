package com.hc.javabasis.collection;

import java.util.LinkedList;

/**
 * LinkedList 底层是基于双向链表实现的，也实现了 list 接口
 * Author: hc
 * DATE: 2019/1/28 = 4:49 PM
 */
public class LinkedListCore<E> {
    LinkedList<String> linkedList = new LinkedList<>();

    transient Node<E> first;
    transient Node<E> last;
    transient private int size;
    transient private int modCount;

    /**
     * [1] 我们先来看下 add 实现
     *
     * @param e item
     * @return true or exception
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * [1] 双向链表的添加就是添加到尾巴喽,每次插入都是移动指针，效率远高于 ArrayList 的 add 方法
     *
     * @param e item
     */
    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
    }

    /**
     * [2] 查询方法
     *
     * @param index 索引
     * @return item
     */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * 检查 index in [0,size)，若不对，直接抛异常
     *
     * @param index index
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {//索引不是合格的
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 检查 index in [0,size)
     *
     * @param index index
     * @return 检查 index 是否 in [0,size)
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 索引越界消息
     *
     * @param index index
     * @return 索引越界消息
     */
    private String outOfBoundsMsg(int index) {
        return "Index:" + index + ",Size:" + size;
    }

    /**
     * 返回知道索引的 节点
     * 1. 根据index 是否处于前半段，还是后半段，分别进行前查询或后查询
     * 2. 时间耗时 O(n/2)
     * @param index
     * @return
     */
    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * 内部静态类, 双向链表
     *
     * @param <E>
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
