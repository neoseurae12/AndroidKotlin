package fastcampus.part0.kotlin2

// 01. 람다
// kotlin : 객체지향 + "함수형"(=> 조건'식', immutable 기본, 람다식 등등)

fun main() {
    // about "람다"
    // 1. 익명함수
    // 2. 변수처럼 사용되어 함수의 argument, return 이 될 수 있음
    // 3. 일회용 (한 번 사용디고, 재사용되지 않는 함수)
    val a = fun(){ println("hello") }
    val b : (Int) -> Int = {
        // input 값은 여러 개일 수 있음, BUT output(return 값)은 only 한 개
        it * 10 // input 값이 '하나'면 it으로 받을 수 있음
        //println(it) // 맨 마지막 줄이 return 값이 됨   // return 타입 : Unit
    }
    val c = {i : Int, j : Int -> i * j}
    // 입력값에 대한 타입 정보를 { } 구현부 내에 작성도 가능
    val d : (Int, String, Boolean) -> String = { _, b, _ -> b}
    // 실제 사용하지 않는 변수들은 _(underscore)로 생략 가능
    hello(10, b)
    /*
    실행 결과
    10
    200
     */

    println(b)  // 실행 결과 : Function1<java.lang.Integer, java.lang.Integer>
    // 실행한 결가가 나오는 게 아니라 코드 조각 자체가 나와버림

    // 람다식 실행 방법
    println(b(10))  // 실행 결과 : 100
    // b라고만 쓰지 말고 함수를 실행시키는 것처럼 ( ) 안에 입력값을 넣어줄 것
    // 입력값이 '없는' 함수라면 => 그냥 b() 로 실행
}

fun hello(a: Int, b: (Int) -> Int) : (Int) -> Int {
    println(a)
    println(b(20))
    return b
    // 람다식을 input으로도 받을 수 있고, output으로도 내뱉을 수 있음
}

// MainActivity 파일로 넘어가서 설명 이어서..