package com.example.lab02_all

public class Calculator{
    var result: Float = 0.0f
    var results = arrayListOf<Float>()

    private fun add(a: Float, b: Float): Float{
        results.add(a+b)
        return (a+b)
    }

    private fun subtract(a: Float, b: Float): Float{
        results.add(a-b)
        return (a-b)
    }

    private fun multiple(a: Float, b: Float): Float{
        results.add(a*b)
        return (a*b)
    }

    private fun division(a: Float, b: Float): Float{
        results.add(a/b)
        return a / b
    }

    fun load(){
        for(i in 0 .. results.size-1 step (1)){
            println(results.get(i).toString())
        }
    }

    fun cal(a: Float, b: Float, type: String){
        when(type){
            in "add" -> result = add(a, b)
            in "subtract" -> result = subtract(a, b)
            in "multiple" -> result = multiple(a, b)
            in "division" ->
                if (b == 0.0f) println("ERROR : number can not be divided with zero.")
                else result = division(a, b)
        }
    }

    fun print_calculated_number(){
        println(result)
    }

}

fun main(){
    var calculator = Calculator()
    calculator.cal(1.0f, 2.0f, "add")
    calculator.print_calculated_number()
    calculator.cal(1.0f, 10.0f, "subtract")
    calculator.print_calculated_number()
    calculator.cal(1.0f, 0.0f, "division")
    calculator.print_calculated_number()
    calculator.cal(1.0f, 10.0f, "division")
    calculator.print_calculated_number()
    calculator.cal(1.0f, 10.0f, "multiple")
    calculator.print_calculated_number()
    println("------------------")
    calculator.load()
}