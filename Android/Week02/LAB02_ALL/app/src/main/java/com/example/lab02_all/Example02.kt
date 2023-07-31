package com.example.lab02_all

class Matrix(private val matrixAsString: String){
    val dates: List<List<Int>>
    // 문자열 to 2차원 배열 매트릭스
    init {
        /*
            init 메소드로 클래스 선언시, 초기값 설정
            받아온 String Matrix 변수를 2차원 배열로 변환을 시작한다
            String 클래스의 확장함수인 .toRegex()로 "[ space ]" 빈문자열이 "+" 1회 이상
            등장할 떄, " space " 로 변환한다.
            즉, val string = "1   2 3   4     5" 라는 String이 존재할 때,
            .replace 와 .toRegex() 통해 string="1 2 3 4 5로 변환이 가능하다."

            it은 변경하고자하는 리스트의 내부 값으로 trim은 앞 뒤의 공백을 제거한다.
        */
        dates = matrixAsString
            .replace("[ ]+".toRegex(), " ")
            .split("\n")
            .map {
                it.trim().split(" ").map { it.toInt() }
            }
    }
    fun column(colNr: Int): List<Int> {
        return dates.map { it[colNr - 1] }
    }
    fun row(rowNr: Int): List<Int> {
        return dates.get(rowNr - 1)
    }
}

fun main(){
    val inputString="1 2 3\n4 5 6\n7 8 9"

    println("매트릭스 문자열")
    println(inputString)

    val matrix = Matrix(inputString)

    println("\n매트릭스 클래스")
    println(matrix.row(2))
    println(matrix.column(1))

    /*
        매트릭스 문자열
        1 2 3
        4 5 6
        7 8 9

        매트릭스 클래스
        [4, 5, 6]
        [1, 4, 7]
     */
}