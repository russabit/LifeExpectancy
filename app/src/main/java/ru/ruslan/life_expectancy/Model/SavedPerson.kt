package ru.ruslan.life_expectancy.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.ruslan.life_expectancy.utils.Country

@Entity(tableName = "persons")
data class SavedPerson(
    @PrimaryKey val name: String,
    val birthday: String,
    val gender: Boolean,
    val country: Country
)