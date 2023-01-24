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

<img src="https://user-images.githubusercontent.com/87654809/212803675-65c118ad-7818-4738-98e8-45ef14293b48.gif">

### 구현 기능
- '+' 버튼을 클릭 시, 숫자를 1씩 올리기
- 초기화 버튼을 클릭 시, 숫자를 0으로 변경하기

### 학습 목표
- 간단한 기능을 구현하고, UI 를 그릴 수 있다
	- LinearLayout 을 이용하여 간단한 UI 를 그릴 수 있다.
	- Activity 를 통해 사용자 입력에 대한 출력을 보여줄 수 있다.
	☞ 사용자가 상호작용하는 곳은 '그래픽'이다!
- UI
	- LinearLayout (orientation, weight)
	- TextView (size, color, ...)
	- Button (margin, padding, gravity)
	- dp, sp
- Kotlin
	- val, var
	- 복합대입 연산자 +=
- Android
	- Activity (user와 상호작용하는 진입점, lifecycle)
	- R 파일 (id 목록)
	- findViewById (R 파일 --> Kotlin으로 id 가져옴)
	- setOnClickListener
	- Log (중간 확인)

### 한 걸음 더
1. 화면의 방향이 변경된다면 어떻게 해야할까?
	- 값을 유지하려면 어떻게 해야할까?
		- App-3 참고
	- 화면 방향에 상관없이 버튼을 보이게 하려면 어떻게 해야할까?
		- numberTextView 의 height 값을 지정하지 말고, weight 를 이용
2. weight 를 넣을 때 dimension 에 왜 0dp 를 넣으라고 했을까?
	- LinearLayout 의 weight 값이 잘 적용되기 위해선, orientation 에 따라, width 또는 height 의 값이 0dp 여야 함
		- orientation: vertical -> layout_height=“0dp”
		- orientation: horizontal -> layout_width="0dp"
	- https://developer.android.com/develop/ui/views/layout/linear#Weight
	
	
## App-2) 단위 변환 앱

![App-2_단위전환 앱](https://user-images.githubusercontent.com/87654809/213958079-c95c5618-750f-43c1-8cc9-272737b28670.gif)

### 구현기능

- cm 를 m 로 변환
- 값을 입력하면, 바로 변환된 값이 노출
	- 입력값은 자연수로 한정
- 단위를 반대로 변경
- 단위 변환 연산
	- cm → m (X 0.01)
	- m → cm (X 100)

### 학습 목표

- 간단한 기능을 구현하고, UI 를 그릴 수 있다 (2)
	- ConstraintLayout 을 이용하여 간단한 UI 를 그릴 수 있다.
	- 키보드로 사용자가 입력한 값을 받을 수 있다.
	- 사용자의 입력값이 변경되면, 바로 변환된 값을 보여줄 수 있다.
	- 방향이 변경됐을때, 값을 유지하는 방법
- UI
	- ConstraintLayout
	- EditText
- Android
	- ViewBinding (https://developer.android.com/topic/libraries/view-binding)
	- ActivityLifecycle
	- onSaveInstanceState
	
#### 학습내용
- ViewBinding(뷰 바인딩)
	- findViewById를 대체함
		- findViewById의 nullPointException을 방지함
		- UI 요소에 사용한 Id 값들에 뭐가 있었는지 다 꿰고 있지 않아도 됨
- 에러처리
	- [에러1] 숫자를 너무 길게 입력하면 NumberFormatException이 발생하면서 앱이 강제 종료됨
		- 이유 : Int의 범위 => -2147483648 ~ 2147483647
		- 해결방법 : try-catch문으로 해결 / 애초에 maxLength로 제한 두기
	- [에러2] 숫자를 입력했다 지웠더니 또 NumberFormatException 발생. 근데 이번에는 For input string: "", 즉 input string이 "empty string"이라 발생. 즉, empty string을 숫자 몇으로 봐야하는지 애매해서 발생한 문제
		- 해결방법 : 숫자 넣기 전에 if 문으로 text.isNullOrBlank()를 검사함
	- [에러3] m -> cm 변환 시 숫자 추가 입력하면 갑자기 cm -> m로 값이 나옴
		- 이유 : 애초에 TextChangedListener에서 cm -> m 계산을 디폴트로 설정해줬어서 그럼.
		- 해결방법 : TextChangedListener에서도 cmToM 변수의 true/false에 따라 다르게 동작되도록 if문으로 분기 나눠줘야 함.
	- [에러4] m -> cm 변환 시 m로 너무 큰 숫자를 입력하면 cm로 거기에 *100한 수가 나오면서 또 Int 범위를 초과하게 됨.
		- 해결방법 : maxLength를 더 줄임
	- 에러 처리 굉장히 중요함. 잘 처리하지 못했을 시 앱 crash로 이어져 앱의 안정성이 떨어지고 사용자들의 불편을 초래하기 때문.
- Lifecycle
	- https://developer.android.com/guide/components/activities/activity-lifecycle
	- ![image](https://user-images.githubusercontent.com/87654809/213953195-af367c33-8010-45e1-ae98-8465680b4c8d.png)
	- lifecycle을 알아야 하는 이유
		- 상태 변화에 따른 처리를 잘해야 사용자가 원하는대로 서비스 제공 가능
		- 적시에 알맞은 작업을 하고 적절하게 전환을 처리하면 앱이 더욱 안정적으로 기능 가능
	- App-2)에서는, 화면이 가로 방향↔세로 방향 간에 회전할 경우, 비정상 종료되거나 사용자의 진행 상태가 저장되지 않는 문제를 통해, lifecycle의 각 콜백이 언제 불리는지, 그 동작은 뭔지를 확인했음
		- m -> cm로 변경하고 화면을 돌렸더니 cm -> m로 제멋대로 바뀌어있음
			- 참고 : Activity state changes > Configuration change occurs (https://developer.android.com/guide/components/activities/state-changes)
		- 단순히 화면만 가로/세로 바꾸는 거지만 lifecycle 상에서는 onCreate()부터 "다시 시작"하게 됨
		- 화면 전환을 해도 사용자의 데이터가 '유지'될 수 있도록 조치 취해줘야 함
			- 참고 : The activity lifecycle > Saving and restoring transient UI state (https://developer.android.com/guide/components/activities/activity-lifecycle#saras)
			- 방법 3가지 
				- 1) ViewModel
				- 2) onSaveInstanceState()
				- 3) 로컬 저장소
			- 이 중 가장 간단한 방법인 onSaveInstanceState()를 사용해서 해결해볼 예정
	- [복원할 저장 상태 "저장"]
		- 참고 : Save simple, lightweight UI state using onSaveInstanceState()
		- onStop() -> onSaveInstanceState()
		- Bundle 객체로 값 저장
	- [복원할 저장 상태 "불러오기"]
		- onCreate() 또는 
		- onStart() -> onRestoreInstanceState() 
			- 단, onRestoreInstanceState()는 복원할 저장 상태가 있을 경우에만 호출됨

### 한 걸음 더
1. 소수점이 정확하지 않은 이유는 뭘까?
	- Java 에서는 실수를 표현할 때는 부동 소수점 방식을 사용 하는데 이 때, 오차가 생길 수 있다. 근사값을 이용하기 때문이다. 부정확성을 해결하기 위해, BigDecimal 이라는 자료형을 사용하면 된다.
		- 상세한 이유는 이해하지 못하더라도, 소수점이 정확하지 않을 수 있다는 점을 인지하고, 정확한 계산을 필요로 할 때는 다른 자료형을 사용하면 됨
		- https://ko.wikipedia.org/wiki/%EB%B6%80%EB%8F%99%EC%86%8C%EC%88%98%EC%A0%90
2. Activity Lifecycle 을 충분히 이해해보자.

## App-3) 응급의료정보 앱

### 시나리오
- 데이터 저장 및 불러오기
- 전화 앱 실행하기
- 데이터 삭제하기

### 구현기능
- 다양한 유저정보
	- 이름
	- 생년월일
	- 혈액형
	- 비상연락처
	- 기타
- 화면 전환
	- intent & activity 추
- 다른 앱 실행
- 데이터 저장

### 학습 목표
- 다양한 위젯을 사용하고, 테마를 이용하여 일관성 있는 UI 를 그릴 수 있다.
	- ConstraintLayout 과 다양한 위젯을 활용
	- Style 을 통한 일관성 있는 UI 구성
	- Adapter 에 대한 이해
	- 간단한 데이터 저장
	- 다른 화면으로 이동
	- 다른 앱 실행
- UI
	- ConstraintLayout
		- guideLine
			- 어느 정도까지만 나타날 수 있도록 함
			- orientation 지정
		- layer
			- 두 개 이상의 요소 "묶기"
	- Style
		- res 폴더에 Android Resource File 추가
		- activity_main.xml 파일에서 style 값으로 적용
	- Theme
	- ImageView
	- RadioButton
		- radiogroup으로 묶어서, 그 중 '하나'만 선택 가능하도록 함
	- CheckBox
	- Spinner ★
		- Adapter : list형 데이터 & UI를 한 번에 연결해주는 역할 => 반복 작업 ↓
	- DatePickerDialog
		- calendar 보여줌
		- .show() 까지 붙여야 보여줌
		- month가 (선택한 달)-1 값으로 나옴. 따라서 +1을 더해줘야 함
- Kotlin
	- const val
		- 하드코딩 피하기
		- 자주 쓰거나 중요한 변수에 오타나면 안 되니까 const 상수값으로 Const.kt 파일에 선언해줌
	- with
		- scope function ☞ 코틀린의 장점
- Android
	- Manifest
		- 안드로이드 앱의 개략적인 개요도
		- 추가해야 하는 것들은 반드시 추가해놔야 함
		- manifests 파일에 InputActivity가 선언되어있지 않을 시 => ActivityNotFoundException 발생
		- 일일이 선언이 귀찮다면 애초부터 activity 추가할 때 New > Acitivity 로 추가할 것
	- Intent
		- 참고 : https://developer.android.com/guide/components/intents-filters
		- 명시적 intent : ex) 화면 전환 / 어떤 화면으로 갈지가 아주 명확한 경우
		- 암시적 intent : ex) 전화 앱 실행 / 어떤 동작이라는 것까지만 알고 구체적으로 어떻게 실행할지는 모를 경우
			- 참고 : https://developer.android.com/training/basics/intents/sending
	- SharedPreference
		- <비교> Intent의 역할
			- activity와 activity 간의 전환 & 데이터(메세지) 전달 & 다른 앱 실행 가능
			- 하지만 앱을 껐다 다시 들어가면 주고받던 데이터 다 날라감.
			- 이유 : intent는 activity들 간에 데이터를 주고받을 뿐, 해당 데이터를 어딘가에 '저장'해놓은 건 아니기 때문.
		- SharedPreferences의 역할
			- 파일 형태로 "데이터 저장"
			- 데이터 저장 후에 '종료'(finish())까지 해줘야 함
			- 참고: https://developer.android.com/training/data-storage/shared-preferences
		- getSharedPreferences().edit()에서 잊지말아야 하는 것 : apply()까지 해야 적용된다!
			- commit() : 동기적. 해당 스레드에서 데이터 저장을 다 하기 전까지 다음 스레드의 동작을 막음
			- apply() : 비동기적. 
			- 가능한 apply >> commit
	- Toast
		- 밑에 작게 메세지 뜨게 함
		- .show()까지 해야 보임
	- onResume()
		- activity로 다시 돌아왔을 때는 onCreate()가 아닌 onResume()이 호출됨
- 함수 하나에 기능 '하나'만 있는 게 좋다

### 한 걸음 더
1. Intent 로 할 수 있는 다양한 동작에 대해 학습해보기
	- https://developer.android.com/reference/android/content/Intent?hl=ko
2. Manifest 에 들어가는 다양한 값들에 대해 학습해보기
	- https://developer.android.com/guide/topics/manifest/manifest-intro#reference
3. Kotlin 의 다양한 ScopeFunction 에 대해 학습해보기
	- https://kotlinlang.org/docs/scope-functions.html
