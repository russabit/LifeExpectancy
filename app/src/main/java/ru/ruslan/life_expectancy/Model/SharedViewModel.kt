package ru.ruslan.life_expectancy.Model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private var selectedGender = MutableLiveData<Boolean>()
    private var selectedCountry = MutableLiveData<Any>()
    private var selectedDateOfBirth = MutableLiveData<Any>()

    //true - male, false - female
    fun setGender(gender: Boolean) {
        selectedGender.value = gender
    }

    fun getGender(): MutableLiveData<Boolean> {
        return selectedGender
    }

    fun setCountry(country: String) {
        selectedCountry.value = country
    }

    fun getCountry(): MutableLiveData<Any> {
        return selectedCountry
    }

    fun setDateOfBirth(dateOfBirth: String) {
        selectedDateOfBirth.value = dateOfBirth
    }

    fun getDateOfBirth(): MutableLiveData<Any> {
        return selectedDateOfBirth
    }

}