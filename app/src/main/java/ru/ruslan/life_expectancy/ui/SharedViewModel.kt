package ru.ruslan.life_expectancy.ui

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ruslan.life_expectancy.data.PersonRepository
import ru.ruslan.life_expectancy.domain.SavedPersonEntity
import ru.ruslan.life_expectancy.data.CountriesListCreator
import ru.ruslan.life_expectancy.domain.Country

class SharedViewModel(private val repository: PersonRepository, application: Application) :
    AndroidViewModel(application) {

    val allPersons: LiveData<List<SavedPersonEntity>> = repository.allPersons

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(personEntity: SavedPersonEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(personEntity)
    }

    fun delete(personEntity: SavedPersonEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(personEntity)
    }

    val countriesList: ArrayList<Country> = CountriesListCreator.getCountriesList()

    private var selectedGender = MutableLiveData<Boolean>()
    private var selectedCountry = MutableLiveData<Country>()
    private var selectedDateOfBirth = MutableLiveData<Any>()
    private var savedPerson = MutableLiveData<SavedPersonEntity>()

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

    fun setSavedPerson(savedPersonEntity: SavedPersonEntity) {
        this.savedPerson.value = savedPersonEntity
    }

    fun getSavedPerson(): MutableLiveData<SavedPersonEntity> {
        return savedPerson
    }

}