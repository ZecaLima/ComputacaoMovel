package com.example.lab_per_2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.lab_per_2.model.Person
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDao {

    @Query("SELECT * From person_table ORDER BY name")
    fun getOrderedPeople(): Flow<List<Person>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM person_table")
    suspend fun deleteAll()
}