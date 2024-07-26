package com.example.letscheck.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.letscheck.data.classes.input.CheckBoxTitle
import com.example.letscheck.data.classes.input.CheckList
import com.example.letscheck.data.classes.input.UserActivity
import com.example.letscheck.data.classes.input.UserEntity

@Database(entities = [
        UserActivity::class,
        UserEntity::class,
        CheckList::class,
        CheckBoxTitle::class], version = 1
)
abstract class MainDb : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        fun createDatabase(context: Context): MainDb{
            return Room
                .databaseBuilder(context, MainDb::class.java, "Main_database")
                .build()
        }
    }
}

//class RoomInitializer(private val userDao: Dao): RoomDatabase.Callback() {
//    private val scope = CoroutineScope(SupervisorJob())
//    override fun onOpen(db: SupportSQLiteDatabase) {
//        super.onOpen(db)
//        scope.launch(Dispatchers.IO) {
//            DataLoader(userDao)
//        }
//    }
//
//}
