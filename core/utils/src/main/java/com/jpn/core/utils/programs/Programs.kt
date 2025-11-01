package com.jpn.core.utils.programs

import kotlin.system.measureNanoTime

/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */
enum class COLOR {
    RED,
    GREEN,
    BLUE
}

const val YELLOW = "Yellow"
const val BLACK = "Black"

fun insertionSort(arr: IntArray) {
    for (i in 1 until arr.size) {
        val key = arr[i]
        var j = i - 1

        // Shift elements greater than key to the right
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }

        // Insert the key
        arr[j + 1] = key

        //println("After Pass $i: ${arr.joinToString()}")
    }
}

fun bubbleSort(arr: IntArray) {
    val n = arr.size
    //println("Size ${n} I end ${n-1}")
    for (i in 0 until n - 1) {
        //println("Pass ${i + 1}:  J end: ${n - i - 1}")
        for (j in 0 until n - i - 1) {
            // println("Compare ${j}:")
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
            //println("   Step ${j+1}: ${arr.joinToString()}")
        }
        //println("After Pass ${i + 1}: ${arr.joinToString()}")
        //println("------------")
    }
}

fun mergeSort(arr: IntArray, depth: Int = 0): IntArray {
    if (arr.size <= 1) {
        println(" doing arr.size ${arr.size}  ${arr.toList()}")
        return arr
    }

    val mid = arr.size / 2
    val left = arr.sliceArray(0 until mid)
    val right = arr.sliceArray(mid until arr.size)

    println("${"-".repeat(depth)} Splitting: ${arr.toList()} -> Left: ${left.toList()}, Right: ${right.toList()}")
    println(" doing mergeLeft")
    val sortedLeft = mergeSort(left, depth + 1)
    println(" doing mergeright sortedLeft: ${sortedLeft.toList()}")
    val sortedRight = mergeSort(right, depth + 1)
    println(" doing merge sortedRight: ${sortedRight.toList()}")
    val merged = merge(sortedLeft, sortedRight)
    println("${"-".repeat(depth)} Merged: ${merged.toList()}")
    return merged
}

fun merge(left: IntArray, right: IntArray): IntArray {
    var i = 0
    var j = 0
    val merged = mutableListOf<Int>()
    println(" doing merge left: ${left.size} right: ${right.size}")
    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) {
            println(" doing merge left add ${merged.toList()}")
            merged.add(left[i])
            i++
        } else {
            merged.add(right[j])
            println(" doing merge right add ${merged.toList()}")
            j++
        }
    }

    while (i < left.size) {
        merged.add(left[i])
        println(" doing merge Outer left add ${merged.toList()}")
        i++
    }
    while (j < right.size) {
        merged.add(right[j])
        println(" doing merge Outer right add ${merged.toList()}")
        j++
    }

    return merged.toIntArray()
}


fun printNumbers(n: Int) {
    for (i in n downTo 1) {
        //print("$i ")
        for (k in n downTo 1) {
            for (j in 1..i) {
                print("$k ")
            }
        }
        println(" ")
    }
}

fun doMulitplication(n: Int): ArrayList<Int> {
    val list: ArrayList<Int> = arrayListOf()
    for (i in 1..10) {
        list.add(n * i)
    }
    return list
}

fun secondLargest(arr: IntArray): Int {
    var secondlargest = -1
    if (arr.size == 1) return secondlargest
    var largest = arr[0]

    for (i in 1 until arr.size) {
        if (largest < arr[i]) {
            secondlargest = largest
            largest = arr[i]
        }

        if (secondlargest < arr[i] && largest > arr[i]) {
            secondlargest = arr[i]
        }
    }
    return secondlargest
}

fun kthSmallest(arr: IntArray, k: Int): Int {
    if (arr.size < k) return -1

    for (i in 0 until arr.size) {
        for (j in 0 until arr.size - i - 1) {
            if (arr[j] > arr[j + 1]) {
                arr[j] = arr[j] + arr[j + 1]
                arr[j + 1] = arr[j] - arr[j + 1]
                arr[j] = arr[j] - arr[j + 1]
            }

        }
    }
    return arr[k - 1]
}

fun findOccurance(arr: IntArray, target: Int): Int {
    var count = 0
    if (arr.size == 0) return count

    for (i in 0 until arr.size) {
        if (arr[i] == target)
            count++
    }
    return count
}

fun missingNumber(array: IntArray): Int {
    var miss = 2
    var found: Boolean = false
    val n = array.size - 1
    // println("n : $n  array[0]: ${array[0]}")
    if (n == 0 && array[0] == 1) return miss
    if (n == 0 && array[0] == 2) return 1
    println("initial condition broken ${n + 1}")
    for (k in 1..n) {
        found = false
        println("k  ${k}")
        for (i in 0..n) {
            println("i  ${i} arr: ${array[i]} k: ${k} ")
            if (array[i] == k) {
                found = true
                //break
            }
        }
        if (!found) {
            miss = k
            println("miss  ${miss}")
            break;
        }
    }

    return miss
}

fun main() {

    printNumbers(5)
    val list = listOf(12, 23, 34, 45)
    val unsort = intArrayOf(34, 56, 23, 12, 35, 56, 78, 23, 8, 99, 47)
    val unsort2 = intArrayOf(1, 2, 3, 4, 5, 6)
    val unsort3 = intArrayOf(6, 5, 4, 3, 2, 1)
    val unsort4 = IntArray(590) { 590 - it }
    val timeTaken = measureNanoTime {
        mergeSort(unsort3)
    }

    val timeTakenBubble = measureNanoTime {
        bubbleSort(unsort4)
    }
    println("------------ Time taken: ${timeTaken / 1_000_000.0} ms  bubble: ${timeTakenBubble / 1_000_000.0} ms")


    val color = COLOR.RED
    val text = when (color) {
        COLOR.RED -> "RED"
        COLOR.GREEN -> "GREEN"
        COLOR.BLUE -> "BLUE"
        else -> ""
    }
    println("Hello, world!!! YOU Choosed : " + text)
    secondFn()
    val sum = sum(4, 5, { a, b -> a + b })
    println("sum: " + sum)

    val multi = { a: Int, b: Int, c: Int -> a * b * c }
    val outs = multi(5, 6, 7)
    println("multi: " + outs)
    for (i in 1..7 step 3) {
        println("i = $i")
    }

    for (i in 5 downTo 1)
        print("$i ")


}


fun sum(a: Int, b: Int, opera: (Int, Int) -> Int): Int {
    return opera(a, b)
}

fun secondFn() {
    val color = YELLOW
    val text = when (color) {
        YELLOW -> "YELLOW"
        BLACK -> "BLACK"
        else -> "NO"

    }
    println("Second, YOU Choosed : " + text)
}

data class Dob(val date: Int, val month: Int, val year: Int)

data class Customer(val name: String, val dob: Dob)

fun main2() {
    println("Hello, world!!!")
    val dob: Dob = Dob(10, 10, 1990)
    val customer1 = Customer("djh", dob = Dob(10, 10, 1990))

    val customer2 = Customer("djh", Dob(27, 12, 1980))
    val customer3 = Customer("djh", Dob(4, 8, 1993))

    val list = arrayListOf(customer1, customer2, customer3)

    list.sortedWith(compareBy<Customer> { it.dob.year }.thenBy { it.dob.month }
        .thenBy { it.dob.date }.thenBy { it.name })

}