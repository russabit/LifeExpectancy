package ru.ruslan.life_expectancy.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.ruslan.life_expectancy.Model.SavedPerson

@Dao
interface PersonDao {

    @Query("SELECT * from persons")
    fun getNames() : LiveData<List<SavedPerson>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(savedPerson: SavedPerson)

    @Query("DELETE FROM persons")
    suspend fun deleteAll()

    @Delete
    fun deletePerson(savedPerson: SavedPerson) //person as a parameter?
}