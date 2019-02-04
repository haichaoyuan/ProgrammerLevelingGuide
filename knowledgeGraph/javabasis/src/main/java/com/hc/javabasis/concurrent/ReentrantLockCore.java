package com.hc.javabasis.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * [1]
 * 1. 使用synchronized 来做同步处理时，锁的获取和释放都是隐式的，实现的原理是编译器会加上不同的机器指令来实现
 * 2. ReentrantLock 是一个普通的类，基于 AQS 来实现
 * Author: hc
 * DATE: 2019/2/3 = 8:30 PM
 */
public class ReentrantLockCore {
    ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * [2] 公平锁和非公平锁
     */

}
