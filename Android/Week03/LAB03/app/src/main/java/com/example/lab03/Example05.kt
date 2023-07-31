package com.example.lab03

import java.util.*

class virus{
    val sc: Scanner = Scanner(System.`in`)

    var n: Int = 0
    var m: Int = 0
    var arr = Array(n,{IntArray(m)})
    var spread_arr = Array(n,{IntArray(m)})
    var max = 0

    public fun input(){
        n = sc.nextInt()
        m = sc.nextInt()

        arr = Array(n,{IntArray(m)})

        for(i in 0..n-1){                     
            for(j in 0..m-1){
                arr[i][j] = sc.nextInt()
            }
        }
    }

    fun createWall(num: Int, count: Int){
        if (count == 3){
            safetyArea()
            var n = num - 1
            arr[n/ m][n % m] = 0
            return
        }

        for (i in num..n * m -1){
            if (arr[i / m][i % m] != 0) continue
            arr[i / m][i % m] = 1
            createWall(i + 1, count+1)
            arr[i / m][i % m] = 0
        }
    }

    fun safetyArea(): Int{
        spread_arr = Array(n,{IntArray(m)})
        for (i in 0..n-1){
            for (j in 0..m-1){
                spread_arr[i][j] = arr[i][j]
            }
        }

        for (i in 0..n-1){
            for (j in 0..m-1){
                if(spread_arr[i][j] == 2) {
                    // 오른쪽
                    if(j < m - 1) spreadVirus(i, j+1)
                    // 아래
                    if(i < n - 1) spreadVirus(i+1, j)
                    // 왼쪽
                    if (j > 0) spreadVirus(i, j-1)
                    // 위쪽
                    if (i > 0) spreadVirus(i-1, j)
                }
            }
        }

        var count = 0
        for (i in 0..n-1){
            for (j in 0..m-1){
                if(spread_arr[i][j] == 0) count += 1
            }
        }

        if (max < count) max = count
        return count
    }

    fun spreadVirus(i: Int, j: Int){
        if (spread_arr[i][j] != 0) return
        else spread_arr[i][j] = 2
        //println("$i $j")
        // 오른쪽
        if(j < m - 1) spreadVirus(i, j+1)
        // 아래
        if(i < n - 1) spreadVirus(i+1, j)
        // 왼쪽
        if (j > 0) spreadVirus(i, j-1)
        // 위쪽
        if (i > 0) spreadVirus(i-1, j)
    }

    fun printWall(){
        for (i in 0..n-1){
            for (j in 0..m-1){
                print(spread_arr[i][j].toString() + " ")
            }
            println()
        }
        println("===============")
    }

    fun printMax(){
        println("$max")
    }
}



fun main(){

    var v = virus()
    v.input()
    v.createWall(0, 0)
    v.printMax()
}

