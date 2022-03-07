package com.joeyyang.kotlintest

/*
    Lambda test environment.
    Reference: https://kotlinlang.org/docs/lambdas.html
    Version:

    看到Function types
 */

const val F001_HIGHER_ORDER_FUNCTION = 1
const val F002_HIGHER_ORDER_FUNCTION_EXAMPLE = 2
fun main() {
    var testNumber = F002_HIGHER_ORDER_FUNCTION_EXAMPLE

    when(testNumber) {
        F001_HIGHER_ORDER_FUNCTION -> fun001HigherOrderFunction()
        F002_HIGHER_ORDER_FUNCTION_EXAMPLE ->  fun001HigherOrderFunctionEx()


        else -> print("None")
    }
    print("Hello world!2")
}

fun fun001HigherOrderFunctionEx() {
    val items = listOf(1, 2, 3, 4, 5)

    //Lambdas是被block在 {} 區域裡
    items.fold(0, {
        // 當 lambda 有變數,變數會先寫,後來接個 ->
        acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // 在 lambda 的最後一個 expression, 是被視為 return 值
        result
    })

    // 在 lambda 的變數型態若可以被推論出來,即是可選(Optional)的,
    val joinedToString = items.fold("Elements", { acc, i -> acc + " " + i })
      //這表示使用fold的是Int的Iterable,內有二個參數,參數1的型態是字串"Elements", 參數2的function有二個變數,變數1 (acc)會先是空字串,變數2(i)就是items list的各項內容

    // Function references也能被用在 higher-order function calls
    val product = items.fold(1, Int::times) //times 是乘法的意思

    println("joinedToString = $joinedToString")
    println("product = $product")
}

fun fun001HigherOrderFunction() {
    // Just the Collection.fold code
    fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
    ): R {
        var accumulator: R = initial
        for(element: T in this) {
            accumulator = combine(accumulator, element) //combine 的使用就和遞迴一樣的意思
        }
        return accumulator
    }
    /*
    在上面的code, combine變數有 function type (R, T) ->, 所以他接受一個有二個變數的function,
    二變數型態是 R 和 T, 且回傳值的 type是R. 它們是在loop裡面被 invoked(喚起)的, 且回傳值是被分配到accumulator
     */
}
