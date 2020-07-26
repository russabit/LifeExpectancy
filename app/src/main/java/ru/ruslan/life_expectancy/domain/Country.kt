package ru.ruslan.life_expectancy.domain

data class Country(
    val rank: Number,
    val countryName: String = "some country",
    val overallYears: Number = 77,
    val femaleYears: Number = 74.9,
    val maleYears: Number = 70.4
)