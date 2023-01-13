package fastcampus.part0.kotlin1

// 07. 컬렉션 (list, map, set)

fun main() {
    // 리스트 (list)
    // 특징 : immutable(기본) & mutable로 구분되어있음 ★
    val listMut = mutableListOf(1,2,3,4,5)
    listMut.add(6)
    listMut.addAll(listOf(7,8,9))

    val listImmut = listOf(1,2,3,4)
    //listImmut.add   // 함수 안 뜸
    listImmut.get(0)
    listImmut[0]    // .get(0) == [0]

    // 강력한 확장 함수
    // => 반복문 사용할 일 굉장히 줄어듦
    //listMut. 해서 뜨는 함수들
    println(listMut.map { it * 10 }.joinToString("/"))

    val diverseList = listOf(1, "안녕", 1.78, true)   // 다양한 타입의 리스트 생성 가능

    println(listMut.joinToString(","))  // joinToString

    // 맵 (map) : (key, value)
    val mapMut = mutableMapOf((1 to "안녕"), (2 to "hello"))
    val mapImmut = mapOf((1 to "반갑"), (2 to "glad"))
    mapMut.put(3, "응")
    //mapImmut.put  // 에러
    mapMut[5] = "아니"    // get, put 메소드 말고도 index operator를 통해서도 구현 가능

    // 컬렉션을 잘 쓰기 위해서는 그 안에 있는 함수들을 잘 학습해야 함
}