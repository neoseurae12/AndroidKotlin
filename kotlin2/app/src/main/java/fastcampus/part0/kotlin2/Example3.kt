package fastcampus.part0.kotlin2

// 03. Scope function  (범위 지정함수)
// 자세한 설명 ref : https://github.com/Fastcampus-Android-Lecture-Project-2023/part0-chapter2
    // 깔끔하게 표로 잘 정리 되어있음

fun main() {
    // let, run, apply, also, with

    // 1. let
    // 수신객체를 통해서 let을 확장함수로 호출
    // 수신객체.let { it 또는 user ->
    //
    // 마지막 줄    // return 값
    // }
    // it => it. 은 생략 불가능 / 원하는 로컬 변수로 선언 가능
    val a = 3
    a.let {  }
    val user1 = User("노원희", 24, true)
    val age = user1.let {
        user1.age
    }
    println(age)    //24
        // 만약 user2가 nullable한 객체라면
    var user2 : User? = User("아구몬", 5, false)
    val gender = user2?.let {
        it.gender
    }
    println(gender) //false
    user2 = null
    println(user2)  //null

    // 2. run
    // 수신객체.run { this ->
    //
    // 마지막 줄    // return 값
    // }
    val kid = User("벤틀리", 5, false)
    val kidAge = kid.run {
        this.age
        // this. 은 생략 가능 / this => 원하는 로컬 변수로 선언 불가능
        // # 음? 좀 이상함. this 생략 가능하댔는데 생략하면 5가 아니라 24가 나옴
    }
    println(kidAge) // 5

    // 3. apply
    // 주요 용도 : 함수 '초기화' (return 값이 '수신객체 본인'이기 때문)
    // '초기화' 시 : apply > also (also는 it으로, 생략 불가능하기 때문)
    // '로그 확인' 시 : also > apply
    // 수신객체.apply { this ->
    // ~@#$%^%$#@#$%^&
    // ~@#$%^%$#
    // ~@#$%^%$#@#$%^&&*(*&*
    // }
    // return 값 : 수신객체 본인
    // 비교 : run VS. apply
        // run의 경우
    val kidName1 = kid.run {
        name
    }
        // apply의 경우
    println(kidName1)   // 벤틀리 => 마지막 줄
    val kidName2 = kid.apply {
        name
    }
    println(kidName2)   // fastcampus.part0.kotlin2.User@2d6e8792 => 수신객체 본인
    val girl = User("리플리", 67, true, true)
    val girlValue = girl.apply {
        hasGlasses = false  // 초기화
    }
    println(girlValue.hasGlasses)   // false => 수신객체 본인

    // 4. also
    // 주요 용도 : 초기화보다는 log를 찍으면서 수신객체의 내용을 확인할 때
    // 수신객체.also { it ->
    //
    //
    // }
    // return 값 : 수신객체 본인
    val boy = User("에일리언", 16, false, true)
    val boyValue = boy.also { male ->
        male.name
        male.hasGlasses = false
    }
    println(boyValue)   // fastcampus.part0.kotlin2.User@2812cbfa => 수신객체 본인
    println(boyValue.hasGlasses)    // false => 수신객체 본인

    // 5. with (성격이 살짝 다름 ; 앞의 함수들은 '확장함수'로써 사용, with은 "함수의 인자"로써 사용)
    // with(수신객체) { this ->
    //
    // 마지막줄     // return 값
    // }
    val result = with(boy) {
        hasGlasses = true
        true
    }
    println(result) // true
}

class User(
    val name: String,
    val age: Int,
    val gender : Boolean,
    var hasGlasses : Boolean = true
)