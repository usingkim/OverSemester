package com.example.lab03

import java.util.*

/*
4 6
0 0 0 0 0 0
1 0 0 0 0 2
1 1 1 0 0 2
0 0 0 0 0 2

7 7
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
 */

//https://jeongchul.tistory.com/672

class virus{
    val sc: Scanner = Scanner(System.`in`)

    var n: Int = 0
    var m: Int = 0
    var arr = Array(n,{IntArray(m)})
    var map = Array(n,{BooleanArray(m)})

    public fun input(){
        n = sc.nextInt()
        m = sc.nextInt()

        arr = Array(n,{IntArray(m)})
        map = Array(n,{BooleanArray(m)})

        for(i in 0..n-1){
            for(j in 0..m-1){
                arr[i][j] = sc.nextInt()
                map[i][j] = false
            }
        }
    }

    fun createWall(num: Int, count: Int){
        if (count == 3){

        }
        else{
            for (i in num+1..n*m){
                var row = i / m;
                var col = i % m;
                if (map[row][col] == false){
                    createWall(i, count + 1)
                }
            }
        }


    }
}



fun main(){

    var v = virus()
    v.input()

}

