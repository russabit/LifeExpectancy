package ru.ruslan.life_expectancy

import androidx.lifecycle.LiveData
import ru.ruslan.life_expectancy.DB.PersonDao
import ru.ruslan.life_expectancy.Model.SavedPerson

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class PersonRepository(private val personDao: PersonDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPersons: LiveData<List<SavedPerson>> = personDao.getNames()

    suspend fun insert(person: SavedPerson) {
        personDao.insert(person)
    }

    suspend fun delete(person: SavedPerson) {
        personDao.deletePerson(person)
    }
}