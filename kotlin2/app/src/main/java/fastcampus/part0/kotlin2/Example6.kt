package fastcampus.part0.kotlin2

import fastcampus.part0.kotlin2.Book.Companion.NAME

// 06. object & companion object

fun main() {
    println(Counter.count)
    Counter.countUp()
    Counter.countUp()
    println(Counter.count)
    // 싱글톤 ; 한 번만 생성됨
    /*
    카운터 초기화
    0
    2
     */

    // 다른 클래스나, 인터페이스를 상속받을 수 있음
    Counter.hello()     // hello

    // 인스턴스를 아직 만들기 전 상태여도 클래스 변수, 메소드처럼 사용 가능
    Book.NAME
    Book.create()

    NAME    // import fastcampus.part0.kotlin2.Book.Companion.NAME
}

// 1) Object
// 주요 쓰임 : 에러 코드 정의
object Counter :Hello() {    // 생성자 X => 클래스를 정의함과 동시에 객체를 생성
    // 프로퍼티, 메서드, 초기화 블록은 사용 가능
    init {
        println("카운터 초기화")
    }
    var count = 0
    fun countUp() {
        count++
    }
}

open class Hello() {
    fun hello() = println("hello")
}


// 2) Companion object

class Book {
    // Java의 "static"과 유사한 역할
    companion object {
        const val NAME = "hello"    // 상수
        fun create() = Book()
    }

    // 클래스 내에 하나만 생성 가능
//    companion object {
//
//    }
}