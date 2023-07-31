/*
----일반함수----
10 와 5 의 곱 : 50
----람다함수----
10 와 5 의 곱 : 50
*/

fun main()
{
    fun normal_function(a: Int, b: Int): Int{
        println("----일반함수----")
        print("$a 와 $b 의 곱 : ")
        return a * b
    }
    println(normal_function(10, 5))

    /* 
    람다 함수 : 익명함수 기법
    val 함수 이름: (매개변수 타입) -> 리턴 타입 = {매개변수 -> 함수 본문}, 
    마지막 줄의 실행 결과가 리턴 값이다.
    
    람다 함수의 특징
    - 람다함수는 함수형 프로그래밍 개념으로 함수를 파라미터로 전달할 수 있다.
    재귀 반복 문제의 성능 개선에 효과적이다.
    */

    val lambda_function: (Int, Int) -> Int = {
        a : Int, b: Int->
        println("----람다함수----")
        print("$a 와 $b 의 곱 : ")
        a*b
    }
    println(lambda_function(10, 5))
}