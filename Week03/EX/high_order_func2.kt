enum class Delivery{STANDART, EXPEDITED}
class Order(val itemCount: Int)

fun getShippingCostCalculator( // delivery 클래스 타입을 가지는 매개변수
    delivery: Delivery 
):(Order)->Double{ // 리턴 타입으로 람다함수를 가진다.
    if(delivery == Delivery.EXPEDITED){
        return {order -> 6 + 2.1 * order.itemCount}
    }
    return {order -> 61.2 * order.itemCount}
}

fun main(){
    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)

    println("배달 비용은 : ${calculator(Order(3))}")
    // 배달 비용은 : 12.3
}