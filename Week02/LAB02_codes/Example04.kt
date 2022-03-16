package com.example.lab02_all
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun forcheck(a:Int, b:Int ,arr: IntArray){
    print(a.toString() + " " + b.toString() + "] : ")
    for (i in 0..10){
        print(arr[i].toString() + " ")
    }
    println()
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var N = readLine().toInt() // 일 할 수 있는 날짜

    var T = IntArray(N + 10) // 시간 저장 배열
    var P = IntArray(N + 10) // 각 날짜별 금액 저장 배열
    var dp = IntArray(N + 10) // 각 날짜별 최대 누적 금액

    var m = 0

    /* code */

    for(i in 0 until N) {
        var data = readLine().trim().split(' ')
        T[i] = data[0].toInt()
        P[i] = data[1].toInt()
        dp[i] = 0
    }

    for(i in 0 until N){
        if (i + T[i] > N) continue
        dp[i] = max(dp[i], P[i])
        for(j in i + T[i] until N){
            if (j + T[j] > N) continue
            dp[j] = max(dp[i] + P[j], dp[j])
            //forcheck(i + 1, j + 1, dp)
        }
        m = max(m, dp[i])
    }

    //forcheck(0, 0, dp)

    println(m)
    close()
}

/*
10
5 10
5 9
5 8
5 7
5 6
5 10
5 9
5 8
5 7
5 6

7
3 10
5 20
1 10
1 20
2 15
4 40
2 200
 */