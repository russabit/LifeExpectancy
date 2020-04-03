package ru.ruslan.life_expectancy.Model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ruslan.life_expectancy.utils.CountriesListCreator
import ru.ruslan.life_expectancy.utils.Country

class SharedViewModel : ViewModel() {

    val countriesList : ArrayList<Country> = CountriesListCreator.getCountriesList()

    private var selectedGender = MutableLiveData<Boolean>()
    private var selectedCountry = MutableLiveData<Country>()
    private var selectedDateOfBirth = MutableLiveData<Any>()

    //true - male, false - female
    fun setGender(gender: Boolean) {
        selectedGender.value = gender
    }

    fun getGender(): MutableLiveData<Boolean> {
        return selectedGender
    }

    fun setCountry(country: Country) {
        selectedCountry.value = country
    }

    fun getCountry(): MutableLiveData<Country> {
        return selectedCountry
    }

    fun setDateOfBirth(dateOfBirth: String) {
        selectedDateOfBirth.value = dateOfBirth
    }

    fun getDateOfBirth(): MutableLiveData<Any> {
        return selectedDateOfBirth
    }

}