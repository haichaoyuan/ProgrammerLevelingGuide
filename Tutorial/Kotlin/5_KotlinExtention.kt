package com.hc.delaydownload

/** Kotlin 扩展
 * [1] 构造函数,增加var 就是变量，不增加var 就是参数
 * [2] 扩展函数
 * [3] 伴生对象的扩展函数
 * [4] 伴生对象的扩展变量
 * [5] 对象的扩展变量
 * Author: hc
 * DATE: 2019/1/24 = 下午5:21
 */

/**
 * [1] 构造函数，增加var 就是变量，不增加var 就是参数
 */
class User constructor(var name:String){
    companion object{}
}

/**
 * [2] 扩展函数
 * 1. 已有类中添加新的方法，不好对原类做修改
 * 2. 若扩展的方法和原类相同，优先使用原类的方法
 */
fun User.paint(){
    println("用户名： $name")
}

/**
 * [3] 伴生对象的扩展函数
 * 对应 Java 的静态方法
 */
fun User.Companion.foo(){
    println("伴生对象的扩展函数")
}

/**
 * [4] 伴生对象的扩展变量
 */
val User.Companion.no:Int
    get() = 10

/**
 * [5] 对象的扩展变量
 */
val User.no2:Int
    get() = 10

fun main(args: Array<String>) {
    var user = User("hc")
    user.name
    user.paint()

    User.foo()
    println("User.no : ${User.no}")
}