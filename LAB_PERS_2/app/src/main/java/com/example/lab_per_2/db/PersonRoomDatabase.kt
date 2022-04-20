package com.example.lab_per_2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab_per_2.dao.PersonDao
import com.example.lab_per_2.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object{

        @Volatile
        private var INSTANCE: PersonRoomDatabase? = null

        fun getDatabase(context:Context): PersonRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonRoomDatabase::class.java,
                    "person_database"
                ).build()
                INSTANCE = instance
                instance
            }


        }
    }
}