package ru.ruslan.life_expectancy.Data

import androidx.lifecycle.LiveData
import ru.ruslan.life_expectancy.Domain.SavedPersonEntity

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class PersonRepository(private val personDao: PersonDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPersons: LiveData<List<SavedPersonEntity>> = personDao.getNames()

    suspend fun insert(personEntity: SavedPersonEntity) {
        personDao.insert(personEntity)
    }

    suspend fun delete(personEntity: SavedPersonEntity) {
        personDao.deletePerson(personEntity)
    }
}