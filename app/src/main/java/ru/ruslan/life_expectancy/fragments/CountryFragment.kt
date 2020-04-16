package ru.ruslan.life_expectancy.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import kotlinx.android.synthetic.main.fragment_country.*
import ru.ruslan.life_expectancy.Model.SharedViewModel
import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.adapters.CountriesRecyclerAdapter
import ru.ruslan.life_expectancy.utils.Country

class CountryFragment : Fragment(), CountriesRecyclerAdapter.OnViewListener {

    lateinit var adapter: CountriesRecyclerAdapter
    private lateinit var listener: OnNextFragment
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var countriesList: ArrayList<Country>

    companion object {
        fun newInstance() = CountryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = list_of_countries

        countriesList = viewModel.countriesList
        countriesList.sortBy { it.countryName }

        adapter = CountriesRecyclerAdapter(activity, countriesList, this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterByTypingTheName(s.toString())
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
            }
        }
        adapter.filterList(filteredCountriesList)
    }

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
        viewModel.setCountry(adapter.countries[position])
        listener.onNextFragment(AllFragmentNames.RESULT)
    }
}
