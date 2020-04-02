package ru.ruslan.life_expectancy.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import kotlinx.android.synthetic.main.fragment_country.*
import ru.ruslan.life_expectancy.Model.SharedViewModel

import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.adapters.RecyclerAdapter
import ru.ruslan.life_expectancy.utils.CountriesListCreator
import ru.ruslan.life_expectancy.utils.Country
import timber.log.Timber

class CountryFragment : Fragment(), RecyclerAdapter.OnViewListener{

    lateinit var adapter: RecyclerAdapter
    private val countriesList : ArrayList<Country> = CountriesListCreator.getCountriesList()
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

        countriesList.sortBy { it.countryName }

        adapter = RecyclerAdapter(context, countriesList, this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterByTypingTheName(s.toString())
                Timber.d("Clicked inside")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                imageViewCountry.visibility = View.GONE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        list_of_countries.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                imageViewCountry.visibility = View.GONE
            }
        })
    }

    fun filterByTypingTheName(text: String) {

        val filteredCountriesList = ArrayList<Country>()

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

    override fun onViewClick(position: Int) {

        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.setCountry(adapter.countries[position].countryName)
        Timber.d("country's number is $position")
        listener.onNextFragment(AllFragments.RESULT)
    }
}
