package ru.ruslan.life_expectancy.Domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class SavedPersonEntity(
    @PrimaryKey val name: String,
    val birthday: String,
    val gender: Boolean,
    val country: String
)