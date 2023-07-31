package com.example.lab02_all
import java.util.Scanner

enum class grades{
    A, B, C, D, E, F
}

class MyAccount {

    var name: String = ""                   // 사용자 이름
    var money: Int = 0                      // 사용자 계좌 잔액
    var grade = grades.valueOf("C")   // 신용 등급
    var sc: Scanner = Scanner(System.`in`)  // 입출력 위함
    var isFreezing: Boolean = false         // 계좌 동결 여부

    private fun Deposite() {
        // 입금 함수
        println("입금하실 금액을 말하세요.")
        var m: Int = sc.nextInt()

        money += m

        if(isFreezing && money >= 0){
            println("동결계좌가 열렸습니다.")
            isFreezing = false
        }

        if (money >= 0) upGrade()

        println(m.toString() + " 원을 입금하였습니다. 잔액 : " + money.toString())
    }

    private fun Withdraw() {
        if (isFreezing) {
            println("동결된 계좌에서 출금하실 수 없습니다.")
            return
        }

        println("출금하실 금액을 말하세요.")
        var m: Int = sc.nextInt()
        money -= m

        if(money < -1000) {
            println("잔액이 부족합니다.")
            money += m // 출금 안됨. alert만
        }
        else if(money < 0){

            if (grade == grades.F){
                println("최저 등급의 신용을 가지고 있습니다.")
                println("계좌가 동결됩니다.")
            }

            println("계좌가 마이너스 되었습니다.")
            downGrade()
            println("$m 원을 출금하였습니다. 잔액 : $money")
        }

    }

    private fun downGrade()
    {
        var prev = grade
        when(grade.name){
            in "A" -> grade = grades.B
            in "B" -> grade = grades.C
            in "C" -> grade = grades.D
            in "D" -> grade = grades.E
            in "E" -> grade = grades.F
            in "F" -> {
                isFreezing = true
                return
            }
        }

        print("신용등급이 '" + prev.name + "->" + grade.name + "'로 한단계 떨어집니다.")
    }

    private fun upGrade(){
        var prev = grade
        when(grade.name){
            in "A" -> {
                println("최고 등급의 신용을 가지고 있습니다.")
                return
            }
            in "B" -> grade = grades.A
            in "C" -> grade = grades.B
            in "D" -> grade = grades.C
            in "E" -> grade = grades.D
            in "F" -> grade = grades.E
        }

        println("신용등급이 '" + prev.name + "->" + grade.name + "'로 한단계 상승합니다.")
    }

    private fun Information(){
        println("계좌정보는 다음과 같습니다.")
        println("|이름:       Kim |")
        println("|계좌잔액:    " + money.toString() + "   |")
        println("|신용등급:    " + grade + "   |")
        println("----------------")
    }

    public fun callFun(mode: String){
        when(mode){
            in "I" -> Information()
            in "D" -> Deposite()
            in "W" -> Withdraw()
            in "E" -> System.exit(0)
        }
    }

}

fun main()
{
    var user = MyAccount()

    var sc: Scanner = Scanner(System.`in`)


    while(true){
        println("----선택하세요----")
        println("|(I) 계좌정보    |")
        println("|(D) 입금       |")
        println("|(W) 출금       |")
        println("|(E) 종료       |")
        println("----------------")

        var mode: String = sc.next()
        user.callFun(mode)

        println()
        println()
    }

}