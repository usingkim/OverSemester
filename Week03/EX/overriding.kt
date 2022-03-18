/*
슈퍼클래스의 drink 함수
money: 2000
콜라를 마십니다.
money: 500
*/

open class Beverage(){
    open fun drink(money: Int): Int{
        println("슈퍼클래스의 drink 함수")
        return money
    }
}

class Cola(): Beverage(){
    var price = 1500
    override fun drink(money: Int): Int{
        println("콜라를 마십니다.")
        return money - this.price
    }
}

fun main(){
    var sup = Beverage()
    var sub1 = Cola()

    println("money: ${sup.drink(2000)}")
    println("money: ${sub1.drink(2000)}")
}