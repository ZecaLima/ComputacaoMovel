package com.example.lab_per_2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lab_per_2.dao.PersonDao
import com.example.lab_per_2.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.personDao())
                }
            }
        }

        suspend fun populateDatabase(personDao: PersonDao) {
            // Delete all content here.
            personDao.deleteAll()

            // Add sample words.
            var nome = Person("Quim")
            personDao.insert(nome)
            nome = Person("Berto")
            personDao.insert(nome)

            // TODO: Add your own words!
        }
    }

    companion object{

        @Volatile
        private var INSTANCE: PersonRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope): PersonRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonRoomDatabase::class.java,
                    "person_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }


        }
    }
}

