package space.stanton.technicaltask.data.local


import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.viewbinding.BuildConfig
import space.stanton.technicaltask.data.models.PostsResponse
import java.util.concurrent.Executors


@Database(
    entities = [PostsResponse.Post::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDAO(): PostDAO

    companion object {
        val DATABASE_NAME: String = "db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .setQueryCallback({ sqlQuery, bindArgs ->
                    if (BuildConfig.DEBUG) {
                        Log.d("query", sqlQuery)
                    }
                }, Executors.newSingleThreadExecutor())
                .build()
    }
}