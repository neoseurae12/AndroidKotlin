package fastcampus.part0.kotlin1

// 08. Null

fun main() {
    // 자바에서 가장 많이 만나는 exception : NullPointerException
    // 자바에서는 null을 컴파일 단계에서 체크할 수 없기 때문

    // 코틀린 : language 단계에서부터 null이 될 수 있는 것과 없는 것을 구분해서 null에 대한 안정성을 더 가짐 ★
    var name : String = "원희"    // String 뒤에 아무것도 없는 것 자체가 이는 non-nullable하다는 것임
    //name = null // 오류 발생

    // nullable로 선언하고 싶다면
    var nickname : String? = null
    // 타입 뒤에 '?'(물음표) 넣으면 됨

    // null에 대한 safety를 체크하고 싶을 때 많이 쓰는 방식
        // 자바 ver.
    val resultJava = if (nickname == null) {
        "값이 없음"
    } else {
        nickname
    }
    println(resultJava)

        // 코틀린 ver.
    // 엘비스 연산자(Elvis Operation) : '?:' => 값이 null이라면 오른쪽 값을 내뱉어라. 만약 non-null이라면 해당 값을 내뱉고.
    nickname = null
    val resultKotlin = nickname?: "값이 없음"
    println(resultKotlin)

    var size = nickname?.length
    println(size)
    // '?' 포함 (포함 안할 시 컴파일에러 뜸)
    // nickname이 null일 경우 => 더이상 진행 안 하고 null을 내뱉음 / non-null일 경우 => length값을 내뱉음
    // nullpointerexception 발생 안함

    nickname = "너스레"
    size = nickname!!.length
    println(size)
    // !! => nullable한 타입이라고 선언은 했지만 맥락상 non-nullable일 수밖에 없는 상황이라면 반드시 length를 내뱉도록 함
    // 웬만하면 !! 안 쓰고 애초에 non-nullable한 타입으로 선언하는 것을 권장하긴 함
    // 그럼에도 불구하고 !!를 쓸 수밖에 없는 상황 有
}