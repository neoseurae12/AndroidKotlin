package fastcampus.part0.kotlin1

// 04. 클래스

fun main() {
    val user1 = User("노원희", 24)
    val user2 = User("채상아")
    println(user2.age)
    val kid = Kid("아이", 3, "male")
    println()
    // 출력 결과 :
    // 100
    // 초기화 중 입니다.   // init이 먼저 호출됨
    // 부 생성자 호출
}

open class User(open val name : String, open val age: Int = 100)
// 구현부가 없다면 { } 생략도 가능
// 이 한 줄로 '생성자' 역할도 할 수도 있고, property들(ex: name, age)에도 접근이 가능함
// val, var 앞에 'private'을 붙여 해당 property를 외부로부터 숨길 수도 있음
// 함수에서와 동일하게 'default값'을 넣어줄 수도 있음
// 자바로 했으면 여러 줄이었을 것을, 코틀린에서는 단 한 줄로 가능!
// '주 생성자' => constructor라는 키워드 생략되어있는 상태

// '상속'의 방법이 좀 다름
// "open" : Kid 생성하자마자 User의 값을 받고 싶다면
// 기본적으로 코틀린은 상속을 권장하지 않고 있음 => 상속을 굳이 하고 싶으면 open 키워드를 앞에 붙여야 함
class Kid(override val name: String, override val age: Int) : User(name, age) {
    var gender: String = "female"

    // init => 클래스 안에서 '가장 먼저' 호출됨
    init {
        println("초기화 중 입니다.")
    }

    // '부 생성자' (secondary constructor)
    constructor(name: String, age: Int, gender: String) : this(name, age) {
        this.gender = gender
        println("부 생성자 호출")
    }
}

