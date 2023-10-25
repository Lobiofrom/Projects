package com.example.kinopoisk.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kinopoisk.entity.dBCollection.Collection
import com.example.kinopoisk.entity.dBCollection.CollectionMovieCrossRef
import com.example.kinopoisk.entity.dBCollection.CollectionWithMovies
import com.example.kinopoisk.entity.dBCollection.MovieCollection
import com.example.kinopoisk.entity.dBCollection.MovieId
import com.example.kinopoisk.utils.MovieIdListConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        MovieCollection::class,
        Collection::class,
        MovieId::class,
        CollectionMovieCrossRef::class
    ],
    version = 6,
    exportSchema = false
)
@TypeConverters(MovieIdListConverter::class)

abstract class AppDataBase : RoomDatabase() {
    abstract fun collectionDao(): MovieCollectionDao

    private class DBCallBack(
        private val scope: CoroutineScope
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE.let {
                scope.launch {
                    val dao = it?.collectionDao()
                    dao?.deleteAllCollectionsWithMovies()

                    val viewed = Collection(collectionName = "Viewedddddd")
                    val like = Collection(collectionName = "Likeeeeee")
                    val wantToWatch = Collection(collectionName = "WantToWatch-2")

                    val emptyList1 = mutableListOf(MovieId(movieId = 11), MovieId(movieId = 22))
                    val emptyList2 = mutableListOf(MovieId(movieId = 33), MovieId(movieId = 111))
                    val emptyList3 = mutableListOf(MovieId(movieId = 11), MovieId(movieId = 3))


                    val viewedCollection =
                        CollectionWithMovies(collection = viewed, movies = emptyList1)
                    val likedCollection =
                        CollectionWithMovies(collection = like, movies = emptyList2)
                    val toWatchCollection =
                        CollectionWithMovies(collection = wantToWatch, movies = emptyList3)

                    dao?.addCollectionWithMovies(viewed, emptyList1)
                    dao?.addCollectionWithMovies(like, emptyList2)
                    dao?.addCollectionWithMovies(wantToWatch, emptyList3)
                    dao?.addCollection(
                        MovieCollection(
                        collectionName = "first",
                        movieIdList = mutableListOf(1,2,3,5)
                    )
                    )
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "db"
                )
                    .addCallback(DBCallBack(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}