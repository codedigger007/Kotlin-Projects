package com.example.mockexam

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(LagosSecondData::class), version = 1)

abstract class LagosDataBase : RoomDatabase() {
    abstract fun lagosDao(): LagosDao

    companion object {
        @Volatile
        private var INSTANCE: LagosDataBase? = null

        fun getDatabase(context: Context): LagosDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    LagosDataBase::class.java,
                    "app_database").build()
                INSTANCE = instance

                instance
            }
        }
    }
}