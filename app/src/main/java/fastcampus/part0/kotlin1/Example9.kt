package fastcampus.part0.kotlin1

// 09. 타입체크 & 캐스팅

fun main() {
    println(typeCheck("안녕"))
    println(typeCheck(3))
    println(typeCheck(true))

    cast("안녕")
    cast(5) // "as" => exception 발생 / "as?" => exception 발생 안함

    smartCast("문자열")
    smartCast(20)
    smartCast(false)
}

// 타입체크 => is
fun typeCheck(a: Any): String {
    return if (a is String) {
        "문자열"
    } else if (a is Int) {
        "숫자"
    } else {
        "모르겠넹"
    }
}

// when문 ver.
fun typeCheckWhen(a: Any): String {
    return when (a) {
        is String -> {
            "문자열"
        }
        is Int -> {
            "숫자"
        }
        else -> {
            "모르겠넹"
        }
    }
}

// 캐스팅 => "as"
fun cast(a : Any) {
    //val result1 = a as String // 들어올 a가 String 또는 String으로 변환 가능한 것이라는 확신이 있을 경우
    val result2 = a as? String  // "as?" => String으로 변환 가능하면 변환돼서 나오고, 불가능하다면 그냥 null을 내뱉어버림
    //println(result1)
    println(result2)

    // 엘비스 오퍼레이터 추가
    val result3 = (a as? String) ?: "실패"
    println(result3)
}

// 스마트 캐스트
fun smartCast(a : Any) {
    val result = if (a is String) {
        println(a.length)   // 3
    } else if (a is Int) {
        println(a.dec())    // 19
    } else {
        println(-1)         // -1
    }
}