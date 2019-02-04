package com.hc.javabasis.collection;

import java.util.Vector;

/** Vector 也是实现List 接口，底层数据结构与 ArrayList 类似，不过在调用 add 方法使用 synchronized 进行同步，开销较大
 * Author: hc
 * DATE: 2019/1/28 = 4:26 PM
 */
public class VectorCore {
    Vector<String> vector = new Vector<>();

    transient Object[] elementData;

    private int size;
    private int modCount;

    /** [1] vector 的 add 方法，可看出与 ArrayList 的 add 方法类似，只是多了 synchronized 关键字用作同步
     * @param e
     * @param <E>
     * @return
     */
    public synchronized <E> boolean add(E e){
        modCount++;
        ensureCapacityHelper(size + 1);
        elementData[size ++] = e;
        return true;
    }

    /** ensureCapacityHelper
     * @param i  ensureCapacityHelper
     */
    private void ensureCapacityHelper(int i) {
        //...
    }
}
