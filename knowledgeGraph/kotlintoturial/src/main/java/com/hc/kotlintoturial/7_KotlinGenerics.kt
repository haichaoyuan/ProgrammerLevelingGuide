package com.hc.kotlintoturial

/** 泛型
 * [1] 看！泛型
 * [2] 泛型方法
 * [3] 泛型配合 is 关键字
 * [4] 生产者 out 关键字，用作输出
 * [5] 消费者 int 关键字，用作输入
 * [6] 星号投射的例子
 * Author: hc
 * DATE: 2019/1/25 = 下午2:15
 */

/**
 * [1] 看！泛型
 */
data class Box<T>(var t: T)

/**
 * [2] 泛型方法
 * 使用的方式上，看不出类和方法
 */
fun <T> BoxFun(value: T): Box<T> = Box(value)

/**
 * [3] 泛型配合 is 关键字
 * 泛型的最棒使用地方
 */
fun <T> BoxJudge(value: T): Int {
    when (value) {
        is Int -> {
            println("this is int")
            return 1
        }
        is Long -> {
            println("this is Long")
            return 2
        }
        is String -> {
            println("this is String")
            return 3
        }
        else -> {
            println("other")
            return -1
        }

    }
}

/**
 * [4] 生产者 out 关键字，用作输出
 */
class Runoob<out A>(val a: A) {
    fun foo(): A {
        return a
    }
}

/**
 * [5] 消费者 int 关键字，用作输入
 */
class Runoob2<in A> {
    fun foo(a: A) {

    }
}

class Runoob3<in A, out B> {
    fun foo(a: A): B? {
        return null
    }
}

/**
 * [6] 星号投射的例子
 */
class Runoob4<T>(val t:T, val t2:T, val t3:T)

fun main(args: Array<String>) {
    //[1] 看！泛型
    val box: Box<Int> = Box(1)
    //类型推断
    val box2 = Box("String")
    println("box.t: ${box.t}")
    println("box2.t: ${box2.t}")

    //[2] 泛型方法
    val boxFun = BoxFun(2)
    val boxFun2 = BoxFun("BoxFun")
    println("boxFun.t: ${boxFun.t}")
    println("boxFun2.t: ${boxFun2.t}")

    //[2].2 泛型方法
    BoxJudge(1)
    BoxJudge(1L)
    BoxJudge("ok")
    BoxJudge(true)

    //[6] 星号投射
    val a1: Runoob4<*> = Runoob4(12, "apple", Runoob2<Int>())
    println(a1.t)
    println(a1.t2)
    println(a1.t3)
}