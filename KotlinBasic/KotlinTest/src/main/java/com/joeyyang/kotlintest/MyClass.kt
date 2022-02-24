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
    研究到 https://kotlinlang.org/docs/basic-syntax.html#nullable-values-and-null-checks
    Using lambda expressions to filter and map collections:
 */

const val F001_FUNCTION_BODY_EXPRESSION = 1
const val F002_IF_EXPRESSION_01 = 2
const val F003_LOOP_EXPRESSION_01 = 3
const val F004_IN_EXPRESSION_01 = 4
fun main() {
    var testNumber = F004_IN_EXPRESSION_01

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

    val list = listOf("a", "b", "c") //listOf可點開看原始code
    if(x in 1..y+1)
        println("fits in range")

    if(-1 !in 0..list.lastIndex)
        println("-1 is out of range")

    if(list.size !in list.indices)
        println("list size is out of valid list indices range, too")

    for(x in 1..10 step 2)
        print(x)
    println()
    for(x in 9 downTo 0 step 3)
        print(x)

    for(item in list)
        println(item)

    val items = setOf("apple", "banana", "kiwifruit")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
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
