package com.example.lab02_all
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var N = readLine().toInt() // 일 할 수 있는 날짜

    var T = IntArray(N + 10) // 시간 저장 배열
    var P = IntArray(N + 10) // 각 날짜별 금액 저장 배열
    var dp = IntArray(N + 10) // 각 날짜별 최대 누적 금액

    var max = 0

    /* code */

    for(i in 0 until N) {
        var data = readLine().trim().split(' ')
        T[i] = data[0].toInt()
        P[i] = data[1].toInt()
    }

    for(i in 0 until N){
        if (i - T[i] < 0)
            dp[i] = P[i]
        else
            dp[i] = P[i - T[i]] + P[i]
        if (dp[i] > max)
            max = dp[i]
    }

    println(max)
    close()
}