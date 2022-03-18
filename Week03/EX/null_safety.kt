/*
?. 세이프콜 : 변수값이 null이면 뒤의 메소드는 실행하지 않는다.
?: 엘비스 연산자 : 변수가 null이면 왼쪽, 아니면 오른쪽을 선택한다.

null
길이 : -1
*/

fun main(){
    var a: String? = null
    println(a?.length)
    var b: String? = null
    var l = (b?.length) ?: -1
    println("길이 : $l")
}