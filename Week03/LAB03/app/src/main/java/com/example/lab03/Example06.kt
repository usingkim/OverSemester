package com.example.lab03

import java.lang.Exception
import java.lang.NumberFormatException
import java.util.*

class MenuInvalidException(message: String): Exception(message)

class VendingMachine(){
    var menuName: String? = null

    var coin: Int = 0

    fun getChange(): Int{ // 잔돈 반환하기
        return coin - getPrice()
    }

    fun getCoin(c: Int): Int{ // 돈 넣기
        coin += c
        return coin
    }

    fun getMenu(num: String): String?{ // 메뉴 선택하고 반환하기

        when (num) {
            "1" -> menuName = "참깨라면"
            "2" -> menuName = "햄버거"
            "3" -> menuName = "콜라"
            "4" -> menuName = "핫바"
            "5" -> menuName = "초코우유"
        }
        return menuName
    }

    fun getPrice(): Int{ // 선택한 메뉴의 가격정보 가져오기
        var price: Int = 0
        when (menuName){
            "참깨라면" -> price = 1000
            "햄버거" -> price = 1500
            "콜라" -> price = 800
            "핫바" -> price = 1200
            "초코우유" -> price = 1500
        }
        return price
    }

    fun printMenuScript(){
        println("=========MENU=========")
        println("| (1) 참깨라면 - 1,000원 |")
        println("| (2) 햄버거  - 1,500원 |")
        println("| (3) 콜라    -   800원 |")
        println("| (4) 핫바    - 1,200원 |")
        println("| (5) 초코우유 - 1,500원 |")
    }
}

fun main(){
    val sc: Scanner = Scanner(System.`in`)

    var v = VendingMachine()

    while(true) {
        v.printMenuScript()
        println("Choose menu:")
        val menuNum = sc.nextLine()
        try {
            var menu =
                v.getMenu(menuNum) ?: throw MenuInvalidException("아무것도 선택하지 않았습니다.\n다시 선택해주세요.")
            println("${menu}이 선택되었습니다.")
            break
        } catch (e: MenuInvalidException) {
            println(e.message)
            continue
        }
    }

    var coin: Int
    while (true) {
        println("Insert Coin")
        try {
            coin = sc.nextLine().toInt()
            break
        } catch (e: NumberFormatException) {
            println("돈을 넣지 않았습니다.\n다시 돈을 넣어주세요.")
        }
    }

    println(v.getCoin(coin).toString() + " 원을 넣었습니다.")

    var change = v.getChange()
    if (change >= 0) println("감사합니다. 잔돈 반환 : " + change.toString())
    else println("현금이 부족합니다.")
}