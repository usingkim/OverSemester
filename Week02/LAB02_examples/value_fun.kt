fun plus_number(number: Int, number2: Int): String{
    var sum = (number1 + number2).toString()
    return "number1과 number2를 더하면? : " + sum
}

fun main()
{
    var number1: Int = 10
    var number2: Int = 10 // 변경이 가능하다
    val number3: Int = 10

    number1 = 20
    number2 = 20
    // number3 = 20 불가능하다.

    println(plus_number(number1, number2))
}