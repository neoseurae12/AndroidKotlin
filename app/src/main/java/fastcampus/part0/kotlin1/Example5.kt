package fastcampus.part0.kotlin1

// 05. 조건식

fun main() {
    max(10, 3)
    isHoliday("일")
}

// 자바 : 조건"문" => statement ; 실행하는 하나의 문장 덩어리
// 코틀린 : 조건"식" => expression ; return 하는 값 有

// 두 숫자 중 큰 숫자를 내뱉는 함수
fun max(a: Int, b: Int) {
    // statement로써의 쓰임 ; 자바와 동일하게도 가능
    if (a > b) {
        println(a)
    } else {
        println(b)
    }

    // + expression로써의 쓰임 ; kotlin만의 특징
    val result1 = if (a > b) {
        println(a)
    } else {
        println(b)
    }
    println(result1)
    // 출력 결과 : kotlin.Unit

    val result2 = if (a > b) {
        a
    } else {
        b
    }
    println(result2)
    // 출력 결과 : 10

    // 자바 : 삼항 연산자 有
    // 코틀린 : 삼항 연산자 "없음"

    // { } 생략 => 간단하게도 가능
    val result3 = if (a>b) a else b
    println(result3)
    // 출력 결과 : 10
}

// 자바 : switch-case 문
// 코틀린 : when 문
// 월 화 수 목 금 토 일
fun isHoliday(dayOfWeek: String) {
    val result = when(val day = dayOfWeek) {
        "월",
        "화",
        "수",
        "목",
        "금" -> { "안 좋아" }
        else -> if (day=="토") "좋아" else "완전 좋아"
    }
    println(result)
    // when문이 "expression"으로 사용될 경우에는 반드시 'else'를 포함해야 함 => 컴파일 에러
    // 'statement'로만 사용된다면 굳이 else 안 써도 괜찮음
    // 새로운 변수로 받아서도 사용 가능
}
// 출력 결과 : false

fun isHoliday2(dayOfWeek: Any) {    // "Any"로 지정했을 경우
    when(dayOfWeek) {
        "토",
        "일" -> if (dayOfWeek == "토") "좋아" else "완전 좋아"
        in 2..4 -> {}   // when문 안에 "범위 값"도 들어갈 수 있음
        in listOf("월", "화") -> {}
        else -> "안 좋아"
    }
}