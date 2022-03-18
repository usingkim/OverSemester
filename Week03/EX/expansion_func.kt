/*
3
6
10
*/

class Adder(){
    fun add(a: Int, b: Int): Int{
        return a + b
    }

    fun add(a: Int, b: Int, c: Int): Int{
        return a + b + c
    }
}

// 클래스 밖에서 함수(메소드)를 추가하는 방법
fun Adder.add(a: Int, b: Int, c: Int, d: Int): Int{
    return a + b + c + d
}

fun main()
{
    var adder = Adder()

    println(adder.add(1, 2))
    println(adder.add(1, 2, 3))
    println(adder.add(1, 2, 3, 4))
}