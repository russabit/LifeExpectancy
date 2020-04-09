package ru.ruslan.life_expectancy.Model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ruslan.life_expectancy.utils.CountriesListCreator
import ru.ruslan.life_expectancy.utils.Country

class SharedViewModel : ViewModel() {

    val countriesList : ArrayList<Country> = CountriesListCreator.getCountriesList()
    val arrayListSavedPerson = ArrayList<SavedPerson>()

    private var selectedGender = MutableLiveData<Boolean>()
    private var selectedCountry = MutableLiveData<Country>()
    private var selectedDateOfBirth = MutableLiveData<Any>()
    private var savedPersons = MutableLiveData<ArrayList<SavedPerson>>()

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

    fun setSavedPersons(savedPerson: SavedPerson) {
        arrayListSavedPerson.add(savedPerson)
        savedPersons.value = arrayListSavedPerson
    }

    fun getSavedPersons(): MutableLiveData<ArrayList<SavedPerson>> {
        return savedPersons
    }

}