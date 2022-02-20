package com.joeyyang.kotlintest

/*
    Kotlin test command environment.
    Environment: https://kotlinlang.org/docs/getting-started.html
    Version: v1.6.10
    1. Home: OK, none
    2. Get started:  OK, none
    3. Kotlin overview:
     a. Kotlin Multiplatform: OK, none
    4. What's new in Kotlin
     a. Kotlin 1.6.0: 有一些,但比較深,晚點研究
    5. Basic syntax [Basic syntax說明]
 */

const val F001_FUNCTION_BODY_EXPRESSION = 1
const val F002_IF_EXPRESSION_01 = 2
const val F003_LOOP_EXPRESSION_01 = 3
const val F004_IN_EXPRESSION_01 = 4
fun main() {
    var testNumber = F003_LOOP_EXPRESSION_01

    when(testNumber) {
        F001_FUNCTION_BODY_EXPRESSION -> funBodyExpression()
        F002_IF_EXPRESSION_01 -> funIf001()
        F003_LOOP_EXPRESSION_01 -> funLoop001()
        F004_IN_EXPRESSION_01 -> funIn001()
        else -> print("None")
    }
    print("Hello world!")
}

fun funIn001() {
    val x = 10
    val y = 9

    val list = listOf("a", "b", "c")
    if(x in 1..y+1)
        println("fits in range")

    if(-1 !in 0..list.lastIndex)
        println("-1 is out of range")

}

fun funLoop001() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items)
        println(item)

    for(index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

fun funIf001() {
    println("max of 0 and 42 is ${maxOfFunc(0,42)}")
}

// 簡單的if處理可以用一行來表示
fun maxOfFunc(a: Int, b: Int) = if( a > b) a else b

fun funBodyExpression() {
    println("sum of 3 and 5 is ${sumAdd(3,5)}")
}

// Function body能只是個語法, Build時系統會推斷其返回值
fun sumAdd(a: Int, b: Int) = a + b
