package com.example.lab_per_2.repository

import androidx.annotation.WorkerThread
import com.example.lab_per_2.dao.PersonDao
import com.example.lab_per_2.model.Person
import kotlinx.coroutines.flow.Flow

class PersonRepository(private val personDao: PersonDao) {

    val allPeople: Flow<List<Person>> = personDao.getOrderedPeople()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(person: Person) {
        personDao.insert(person)
    }
}