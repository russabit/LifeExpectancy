package ru.ruslan.life_expectancy.Model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val selectedGender = MutableLiveData<Any>()
    private val selectedCountry = MutableLiveData<Any>()
    private val selectedDateOfBirth = MutableLiveData<Any>()

    fun setGender(gender: String) {
        selectedGender.value = gender
    }

    fun setCountry(country: String) {
        selectedCountry.value = country
    }

    fun setDateOfBirth(dateOfBirth: String) {
        selectedDateOfBirth.value = dateOfBirth
    }


}