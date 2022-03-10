public class Grade constructor(val name: String, score: Int)
{
    var score = score
    fun change_score(changed_score: Int){
        score = changed_score
    }
    fun print_score()
    {
        println(score)
    }
}

fun main()
{
    var Hong = Grade(name: "Hong", score: 80)
    Hong.print_score()
    Hong.change_score(changed_score: 95)
    Hong.print_score()
}