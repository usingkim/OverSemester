package com.example.lab02_all
import java.util.Scanner

fun main()
{
    var num = 0
    var pairList: MutableList<Int> = mutableListOf()

    var sc: Scanner = Scanner(System.`in`)
    num = sc.nextLine().toInt()

    for (i: Int in 0..num - 1 step(1))
    {
        var temp = sc.nextLine().toInt()
        pairList.add(temp)
    }

    for (i: Int in 0..num - 1 step(1)){
        print("Pairs for " + pairList[i].toString() + " : ")
        for (j: Int in 1..pairList[i]) {
            if (j >= pairList[i] - j) break
            if (j > 1) print(", ")
            print("(" + j.toString() + " " + (pairList[i] - j).toString() + ")")
        }
        println()
    }
}