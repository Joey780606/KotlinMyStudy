package com.joeyyang.kotlintest

import javax.swing.tree.TreeNode

/*
    Inline test environment.
    Reference: https://kotlinlang.org/docs/getting-started.html
    Version: https://kotlinlang.org/docs/inline-functions.html#non-local-returns

    研究到 https://kotlinlang.org/docs/basic-syntax.html#nullable-values-and-null-checks
    Using lambda expressions to filter and map collections:
 */

const val F001_NO_LOCAL_RETURNS = 1
const val F002_INLINED_ALLOW_RETURN = 2
const val F003_INLINED_LOOP_RETURN = 3
const val F004_REIFIED_TYPE_PARAMETER = 4
const val F005_REFLECTION_IN_REIFIED_TYPE = 5
fun main() {
    var testNumber = F005_REFLECTION_IN_REIFIED_TYPE

    when(testNumber) {
        F001_FUNCTION_BODY_EXPRESSION -> fun001NonLocal()
        F002_INLINED_ALLOW_RETURN -> fun002InlinedAllowReturn()
        F003_INLINED_LOOP_RETURN -> fun003InlinedLoopReturn(listOf(1,2,0,3))
        F004_REIFIED_TYPE_PARAMETER -> fun004RefiedTypeParameter()
        F005_REFLECTION_IN_REIFIED_TYPE -> fun005ReflectionInReified()  //不錯的例子

        else -> print("None")
    }
    print("Hello world!2")
}

fun fun005ReflectionInReified() {
    print("005 Start!")
    println(memberOf<StringBuilder>().joinToString("\n"))
    print("005 End!")
}

inline fun <reified T> memberOf() = T::class.members    //這是有reified type parameter,也有reflection

fun fun004RefiedTypeParameter() {
    // 1.先建一個官網的function, TreeNode.findParentOfType ,標記 (004-1)
    // 2.先建一個官網的function,裡面有支援reified(具體化) type parameter ,標記 (004-2)
      // 呼叫方式: myTree.findParentOfType<MyTreeNodeType>()  //即直接放型態就好
}

fun <T> TreeNode.findParentOfType(clazz: Class<T>): T? {  //(004-1)
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T?
}

inline fun <reified T> TreeNode.findParentOfType(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p.parent
    }
    return p as T?
}

fun fun003InlinedLoopReturn(ints: List<Int>): Boolean {
    inlined003 {
        //不過這可能不符合文件說的located in a lambda, but exiting the enclosing function.
        ints.forEach() { //重要,用{}是表示在lambdas,若inlined是一個inline函式,就可以用return.
            if(it == 0) return true
            else println(it)
        }
        return false
    }
    return true
}

inline fun inlined003(block: () -> Unit) {
    block()
    println("hi! 003.")
    //block()
}

fun fun002InlinedAllowReturn() {
    inlined {
        return  //重要,用{}是表示在lambdas,若inlined是一個inline函式,就可以用return.
    }
}

inline fun inlined(block: () -> Unit) { //重要, block是我們可以在這函式裡呼叫的函式名稱，函式不帶參數，沒有回傳值(Unit)
    println("hi!")
}

fun fun001NonLocal() {
    ordinaryFunction {
        //return  //重要,這會有錯誤,用{}是表示在lambdas, 在lambdas裡,不能使用return
    }
}

fun ordinaryFunction(block: () -> Unit) {
    println("hi!")
}
