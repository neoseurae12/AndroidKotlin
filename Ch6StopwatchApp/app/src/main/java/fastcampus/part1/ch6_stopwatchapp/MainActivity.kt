package fastcampus.part1.ch6_stopwatchapp

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import fastcampus.part1.ch6_stopwatchapp.databinding.ActivityMainBinding
import fastcampus.part1.ch6_stopwatchapp.databinding.DialogCountdownSettingBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownSecond = 5
    private var currentCountDownDeciSecond = countdownSecond * 10
    private var currentDeciSecond = 0   // 0.1초 단위의 숫자
    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countdownTextView.setOnClickListener {
            showCountdownSettingDialog()
        }

        binding.startButton.setOnClickListener {
            start()
            binding.startButton.isVisible = false
            binding.stopButton.isVisible = false
            binding.pauseButton.isVisible = true
            binding.lapButton.isVisible = true
        }
        binding.pauseButton.setOnClickListener {
            pause()
            binding.startButton.isVisible = true
            binding.stopButton.isVisible = true
            binding.pauseButton.isVisible = false
            binding.lapButton.isVisible = false
        }
        binding.stopButton.setOnClickListener {
            showAlertDialog()
        }
        binding.lapButton.setOnClickListener {
            lap()
        }
        initCountdownViews()
    }
    
    private fun initCountdownViews() {
        binding.countdownTextView.text = String.format("%02d", countdownSecond)
        binding.countdownProgressBar.progress = 100

        // stop 한 뒤 다시 start 하면, 그때도 카운트다운 실행되도록
        currentCountDownDeciSecond = countdownSecond * 10
    }

    private fun start() {
        timer = timer(initialDelay = 0, period = 100) {
            if (currentCountDownDeciSecond == 0) {
                currentDeciSecond += 1
                //Log.d("currentDeciSecond", currentDeciSecond.toString())

                val minutes = currentDeciSecond.div(10) / 60
                val seconds = currentDeciSecond.div(10) % 60
                val deciSeconds = currentDeciSecond % 10

                runOnUiThread {
                    binding.timeTextView.text =
                        String.format("%02d:%02d", minutes, seconds)
                    binding.tickTextView.text = deciSeconds.toString()

                    binding.countdownGroup.isVisible = false
                }
            } else {
                currentCountDownDeciSecond -= 1
                val seconds = currentCountDownDeciSecond / 10
                val progress = (currentCountDownDeciSecond / (countdownSecond * 10f)) * 100

                binding.root.post {
                    binding.countdownTextView.text = String.format("%02d", seconds)
                    binding.countdownProgressBar.progress = progress.toInt()
                }
            }
            if (currentDeciSecond == 0 && currentCountDownDeciSecond < 31 && currentCountDownDeciSecond % 10 == 0) {
                val tonetype = if (currentCountDownDeciSecond == 0) ToneGenerator.TONE_CDMA_HIGH_L else ToneGenerator.TONE_CDMA_ANSWER
                ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
                    .startTone(tonetype, 100)
            }
        }
    }

    private fun pause() {
        timer?.cancel()
        timer = null
    }

    private fun stop() {
        binding.startButton.isVisible = true
        binding.stopButton.isVisible = true
        binding.pauseButton.isVisible = false
        binding.lapButton.isVisible = false

        currentDeciSecond = 0
        binding.timeTextView.text = "00:00"
        binding.tickTextView.text = "0"

        binding.countdownGroup.isVisible = true
        initCountdownViews()

        // lap 부분도 초기화
        binding.lapContainerLinearLayout.removeAllViews()

        //Log.d("coutdownSecond", countdownSecond.toString())
    }

    private fun lap() {
        // 카운트다운 중에는 lap 막아두기
        if (currentDeciSecond == 0) return

        // UI를 kotlin 코드를 통해 동적으로 생성해봄
        val container = binding.lapContainerLinearLayout
        TextView(this).apply {
            textSize = 20f  // float 값으로 넣어야 함
            gravity = Gravity.CENTER
            val minutes = currentDeciSecond.div(10) / 60
            val seconds = currentDeciSecond.div(10) % 60
            val deciSeconds = currentDeciSecond % 10
            text = String.format(
                "%d. %02d:%02d %01d",
                container.childCount.inc(),
                minutes,
                seconds,
                deciSeconds
            )
            // 1. 01:03 0
            setPadding(30)
        }.let { lapTextView ->
            container.addView(lapTextView, 0)
        }
    }

    private fun showCountdownSettingDialog() {
        AlertDialog.Builder(this).apply {
            val dialogBinding = DialogCountdownSettingBinding.inflate(layoutInflater)
            with(dialogBinding.countdownSecondPicker) {
                maxValue = 20
                minValue = 0
                value = countdownSecond
            }
            setTitle("카운트다운 설정")
            setView(dialogBinding.root)
            setPositiveButton("확인") { _, _ ->
                countdownSecond = dialogBinding.countdownSecondPicker.value
                currentCountDownDeciSecond = countdownSecond * 10
                binding.countdownTextView.text = String.format("%02d", countdownSecond)
            }
            setNegativeButton("취소", null)
        }.show()
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this).apply {
            //setTitle()
            setMessage("종료하시겠습니까?")
            setPositiveButton("네") { _, _ ->
                stop()
            }
            setNegativeButton("아니오", null)
        }.show()
    }
}