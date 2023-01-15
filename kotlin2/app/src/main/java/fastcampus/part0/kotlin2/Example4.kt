package fastcampus.part0.kotlin2

// 04. 초기화 지연
// https://github.com/Fastcampus-Android-Lecture-Project-2023/part0-chapter2
    // 링크 참고 -- 잘 정리되어있음
// 선언 당시에 초기화를 하지 않고 나중에 초기화 가능
// 장점 : 효율적인 메모리 사용, null safe 한 value 사용

// lateinit & var
lateinit var text :String   // 어떤 '타입'인지는 반드시 밝혀줘야 함
lateinit var age :Integer   // Int와 같은 'primitive 타입'은 lateinit으로 선언 불가. 대신, "참조 타입"인 Integer로는 선언 가능

// lazy & val
val test :Int by lazy { // 선언과 동시에 초기화하는 람다식을 구현 해야함
    println("초기화 중")
    55  // 람다식 => return 값 : 맨 마지막 줄
}
// 변수를 '호출'하는 시점에 초기화가 됨. 즉, 호출을 안 하면 메모리에 할당되 안 되고, 선언도 안 한 취급.

fun main() {
    //println(text)   // 초기화 '이전'에 변수에 접근하면 Exception (UninitializedPropertyAccessException) 발생
    text = "name"
    println(text)
    age = Integer(10)

    println("메인 함수 실행")
    println("초기화 한 값 $test")
    println("두 번째 호출 $test")    // 초기화는 한 번만 하는 거라, 또 호출되진 않음
    /*
    * 출력 결과
    * 메인 함수 실행
    * 초기화 중
    * 초기화 한 값 55
    * 두 번째 호출 55
    * */
}