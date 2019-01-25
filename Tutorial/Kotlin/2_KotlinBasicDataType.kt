package com.hc.delaydownload

/** 基本数据类型
 * [toc]
 * [1]. 基本数据类型
 * Byte 8位, Short 16位，Int 32位，Long 64位，Float 32位，Double 64位
 * [2]. 字面常量
 * [3]. 比较两个数字
 * [4]. 基础类型转换
 * [5]. 数组使用
 * [6]. 字符串使用
 *
 * Author: hc
 * DATE: 2019/1/24 = 下午1:29
 */

/**
 * [2]. 字面常量
 * 使用下划线使数字常量更可读
 */
var creditCardNumber = 1234_5678_8901

/**
 * [3]. 比较两个数字
 * 三个等号 === 比较对象地址，两个等号比较值大小，与预期相符
 */
fun compareTwoNumber(x: Int, y: Int) {
    var b = x == y
    var c = x === y
    println("b:$b,d:$c ")
}

/**
 * [4]. 基础类型转换
 * 提供转换方法来互相转换
 * toByte():Byte
 * toShort():Short
 * toInt():Int
 * toLong():Long
 * toFloat():Float
 * toDouble():Double
 */
fun basicTypeTranform() {
    val intT: Int = 1234
    val longT: Long = intT.toLong()
}

/**
 * [5]. 数组使用
 */
fun arrayFun() {
    // [1,2,3]
    val a = arrayOf(1, 2, 3)
    for (tmp in a) {
        print(tmp)
        print(",")
    }
    // b size 为3，值是索引的两倍
    val b = Array(3) { i -> (i * 2) }
    for (tmp in b) {
        print(tmp)
        print(",")
    }
}

/**
 * [6]. 字符串使用
 */
fun stringFun() {
    val str = "这是一个字符串"
    for (c in str){
        print("$c,")
    }
    // trimIndent 去除空格
    val txt = """
        多行字符串
        多行字符
        多字符
    """.trimIndent()
    print(txt)
}

fun main(args: Array<String>) {
//    compareTwoNumber(1234, 1000 + 234)
//    arrayFun()
    stringFun()
}