package com.hc.kotlintoturial

/** 对象表达式和对象声明
 * [1] 对象表达式, 匿名接口实现对象
 * [2] 对象表达式2, 匿名可继承类、接口的实现类，
 * [3] 简单匿名类
 * [4] 伴生对象
 * Author: hc
 * DATE: 2019/1/25 = 下午3:20
 */

/**
 * [1] 对象表达式
 * 匿名接口实现对象
 */
//window.addMouseListener(object : MouseAdapter(){
//    override fun mouseClicked(e:MouseEvent){
//
//    }
//})


/**
 * [2] 对象表达式2
 * 匿名可继承类、接口的实现类，
 */
//用作继承
open class C(x:Int){
    public open val y:Int =x
}
//用作接口实现
interface IC {
    fun IC()
}
//继承类和接口
val cic: C = object : C(1), IC {
    override var y = 15
    override fun IC() {
    }

}

/**
 * [4] 伴生对象
 * 1. 一个雷只能声明一个内部关联的对象，即关键字 companion 只能用一次
 */
class MyClass {
    companion object {
        fun create(): MyClass = MyClass()
    }
}

fun main(args: Array<String>) {
    //[3] 简单匿名类
    val site = object {
        var name = "菜鸟"
        var level = 18
    }
    println(site.name)

    //[4] 伴生对象
    var create = MyClass.create()
}