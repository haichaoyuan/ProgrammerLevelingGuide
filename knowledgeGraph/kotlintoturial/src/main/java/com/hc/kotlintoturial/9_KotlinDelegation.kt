package com.hc.kotlintoturial

import kotlin.properties.Delegates

/** 委托 。Kotlin 通过关键字 by 实现委托
 * [1] 类委托
 * [2] 延迟属性 lazy
 * [3] 可观察属性 Observable
 * [4] map 读取属性值
 * [5] Not Null
 * Author: hc
 * DATE: 2019/1/25 = 下午3:36
 */

/**
 * [1] 类委托
 * by 语句表示，将 b 保存在Derived 的对象实例内部，而且编译器会生成继承自Base 接口的所有方法，并将调用转发给 b
 */
//创建接口
interface Base {
    fun print()
}

// 实现此接口的被委托的类
class BaseImpl(val x: Int) : Base {
    override fun print() {
        println(x)
    }
}

// 通过关键字 by 建立委托类
class Derived(b: Base) : Base by b


/**
 * [2] 延迟属性 lazy
 */
val lazyValue: String by lazy {
    println("第一次调用")
    "Hello"
}

/**
 * [3] 可观察属性 Observable
 */
class ObservableUser {
    var name: String by Delegates.observable("初始值") { property, oldValue, newValue ->
        println("旧值：$oldValue, 新值: $newValue")
    }
}

/**
 * [4] map 读取属性值
 * 适用解析json
 */
class Site2(var map: Map<String, Any?>) {
    val name: String by map
    val url: String by map
}


/**
 * [5] Not Null
 * 适用于那些无法再初始化阶段就确定属性值得场合
 */
class ClassForNotNull {
    var notNullBar : String by Delegates.notNull<String>()
}

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived(b).print() //输入10

    //[2] 延迟属性 lazy
    println(lazyValue) //输出 第一次调用 Hello
    println(lazyValue)//输出 Hello

    //[3] 可观察属性 Observable
    var obserableUser: ObservableUser = ObservableUser()
    obserableUser.name = "第一次赋值" // 旧值：初始值, 新值: 第一次赋值
    obserableUser.name = "第二次赋值" //旧值：第一次赋值, 新值: 第二次赋值

    //[4] map 读取属性值
    val site2 = Site2(mapOf(
        "name" to "菜鸟" ,
        "url" to "www.abc.com"
    ))
    println(site2.name) //菜鸟
    println(site2.url) //www.abc.com
    println(site2.map) //{name=菜鸟, url=www.abc.com}

    //[5] Not Null
    val classForNotNull = ClassForNotNull()
//    classForNotNull.notNullBar = null //不可赋值，编辑器会检查
    classForNotNull.notNullBar = "str"
    println(classForNotNull.notNullBar)
}
