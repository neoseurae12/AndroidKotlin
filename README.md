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

## App-1) 숫자세기 앱

<사진>

### 구현 기능
- '+' 버튼을 클릭 시, 숫자를 1씩 올리기
- 초기화 버튼을 클릭 시, 숫자를 0으로 변경하기

### 학습 목표
- 간단한 기능을 구현하고, UI 를 그릴 수 있다
	- LinearLayout 을 이용하여 간단한 UI 를 그릴 수 있다.
	- Activity 를 통해 사용자 입력에 대한 출력을 보여줄 수 있다.
	☞ 사용자가 상호작용하는 곳은 '그래픽'이다!
- UI
	- LinearLayout
	- TextView
	- Button
	- dp, sp
- Kotlin
	- val, var
	- 복합대입 연산자 +=
- Android
	- Activity
	- R 파일
	- findViewById
	- setOnClickListener
	- Log

### 한 걸음 더
1. 화면의 방향이 변경된다면 어떻게 해야할까요?
	i. 값을 유지하려면 어떻게 해야할까요?
		- part1-chapter3 강의 참고
	ii. 화면 방향에 상관없이 버튼을 보이게 하려면 어떻게 해야할까요?
		- numberTextView 의 height 값을 지정하지 말고, weight 를 이용
2. weight 를 넣을 때 dimension 에 왜 0dp 를 넣으라고 했을까요?
	- LinearLayout 의 weight 값이 잘 적용되기 위해선, orientation 에 따라, width 또는 height 의 값이 0dp 여야 함
		- orientation: vertical -> layout_height=“0dp”
		- orientation: horizontal -> layout_width="0dp"
	- https://developer.android.com/guide/topics/ui/layout/linear?hl=ko#Weight
