package com.hc.kotlintoturial

/** 类和对象
 * Kotlin 类包含：构造函数和初始化代码块、函数、属性、内部类、对象声明
 * [1] 类定义
 * [2] getter 和 setter
 * [3] 主构造器参数的在 init 代码块的使用
 * [4] 次构造函数
 * [5] 类不可实例化
 * Author: hc
 * DATE: 2019/1/24 = 下午4:11
 */

/**
 * [1] 类定义
 * 1. class 关键字 + 类名
 * 2. 类可以有一个主构造器，主构造器可以在初始化代码使用
 * 3. 如果主构造器没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略。
 */
class ClassA constructor(firstName:String){
    /**
     * [2] 变量的getter 和 setter
     * 1. 声明时可以使用构造函数的参数
     * 2. set、get 关键字里不可使用具体变量，只可使用 field,因为kotlin 会把 no = value 编译器会编译成调用 setter 方法，造成循环
     */
    var mFirstName: String? = null
        get() = field?.toUpperCase()
        set

    var no: Int = firstName.length
        get() = field
        private set(value) {
            field = if(value < 10) {
                value
            } else {
                -1
            }
        }

    var height:Float = 125.8F
        private set

    /**
     * [3] 主构造器参数的在 init 代码块的使用
     */
    init {
        println("init")
        mFirstName = firstName
    }


    /**
     * [4] 次构造函数
     * 主构造函数使用 this 关键字，init 只会在主构造函数执行
     */
    constructor(firstName: String, no:Int):this(firstName){
        println("constructor(firstName: String, no:Int)")
        this.no = no
    }
    /**
     * 成员函数
     */
    fun foo() {
        print("Foo")
    }

}

/**
 * [5] 类不可实例化
 * 在主构建函数增加 private 关键字
 */
class ClassB private constructor(){

}


/**
 * [6] 类的修饰符
 * classModifier== 类属性修饰符
 * abstract: 抽象类
 * final : 类不可继承，默认属性。与java 不同
 * enum :枚举类
 * open : 类可继承，类默认是final
 * annotation:注解类
 *
 * accessModifier== 访问权限修饰符
 * private : 仅在同一个文件中可见
 * protected : 同个文件夹中或子类可见
 * public : 所有调用的地方都可见
 * internal : 同一个模块中可见，java 的 package
 *
 */

/**
 * [7] 匿名内部类
 */
class ClassC{
    fun setInterface(test: TestInterface){}
}
interface TestInterface{
    fun test()
}

/**
 * [8] 内部类
 */
class Outer {
    //同文件可见
    private val bar :Int = 1

    inner class Inner {
        fun foo() = bar //访问外部类成员
    }
}

/**
 * [9] 嵌套类
 */
class Outer2 {
    private val bar:Int = 1
    class Nested {
        fun foo() = 2
    }
}

fun main(args: Array<String>) {
    val classA = ClassA("firstName", 123)
    classA.foo()
    classA.mFirstName = "1"
    //由于设置 private ,此处会报错
//    val classB = ClassB()

    //[7] 匿名内部类的实现
    var classC = ClassC()
    classC.setInterface(object : TestInterface {
        override fun test() {
            print("匿名内部类")
        }
    })
    // [8] 内部类 [9]嵌套类
    var outter = Outer().Inner()
    var outer2 = Outer2.Nested()
}