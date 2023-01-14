package fastcampus.part0.kotlin2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SAM
        // Single Abstract Method (단일 추상 메소드)
        // 자바 함수형 인터페이스 관계에서 나온 개념
        // 코틀린에서 추상 메소드가 오직 하나뿐인 함수형 인터페이스를 인자로 받는 자바 함수를 호출할 때 => '람다식'으로 대체 가능
        // # 솔직히 잘 안 와닿는 개념 -- 나중에 추가 리서치 해보는 걸로.
        val view = View(this)

        // 자바 ver.
//        view.setOnClickListener(
//            // 함수형 인터페이스
//            new View.OnClickListener() {
//                @Override
//                public void onClick(view: View) {
//                    // 구현부
//                    // println("안녕")
//                }
//            }
//        )

        // 코틀린 ver. -- 기억! ★
        view.setOnClickListener { println("안녕") }
    }
}