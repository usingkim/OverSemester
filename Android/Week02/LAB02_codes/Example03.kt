package com.example.lab02_all

fun Year(number: Int): Int{

    // else if 잘 이용하자 !!
    if (number % 400 == 0) return 1
    else if (number % 100 == 0) return 0
    else if (number % 4 == 0) return 1

    return 0
}

fun printer(number: Int){
    if (number == 1) println("윤년이 맞습니다.")
    else if (number == 0) println("윤년 아닙니다.")
}

fun main(){
    println("2000년은 윤년 일까?")
    printer(Year(2000))

    println("1900년은 윤년 일까?")
    printer(Year(1900))

    println("2020년은 윤년 일까?")
    printer(Year(2020))

    println("2013년은 윤년 일까?")
    printer(Year(2013))
}