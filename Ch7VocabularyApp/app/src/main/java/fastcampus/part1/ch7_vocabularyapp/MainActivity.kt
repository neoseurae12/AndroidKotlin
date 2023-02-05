package fastcampus.part1.ch7_vocabularyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.part1.ch7_vocabularyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding :ActivityMainBinding
    private lateinit var wordAdapter :WordAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        // addButton 누를 시 => Add 페이지로 넘어가기
        binding.addButton.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
                startActivity(it)
            }
        }
    }

    private fun initRecyclerView() {
        val dummyList = mutableListOf(
            Word("guitar", "기타", "명사"),
            Word("smile", "미소", "명사"),
            Word("run", "실행하다", "동사"),
            Word("fast", "빠른", "형용사"),
        )

        wordAdapter = WordAdapter(dummyList, this)
        binding.wordRecyclerView.apply {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

            // item들 사이에 'divider'(구분선) 추가하기
            val dividerItemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onClick(word: Word) {
        Toast.makeText(this, "${word.text} 가 클릭 됐습니다", Toast.LENGTH_SHORT).show()
    }
}