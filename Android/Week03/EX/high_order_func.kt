/*
----고차 함수----
고차 함수로 삽입되는 매개변수 함수
operation 결과 : 5
*/

fun main()
{
    // 고차 함수 : 다른 함수를 매개변수로 받거나 함수를 반환하는 함수
    // 아래 high_order_function은 함수를 인자로 받는 고차함수이다.
    fun high_order_function(a: Int, operation: (Int, Int)->Int)
    {
        println("----고차 함수----")
        val result = operation(2, 3)

        println("operation 결과 : $result")
    }

    high_order_function(1){ a, b->
        println("고차 함수로 삽입되는 매개변수 함수")
        a + b
    }
}