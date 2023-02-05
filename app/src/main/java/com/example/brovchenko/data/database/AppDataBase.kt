package com.example.brovchenko.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilmDbModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

   // abstract fun filmDetailDao(): FilmDetailDao
    abstract fun filmDao():FilmDao

    companion object {

        private const val DB_NAME = "shop_item_db"
        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()


        fun getInstance(application: Application): AppDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}