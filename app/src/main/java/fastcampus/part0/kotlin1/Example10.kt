package fastcampus.part0.kotlin1

// 10. String template
// log 찍을 때 특히 유용한 기능

fun main() {
    val a = 10
    val name = "안녕"
    val isHigh = true

    // "$"
    println("$a $name $isHigh") // 10 안녕 true
    println("$a ${name.length} $isHigh")    // 함수 사용 가능 => bracket { } 사용   // 10 2 true
}