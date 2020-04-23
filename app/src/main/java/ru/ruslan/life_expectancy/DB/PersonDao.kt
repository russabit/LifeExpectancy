package ru.ruslan.life_expectancy.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.ruslan.life_expectancy.Model.SavedPersonEntity

@Dao
interface PersonDao {

    @Query("SELECT * from persons")
    fun getNames() : LiveData<List<SavedPersonEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(savedPersonEntity: SavedPersonEntity)

    @Query("DELETE FROM persons")
    suspend fun deleteAll()

    @Delete
    fun deletePerson(savedPersonEntity: SavedPersonEntity) //person as a parameter?
}