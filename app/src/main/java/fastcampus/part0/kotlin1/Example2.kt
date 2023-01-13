package fastcampus.part0.kotlin1

// 02. 함수

fun main() {
    println("Hello world!!")
    val result = test(1, c = 5)
    test2(id = "rwhui", name = "노원희", nickname = "너스레")
    println(times1(1, 3))
    println(times2(1, 3))
    println(result)
}

fun test(a: Int, b: Int = 3, c: Int = 4) : Int {
    println(a+b)
    return a + b
}
// 오버로딩 필요 없음
// 명시적 선언 가능

fun test2(name: String, nickname: String, id: String) = println(name + nickname + id)
// single expression : 한 줄 짜리 & 리턴값이 충분히 예측 가능한 함수면 "="으로, 값으로 넣어줄 수 있음 => 간결!

fun times1(a: Int, b: Int) = a * b

fun times2(a: Int, b: Int,): Int {
    return a * b
}
// trailing comma : 나중에 변수 추가를 위해 미리 ,(쉼표) 붙여놨어도 컴파일 에러 나지 않음