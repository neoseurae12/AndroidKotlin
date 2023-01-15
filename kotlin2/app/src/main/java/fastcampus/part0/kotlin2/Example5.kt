package fastcampus.part0.kotlin2

// 05. data, sealed class
// https://github.com/neoseurae12/AndroidKotlin/tree/main
    // 리드미에 정리해둠

// 1) data class

fun main() {
    val person = Person("수지", 24)
    val dog = Dog("해피", 6)

    println(person.toString())  // 그냥 class => fastcampus.part0.kotlin2.Person@2812cbfa
    println(dog.toString())     // data class => Dog(name=해피, age=6)
    println(dog.copy(age = 3).toString())   // copy() => Dog(name=해피, age=3)
    // copy() => property 몇몇 개의 값만 바꾸고 싶다면

    val tiger :Tiger = BaekduTiger()
    val result1 = when(tiger) {
        is BaekduTiger -> "백두산 호랭이"
        is RussianTiger -> "러시안 호랭이"
        is JiriTiger -> "지리산 호랭이"
        else -> "뭔 호랭이?"
        // Tiger를 상속받은 애들은 3개밖에 없어서 else로 빠질 일이 없을 테지만,
        // 일반 abract class의 컴파일러는 Tiger를 상속받은 애들이 누군지 전혀 모르기 때문에 else를 반드시 포함시켜줘야 함
    }

    val bear :Bear = JiriBear()
    val result2 = when(bear) {
        is BaekduBear -> "백두산 곰"
        is RussianBear -> "러시안 곰"
        is JiriBear -> "지리산 곰"
        is HanlaBear -> "한라산 곰"
        //else -> "뭔 곰?"  // else => redundant하다고 나옴
        // sealed class의 컴파일러는 Bear를 상속받은 애들이 누군지 다 앎!
        // 장점1) else 브랜치 아예 필요 없어짐
        // 장점2) 상속받는 class를 더 추가되면 when에도 그 class 추가하라고 에러로써 알려줌
    }
}

class Person(
    val name :String,
    val age :Int
)

class Cat(
    // property 하나도 없어도 괜찮음
)
data class Dog(
    // property 적어도 한 개 이상 무조건 필요
    val name :String,
    val age :Int,
    // 자바 코드로 변환해보면 copy(), toString()<데이터 확인>, hashCode(), equals()<동등성>가 자동 생성 되어있음
) // {
//    // 직접 구현도 가능
//    override fun toString(): String {
//        return "$name 직접 구현했습니다!"
//        // 해피 직접 구현했습니다!
//    }
//}

// 상속 불가
//data class Corgi(
//    val pretty :Boolean = true
//) : Dog()
// 에러 발생 ; Function 'component1' generated for the data class conflicts with member of supertype 'Dog'


// 2) sealed class (추상 클래스)

    // 일반 abstract 클래스일 경우
abstract class Tiger
class BaekduTiger :Tiger()
class RussianTiger :Tiger()
class JiriTiger :Tiger()

    // sealed 클래스일 경우
sealed class Bear
class BaekduBear :Bear()
class RussianBear :Bear()
class JiriBear :Bear()
class HanlaBear :Bear() // 새로운 상속받는 class 추가