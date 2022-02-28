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
    5. Basic syntax [Basic syntax說明] (範例ready)
    研究到 https://kotlinlang.org/docs/basic-syntax.html#nullable-values-and-null-checks
    Using lambda expressions to filter and map collections:
 */

const val F001_FUNCTION_BODY_EXPRESSION = 1
const val F002_IF_EXPRESSION_01 = 2
const val F003_LOOP_EXPRESSION_01 = 3
const val F004_IN_EXPRESSION_01 = 4
const val F005_COLLECTIONS_EXPRESSION_01 = 5
const val F006_NULLABLE_EXPRESSION_01 = 6
const val F007_IS_EXPRESSION_01 = 7

fun main() {
    var testNumber = F007_IS_EXPRESSION_01

    when(testNumber) {
        F001_FUNCTION_BODY_EXPRESSION -> funBodyExpression()
        F002_IF_EXPRESSION_01 -> funIf001()
        F003_LOOP_EXPRESSION_01 -> funLoop001()
        F004_IN_EXPRESSION_01 -> funIn001()
        F005_COLLECTIONS_EXPRESSION_01 -> funCollection001()
        F006_NULLABLE_EXPRESSION_01 -> funNullable001()
        F007_IS_EXPRESSION_01 -> funIs001()
        else -> print("None")
    }
    print("Hello world!")
}

fun funIs001() {
    fun printLength(obj: Any) { //重要, function裡還有function
        println("Getting the length of '$obj'. Result: ${getStringLengthCase7(obj)}")
    }
    printLength("Incomprehensibilities")
    printLength(1000)
    printLength(listOf(Any()))  //重要
}

fun getStringLengthCase7(obj: Any): Int? {  //重要,回傳值可以是null,所以要用 ?
    /* //寫法1
    if(obj is String) {
        return obj.length
    }
    return null
     */

    //寫法2
    if(obj !is String) return null

    return obj.length
}

fun funNullable001() {
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("6", "b")
}

fun parseIntCase6(str: String): Int? {
    return str.toIntOrNull()    //重要
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseIntCase6(arg1)
    val y = parseIntCase6(arg2)

    if(x != null && y != null) {
        println(x * y)
    } else {
        println("'$arg1' or '$arg2' is not a number")   //重要
    }
}

fun funCollection001() {
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach { println(it) }
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
