package ru.ruslan.life_expectancy.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.recyclerview_listitem.view.*
import ru.ruslan.life_expectancy.Model.Country
import ru.ruslan.life_expectancy.Model.SharedViewModel
import ru.ruslan.life_expectancy.OnNextFragment

import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.adapters.RecyclerAdapter
import ru.ruslan.life_expectancy.utils.CountriesListCreator
import timber.log.Timber

class CountryFragment : Fragment() {

    lateinit var adapter: RecyclerAdapter

    private val model: SharedViewModel by viewModels()

    private lateinit var listener: OnNextFragment

    companion object {
        fun newInstance() = CountryFragment()
    }

    //private lateinit var viewModel: CountryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = list_of_countries

        adapter = RecyclerAdapter(context, CountriesListCreator.getCountriesList())
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
                Timber.d("Clicked inside")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                imageViewCountry.visibility = View.GONE
            }
        })
    }

    fun filter(text: String) {

        val filteredCountriesList = ArrayList<Country>()
        val countriesList: ArrayList<Country> = CountriesListCreator.getCountriesList()

        for (eachCountry in countriesList) {
            if (eachCountry.countryName.contains(text, true)) {
                filteredCountriesList.add(eachCountry)
                Timber.d("added")
            }
        }
        adapter.filterList(filteredCountriesList)
    }

    /*    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNextFragment) {
            listener = context
        } else {
            throw ClassCastException(
                "$context must implement OnNextFragment."
            )
        }
    }
}
