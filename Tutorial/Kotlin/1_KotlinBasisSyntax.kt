/**
 * toc 基本语法
 * [1]. 包声明
 * [2]. 默认导包
 * [3] 函数定义
 * [4] 可变长参数函数，vararg
 * [5] lambda 匿名函数
 * [6] 定义常量与变量,val,var
 * [7] 字符串模板
 * [8] NULL 检查机制
 * [9] is 关键字相当于 instance , 类型检查
 * [10] 区间，和Python 类似，很赞的功能 .. 操作符
 * * Author: hc
 * DATE: 2019/1/23 = 下午4:22
 */
/**
 * [1]. 包声明。
 * 1. Kotlin 源文件不需要相匹配的目录和包，源文件可以放在任何文件目录
 * 2. 如果没有指定包，默认 default 包
 */
package com.hc.delaydownload

/**
 * [2]. 怎么啥包都没导入？默认导入
 * 1. 以下包会被默认导入到每个 kotlin 文件中
 * - kotlin.annotation.*
 * - kotlin.collections.*
 * - kotlin.comparisons.*
 * - kotlin.io.*
 * - kotlin.ranges.*
 * - kotlin.text.*
 * - kotlin,sequences.*
 */


/**
 * [3] 函数定义，参数格式为：参数：类型
 * DATE: 2019/1/23 = 下午4:22
 */
fun sum(a: Int, b: Int): Int {
    return a + b
}

/** [3.2] 函数的简写
 */
public fun sum2(x: Int, y: Int): Int = x + y

/** [3.3] 无返回，unit ,相当于 void,可省略不写
 */
public fun sum3(x: Int, y: Int): Unit {
    println(x + y)
}

/**
 * [4] 可变长参数函数
 */
fun vars(vararg x: Int) {
    for (xx in x) {
        print(xx)
        print(",")
    }
}

fun main(args: Array<String>) {
    // [3] 函数定义
    println(sum(1, 2))
    println(sum2(2, 2))
    sum3(2, 3)
    // [4] 可变长参数函数
    vars(1, 2, 3, 4, 5, 6)


    // [5] lambda 匿名函数
    println()
    val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
    println(sumLambda(10, 20))


    // [6] 定义常量与变量,val,var
    // var 关键字，可变变量定义
    var a: Int = 1
    // val 关键字，不可变变量，相当于 java 的 final
    val b = 1
    var c = a + b


    // [7] 字符串模板
    // $ 表示一个变量名或者变量值， 与 PHP类似
    // $varname 表示变量值
    // ${varname.fun()} 表示变量的方法返回值
    var str = "110"
    val str2 = "a is $str"
    println(str2)

    str = "119"
    val str3 = "${str2.replace("is", "was")},but now is $str"
    println(str3)


    // [8] NULL 检查机制
    var age: String? = null
//    age = "123"
    stringIsNull(age)

    // [9] is 关键字相当于 instance , 类型检查
    println(isString("123321"))

    // [10] 区间，和Python 类似，很赞的功能 .. 操作符
    sectionUser()

}

/** [8] NULL 检查机制
 *  1. 字段后面加！！，抛出空异常
 *  2. 字段后面加 ？ ，不做处理
 */
fun stringIsNull(age: String?) {
    // 会抛出 kotlin.KotlinNullPointerException
//    val age2 = age!!.toInt()
    // 为null 不做处理，返回 null
    val age3 = age?.toInt()
    // 为null 则返回 -1
    val age4 = age?.toInt() ?: -1

    println(age3)
}

/**
 * [9] is 关键字相当于 instance , 类型检查
 * 做过类型判断之后，系统自动转换为 String 类型，很赞
 */
fun isString(str: Any): Int? {
    if (str is String) {
        // 做过类型判断之后，系统自动转换为 String 类型，很赞
        return str.length
    }
    return null
}

/**
 * [10] 区间，和Python 类似，很赞的功能 .. 操作符
 * in, downTo, step
 */
fun sectionUser() {
    // 输出 1，2，3，4
    for ( i in 1..4) print(i)
    // 输出 1，3
    for (i in 1..4 step 2) print(i)
    // 输出 4 ，2
    for (i in 4 downTo 1 step 2) print(i)
}
