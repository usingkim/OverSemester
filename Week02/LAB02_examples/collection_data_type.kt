import java.util.*
/*
연관된 데이터를 하나의 변수로 관리하는 방법
종류는 Array, List, Set, Map

3가지 단계로 활용한다.
1. 선언과 초기화
2. 사용(값 참조, 출력)
3. 수정(추가, 변경, 삭제)
 */

fun main()
{
    println("====== 배열 ======")

    // 배열 : 크기가 고정된 것 표현을 위함 Array
    // List : MutableList 가 수정 가능함 => add 가능
    // Array나 그냥 List는 add 불가능(수정 불가능하므로)

    var list1: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    var list2: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    var list3: MutableList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8)

    list1 = list1.plus(element: 9)
    list2 = list2.plus(element: 9)
    list3.add(9)

    println("list1: ${Arrays.toString(list1)}")
    println("list2: $list2")
    println("list2: $list3")

    var filtered_list1 = list1.filter{it % 3 == 0}
    var filtered_list2 = list2.filter{it % 3 == 0}
    var filtered_list3 = list3.filter{it % 3 == 0}

    println("list1의 3의 배수 출력 : $filtered_list1")
    println("list2의 3의 배수 출력 : $filtered_list2")
    println("list3의 3의 배수 출력 : $filtered_list3")
}