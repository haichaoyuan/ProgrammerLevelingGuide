package com.hc.javabasis.collection;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ArrayList/Vector 底层分析
 * 感谢 https://crossoverjie.top/JCSprout/#/collections/ArrayList
 * [1] ArrayList 实现 List、RandomAccess 接口。可以插入空数据，也支持随机访问
 * 接下来会实现的简单核心方法
 * Author: hc
 * DATE: 2019/1/28 = 10:11 AM
 */
public class ArrayListCore {
    ArrayList<String> tmp = new ArrayList<>();

    //=========================================================================================
    //================================================ add in 0128 : [2] 最重要的两个属性分别是：elementData 数组和 size 大小, 和 add 方法
    //================================================ 1. 可见在 ArrayList 的数组扩容和指定位置添加数据，非常消耗性能，因此日常使用最好指定大小，减少指定位置的插入数据操作
    //=========================================================================================
    /**
     * 共享空数组实例使用的默认数组
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    public ArrayListCore() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * [2] 最重要的两个属性分别是：elementData 数组和 size 大小, add 方法
     * 1. 包访问权限，简明内部类访问(non-private to simplify nested class access)
     * 2. object 类型方便存储所有对象
     * 3. 使用 transient 关键字，防止被自动序列化
     */
    transient Object[] elementData;

    private int size;

    /**
     * add
     *
     * @param e   element to add
     * @param <E> type
     * @return true, no false
     */
    public <E> boolean add(E e) {
        //扩容检验
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //将插入的值放入尾部，并将 size +1
        elementData[size++] = e;
        return true;
    }


    /**
     * 默认初始尺寸
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 确保容量可用，size 最小不可小于 10
     * 1. 默认构造函数，执行add 操作，会使用默认尺寸 10
     * 2. 传参构造函数，可以跳过默认尺寸的判断，就是你可以初始化 1 的数组
     *
     * @param minCapacity 最小容量
     */
    private void ensureCapacityInternal(int minCapacity) {
        // 默认构造函数初始化的，一般都会这么操作
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            //此时需要add 数据了，得赶紧确认 minCapacity,
            // 原先 minCapacity = 0 + 1， 执行下面语句，变成 10，minCapacity不可少于10
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    /**
     * 1. 结构更改的次数
     * 2. 防止一个迭代器在处理的时候(next、remove、previous、set、add 操作)，其他人也在暗摸摸操作，
     * 3. 未预期的修改，乐观锁，未出现预期数值会抛出 ConcurrentModificationException
     * 4. 提供快速失败行为 (fail-fast behavior)
     */
    protected transient int modCount = 0;

    /**
     * 确保明确容量
     *
     * @param minCapacity 容量
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }


    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;//防止越界
    /**
     * 扩容
     *
     * @param minCapacity 最小容量
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        // 右移相当于一半
        // oldCapacity = 0 , newCapacity = 0
        // oldCapacity = 10 , newCapacity = 10 + 5 = 15
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            //newCapacity 和 minCapacity 取最大
            newCapacity = minCapacity;
        }
        if(newCapacity - MAX_ARRAY_SIZE > 0){
            // 新分配的要越界了，下面方法会返回 Integer.MAX_VALUE
            newCapacity = hugeCapacity(minCapacity);
        }
        //调用 native 层的拷贝数据
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 想要巨大的容量，先给你 Integer.MAX_VALUE - 8，还不够？ 只好都给你了 Integer.MAX_VALUE
     * @param minCapacity 最小容量
     * @return 巨大容量
     */
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0){//overflow 溢出
            throw new OutOfMemoryError();
        }
        //想要巨大的容量，先给你 Integer.MAX_VALUE - 8，还不够？ 只好都给你了 Integer.MAX_VALUE
        int i = (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
        return i;
    }
    //=========================================================================================
    //================================================ end add in 0128 : [2] 最重要的两个属性分别是：elementData 数组和 size 大小, add 方法
    //=========================================================================================

    //=========================================================================================
    //================================================ add in  0128: [3] 序列化
    //=========================================================================================


}
