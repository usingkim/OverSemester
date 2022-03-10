fun main()
{
    var grade: Array<String>
    grade = arrayOf("A+", "A", "B+", "B+", "C+", "C", "D+", "D", "F")
    var ranking: Int = 1
    var idx: Int = 8

    when(ranking){
        in 0 .. 5 -> idx = 0
        in 6 .. 10 -> idx = 1
        in 11 .. 15 -> idx = 2
        in 16 .. 20 -> idx = 3
        in 21 .. 25 -> idx = 4
        in 26 .. 30 -> idx = 5
        in 31 .. 35 -> idx = 6
        in 36 .. 40 -> idx = 7
        else -> idx = 8
    }

    println("나의 학점은? : " + grade[idx])
}