# AndroidKotlin

## Kotlin 중급

### 05. data class & sealed class

- Data class : 데이터를 담기 위한 클래스
  - toString(), hashCodde(), equals(), copy() 메서드를 자동으로 생성
    - override 하면, 직접 구현한 코드를 사용
  - 1개 이상의 프로퍼티가 있어야함
  - 데이터 클래스는 abstract, open, sealed, inner 를 붙일 수 없음
  - 상속이 불가능
- Sealed class : 추상 클래스로, 상속받은 자식 클래스의 종류를 제한
  - 컴파일러가 sealed 클래스의 자식 클래스가 어떤 것인지 앎
  - when 과 함께 쓰일 때, 장점을 느낄 수 있음

### 06. object & companion object

- Object : 클래스를 정의함과 동시에 객체를 생성
  - 싱글톤을 쉽게 만들 수 있는 키워드
  - 생성자 사용 불가
  - 프로퍼티, 메서드, 초기화 블록은 사용 가능
  - 다른 클래스나, 인터페이스를 상속받을 수 있음
- Companin Object 동반객체
  - Java 의 static 과 동일한 역할
  - 클래스 내에 하나만 생성할 수 있음
