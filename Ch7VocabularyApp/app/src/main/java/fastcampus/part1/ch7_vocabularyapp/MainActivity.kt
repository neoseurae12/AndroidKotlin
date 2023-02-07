package fastcampus.part1.ch7_vocabularyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.part1.ch7_vocabularyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding :ActivityMainBinding
    private lateinit var wordAdapter :WordAdapter
    private var selectedWord: Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        // 추가 ; addButton 누를 시 => Add 페이지로 넘어가기
        binding.addButton.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
//                startActivity(it)
                updateAddWordResult.launch(it)
            }
        }

        // 삭제
        binding.deleteImageView.setOnClickListener {
            delete()
        }

        // 수정
        binding.editImageView.setOnClickListener {
            edit()
        }
    }

    private fun initRecyclerView() {
//        val dummyList = mutableListOf(
//            Word("guitar", "기타", "명사"),
//            Word("smile", "미소", "명사"),
//            Word("run", "실행하다", "동사"),
//            Word("fast", "빠른", "형용사"),
//        )

        wordAdapter = WordAdapter(mutableListOf(), this)
        binding.wordRecyclerView.apply {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

            // item들 사이에 'divider'(구분선) 추가하기
            val dividerItemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

        Thread {
            val list = AppDatabase.getInstance(this)?.wordDao()?.getAll() ?: emptyList()
//            Thread.sleep(1000)
            wordAdapter.list.addAll(list)
            runOnUiThread {
                wordAdapter.notifyDataSetChanged()

            }
        }.start()
    }

    private val updateAddWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val isUpdated = result.data?.getBooleanExtra("isUpdated", false) ?: false

        if(result.resultCode == RESULT_OK && isUpdated) {
            updateAddWord()
        }
    }

    private fun updateAddWord() {
        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.getLatestWord()?.let { word ->
                wordAdapter.list.add(0, word)
                runOnUiThread {
                    wordAdapter.notifyDataSetChanged()
                }
            }
        }.start()
    }

    private fun delete() {
        if(selectedWord == null) return

        Thread {
            selectedWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.delete(word)
                runOnUiThread {
                    wordAdapter.list.remove(word)
                    wordAdapter.notifyDataSetChanged()

                    binding.textTextView.text = ""
                    binding.meanTextView.text = ""

                    Toast.makeText(this, "삭제되었습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    private val updateEditWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val editedWord = result.data?.getParcelableExtra<Word>("editedWord")

        if(result.resultCode == RESULT_OK && editedWord != null) {
            updateEditWord(editedWord)
        }
    }

    private fun updateEditWord(word: Word) {
        val index = wordAdapter.list.indexOfFirst { it.id == word.id }
        wordAdapter.list[index] = word  // 데이터 업데이트
        runOnUiThread { // UI 업데이트
            wordAdapter.notifyItemChanged(index)
            
            // 위의 TextView들의 내용도 '수정된' word의 정보로 변경
            selectedWord = word
            binding.textTextView.text = word.text
            binding.meanTextView.text = word.mean
        }
    }

    private fun edit() {
        if (selectedWord == null) return

        val intent = Intent(this, AddActivity::class.java).putExtra("originWord", selectedWord)
        updateEditWordResult.launch(intent)
    }

    override fun onClick(word: Word) {
        selectedWord = word
        binding.textTextView.text = word.text
        binding.meanTextView.text = word.mean
//        Toast.makeText(this, "${word.text} 가 클릭 됐습니다", Toast.LENGTH_SHORT).show()
    }
}