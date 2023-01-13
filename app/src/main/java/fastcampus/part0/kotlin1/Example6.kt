package fastcampus.part0.kotlin1

// 06. 반복문

fun main() {
    for (i in 1..10) {  // range 표현
        print(i)
        print(".")
    }
    println()

    // IntRange(1, 10) : === 1..10      // inclusive
    for (i in IntRange(1, 10)) {  // range 표현
        print(i)
        print(".")
    }
    println()

    // until : 끝 숫자를 포함 X
    for (i in 1 until 11) {
        print(i)
        print(".")
    }
    println()

    // step : 반드시 '양의 정수'
    for (i in 1..10 step(2)) {
        print(i)
        print(".")
    }
    println()

    // downTo : 내림차순
    for (i in 10 downTo 1) {
        print(i)
        print(".")
    }
    println()

    // downTo & step
    for (i in 10 downTo 1 step(2)) {
        print(i)
        print(".")
    }
    println()

    var c = 1
    while (c < 11) {
        print(c)
        print(".")
        c++
    }
    
    // for문은 주로 iterating (리스트 안의 값들을 하나하나 꺼내볼 때) 할 때 주로 쓰이는데,
    // kotlin의 경우에는 iterating하는 다양한 (확장) 함수들이 따로 있기 때문에
    // for문을 사용하는 경우 적음
}