package fastcampus.part1.ch7_vocabularyapp

import androidx.room.Entity
import androidx.room.PrimaryKey

// data class => 데이터를 홀딩하기 위한 클래스

@Entity(tableName = "word")
data class Word(
    val text: String,
    val mean: String,
    val type: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)