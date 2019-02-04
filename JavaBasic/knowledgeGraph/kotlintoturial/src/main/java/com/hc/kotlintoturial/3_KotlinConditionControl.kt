package com.hc.kotlintoturial


/** 条件控制
 *
    [1] IF 表达式
    [2] WHEN 表达式
    [3] FOR 表达式
    [4] WHILE 表达式
    [5] 标签和break
 * Author: hc
 * DATE: 2019/1/24 = 下午3:35
 */

/**
 * [1] IF 表达式
 */
fun ifFun() {
    //传统用法
    val a = 1
    val b = 2
    val c: Int
    if(a > b){
        c = a
    } else {
        c = b
    }
    println(c)
    // 面向方法编程
    val max = if(a >b){
        print("$a > $b")
        a
    } else {
        b
    }
    println(max)
}

/**
 * [2] WHEN 表达式
 * 类似 switch
 */
fun whenFun(x:Int){
    when(x){
        1 -> print("x == 1.")
        2 -> print("x == 2.")
        3,4 -> print("x == 3,4.")
        in 5..10 -> print("x == 5..10.")
        else -> print("x == 其他.")
    }
}

/**
 * [3] FOR 表达式
 */
fun forFun(){
    val item = listOf<String>("apple", "banana", "kiwi")
    for (index in item.indices){
        println("items at $index is ${item[index]}")
    }
}

/**
 * [4] WHILE 表达式
 */
fun whileFun(x:Int){
    var x2 = x
    while (x2 > 0){
        x2--
        println("${x2}")
    }
}

/**
 * [5] 标签和break
 * 使用标签直接跳出多层循环
 */
fun breakLabelFun():Int{
     var num :Int = 0
     a@ for(i in 1..100){
        for (j in 1.. 10){
            num++
            if (i == 99 &&  j == 9){
                break@a
            }
        }
    }
    return num
}

fun main(args: Array<String>) {
//    ifFun()
//    whenFun(1)
//    whenFun(3)
//    whenFun(5)
//    whenFun(100)

    forFun()
    whileFun(10)
    print(breakLabelFun())
}