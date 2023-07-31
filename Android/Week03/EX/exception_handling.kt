/*
java.lang.ArithmeticException: / by zero
finally 내부 코드는 무조건 실행된다.
Exception in thread "main" java.lang.Exception: ERROR : 0으로 나눌 수 없습니다.
	at com.example.test.MainActivityKt.main(MainActivity.kt:15)
	at com.example.test.MainActivityKt.main(MainActivity.kt)
*/

fun main()
{
    var divNumber = 0
    try { // 코드 실행을 시도하며, 해당 구역 에러 발생을 감지한다.
        1 / divNumber
    } catch(e: Exception) { // try 구역의 에러 발생시 분기되어 해당 코드가 수행됨
        println(e)
    } finally { // try-catch 구문과 상관없이 무조건 수행되는 코드
        println("finally 내부 코드는 무조건 실행된다.")
    }

    if (divNumber == 0){
        throw Exception("ERROR : 0으로 나눌 수 없습니다.")
        // 조건에 맞을 경우, 정의된 에러를 발생시킴
    }
    println("1을 $divNumber 로 나눈 결과 : " + 1/divNumber)
}