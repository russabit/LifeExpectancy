package ru.ruslan.life_expectancy.Model

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ruslan.life_expectancy.DB.PersonRoomDatabase
import ru.ruslan.life_expectancy.PersonRepository
import ru.ruslan.life_expectancy.utils.CountriesListCreator
import ru.ruslan.life_expectancy.utils.Country

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PersonRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allPersons:LiveData<List<SavedPerson>>
    init {
        val personDao = PersonRoomDatabase.getDatabase(getApplication(), viewModelScope).personDao()
        repository = PersonRepository(personDao)
        allPersons = repository.allPersons
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(person: SavedPerson) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(person)
    }

    val countriesList: ArrayList<Country> = CountriesListCreator.getCountriesList()
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