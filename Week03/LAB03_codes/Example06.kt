package com.example.lab03

import java.lang.Exception
import java.lang.NumberFormatException
import java.util.*

class MenuInvalidException(message: String): Exception(message)
val sc: Scanner = Scanner(System.`in`)
var coin: Int = 0

fun printMenuScript(){
    println("=========MENU=========")
    println("| (1) 참깨라면 - 1,000원 |")
    println("| (2) 햄버거  - 1,500원 |")
    println("| (3) 콜라    -   800원 |")
    println("| (4) 핫바    - 1,200원 |")
    println("| (5) 초코우유 - 1,500원 |")
}

fun getMenu(): String?{
    var menuName: String? = null
    while(true) {
        printMenuScript()
        println("Choose menu:")
        val menuNum = sc.nextLine()

        when (menuNum) {
            "1" -> menuName = "참깨라면"
            "2" -> menuName = "햄버거"
            "3" -> menuName = "콜라"
            "4" -> menuName = "핫바"
            "5" -> menuName = "초코우유"
        }

        try {
            menuName ?: throw MenuInvalidException("아무것도 선택하지 않았습니다.\n다시 선택해주세요.")
            when (menuNum){
                "1" -> println("${menuName}이 선택되었습니다.")
                else -> println("${menuName}가 선택되었습니다.")
            }
            break
        } catch (e: MenuInvalidException) {
            println(e.message)
        }
    }
    return menuName
}

fun getCoin(){
    while (true) {
        println("Insert Coin")
        try {
            coin = sc.nextLine().toInt()
            break
        } catch (e: NumberFormatException) {
            println("돈을 넣지 않았습니다.\n다시 돈을 넣어주세요.")
        }
    }

    println(coin.toString() + " 원을 넣었습니다.")
}

fun getPrice(menu: String?): Int{
    var price: Int = 0
    when (menu){
        "참깨라면" -> price = 1000
        "햄버거" -> price = 1500
        "콜라" -> price = 800
        "핫바" -> price = 1200
        "초코우유" -> price = 1500
    }
    return price
}

fun getChange(menu: String?){
    var change: Int = coin - getPrice(menu)
    if (change >= 0) println("감사합니다. 잔돈 반환 : " + change.toString())
    else println("현금이 부족합니다.")
}

fun main(){

    var menu = getMenu()
    getCoin()
    getChange(menu)
}