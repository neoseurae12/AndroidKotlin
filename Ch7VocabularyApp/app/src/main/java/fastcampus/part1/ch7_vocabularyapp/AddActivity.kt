package fastcampus.part1.ch7_vocabularyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.Chip
import fastcampus.part1.ch7_vocabularyapp.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private var originWord : Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        binding.addButton.setOnClickListener {
            if (originWord == null) {
                add()
            } else {
                edit()
            }
        }
    }

    private fun initViews() {
        val types = listOf("명사", "동사", "대명사", "형용사", "부사", "감탄사", "전치사", "접속사")
        binding.typeChipGroup.apply {
            types.forEach { text ->
                addView(createChip(text))
            }
        }

        binding.textTextInputEditText.addTextChangedListener {
            it?.let { text ->
                binding.textTextInputLayout.error = when(text.length) {
                    0 -> "값을 입력해주세요"
                    1 -> "2자 이상 입력해주세요"
                    else -> null
                }
            }
        }

        originWord = intent.getParcelableExtra<Word>("originWord")
        originWord?.let { word ->
            binding.textTextInputEditText.setText(word.text)
            binding.meanTextInputEditText.setText(word.mean)
            val selectedChip = binding.typeChipGroup.children.firstOrNull { (it as Chip).text == word.type } as? Chip
            selectedChip?.isChecked = true

        }
    }

    private fun createChip(text: String): Chip {
        return Chip(this).apply {
            setText(text)
            isCheckable = true
            isClickable = true
        }
    }

    private fun add() {
        val text = binding.textTextInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
        //val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId)?.text ?: ""
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId)?.text.toString()

        // 입력 validation
        if (text.isEmpty()) {
            Toast.makeText(this, "단어를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        if (mean.isEmpty()) {
            Toast.makeText(this, "뜻을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        //Log.d("type", type as String)
        //type as String == ""
        if (type == "null") { // 위에랑 똑같이 처리하면 여기서 걸리지가 않음.. 왜지...?
            // .toString()과 as String 간 차이가 있나봄
            Toast.makeText(this, "품사를 선택해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val word = Word(text, mean, type)

        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.insert(word)
            runOnUiThread {
                Toast.makeText(this, "저장을 완료했습니다!", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent().putExtra("isUpdated", true)
            setResult(RESULT_OK, intent)
            finish()
        }.start()

    }

    private fun edit() {
        val text = binding.textTextInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()

        val editedWord = originWord?.copy(text = text, mean = mean, type = type)

        Thread {
            editedWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.updata(word)
                val intent = Intent().putExtra("editedWord", editedWord)
                setResult(RESULT_OK, intent)
                runOnUiThread {
                    Toast.makeText(this, "수정되었습니다", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        }.start()
    }
}