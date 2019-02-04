package com.hc.kotlintoturial

/** 数据类与密封类
 * [1] 数据类
 * [2] 密封类，专门用来配合 when 语句使用的类
 * Author: hc
 * DATE: 2019/1/25 = 上午9:30
 */

/**
 * [1] 数据类
 * data 关键字会自动从主构造函数中根据声明的属性自动生成如下函数：
 * - equals()/hashCode()
 * - toString()
 * - componentN() function 对应的属性
 * - copy()
 */
data class Apple(val name: String, val age: Int)

data class Banana(val name: String, val age: Int)
data class Orange(val name: String, val age: Int)

/**
 * [2] 密封类，专门用来配合 when 语句使用的类
 * 类似 enum,但是可以携带数据
 */
sealed class UIStatus {
    //静态类
    object Show : UIStatus()

    object HIDE : UIStatus()

    class ShowApple(val age: Int) : UIStatus()
    class ShowBanana(val name: String) : UIStatus()
}

fun judgeViewStatus(x: UIStatus) {
    when(x){
        UIStatus.Show -> println("show")
        UIStatus.HIDE -> println("hide")
        is UIStatus.ShowApple -> println(x.age)
        is UIStatus.ShowBanana -> println(x.name)
    }
}

fun main(args: Array<String>) {
    val apple = Apple("myApple", 17)
    val apple2 = Banana("Banana", 127)
    val apple3 = Orange("myApOrangeple", 37)
    println(apple)
    println(apple2)
    println(apple3)
    //使用 component0 component1
    val (name, age) = apple
    println("name:$name,age:$age")

    //[2]
    judgeViewStatus(UIStatus.Show)
    judgeViewStatus(UIStatus.HIDE)
    judgeViewStatus(UIStatus.ShowApple(12))
    judgeViewStatus(UIStatus.ShowBanana("BANANA"))
}