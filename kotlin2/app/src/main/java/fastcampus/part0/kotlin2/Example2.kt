package fastcampus.part0.kotlin2

// 02. 확장함수

fun main() {
    // 확장함수 (Extension function)
    // 기존에 정의되어 있는 클래스에 함수를 추가하는 기능
    Test().hello()
    val test = Test()
    test.bigHello()
}

fun Test.bigHello() = print("완전 안녕")
// 확장함수 => 코드량 ↓, 커스텀을 위해 상속을 쓰지 않아도 됨, 쉽지만 강력한 도구!

class Test() {  // 더이상 변경할 수 없는 클래스 ; 라이브러리에서 import된 클래스
    fun hello() = println("안녕")
    fun bye() = println("잘가")
}