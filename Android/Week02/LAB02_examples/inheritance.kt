// 클래스의 상속 허용 방법 open
open class Person(school: String)
{
    var school = school
    open fun print_school()
    {
        println(school)
    }
}

public class Grade constructor(school: String, val name: String, score: Int):Person(school)
{
    var score = score
    fun change_score(changed_score: Int)
    {
        score = changed_score
    }
    fun print_score()
    {
        println(score)
    }
}

fun main(){
    var Hong = Grade(school: "Pusan Univ.", name: "Hong", score: 80)
    Hong.print_score()
    Hong.change_score(changed_score: 95)
    Hong.print_score()
    Hong.print_school()
}