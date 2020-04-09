package ru.ruslan.life_expectancy.Model

import ru.ruslan.life_expectancy.utils.Country

data class SavedPerson(val name: String, val dateOfBirth: String, val gender: Boolean, val country: Country)