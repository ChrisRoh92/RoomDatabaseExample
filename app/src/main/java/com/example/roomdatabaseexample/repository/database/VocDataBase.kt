package com.example.roomdatabaseexample.repository.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Voc::class],version = 1, exportSchema = false)
abstract class VocDataBase():RoomDatabase()
{
    abstract val vocDao:VocDao

    companion object{

        @Volatile
        private var INSTANCE:VocDataBase? = null

        fun createInstance(application: Application):VocDataBase
        {
            synchronized(this)
            {
                var instance = INSTANCE
                if(instance == null)
                {
                    instance = Room.databaseBuilder(
                        application.applicationContext,
                        VocDataBase::class.java,
                        "voc_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}