package com.example.changeyourhabit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PointDate::class], version = 1, exportSchema = false)
abstract class PointDatabase : RoomDatabase() {
    abstract val pointDao: PointDao

    companion object{
        @Volatile
        private var INSTANCE: PointDatabase? = null

        fun getInstance(context: Context): PointDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PointDatabase::class.java,
                        "points_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}

