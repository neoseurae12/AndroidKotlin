package fastcampus.part1.ch7_vocabularyapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // Dao 가져오기
    abstract fun wordDao(): WordDao

    // 인스턴스 가져오기
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) { // 오직 '하나만' 만들어지기 위해 =>  synchronized()로 감쌈
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database.db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }
}