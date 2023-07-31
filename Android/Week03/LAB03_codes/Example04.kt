package com.example.lab03

fun isLowerCase(c: Char): Boolean{
    if (c.toInt() < 97) return false
    if (c.toInt() > 122) return false

    return true
}

fun change(str: String): String{
    var result: String = ""
    var error: String = ""

    for(s in str){
        if(isLowerCase(s)){
            result += (s.toInt() - 32).toChar()
        }
        else {
            error += s
        }
    }

    if(error.length == 0) return result
    else return "error with = $error"
}

fun main(){
    var a = "abcd"
    println(change(a))

    var b = "EfgH"
    println(change(b))

    var c = "!@%$"
    println(change(c))
}