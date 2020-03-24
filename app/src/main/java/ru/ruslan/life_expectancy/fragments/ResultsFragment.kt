package ru.ruslan.life_expectancy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_results.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import ru.ruslan.life_expectancy.Model.SharedViewModel

import ru.ruslan.life_expectancy.R
import timber.log.Timber

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

/*    private lateinit var viewModel: ResultsViewModel*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

/*    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResultsViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        var answer: String =  current.format(formatter)

        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        Timber.d(answer)
        results_text.text = "Today ($answer) is your XXXish day!"

        model.getCountry().observe(requireActivity(),
            Observer { t -> results_country.text = t.toString() })

        model.getGender().observe(requireActivity(),
            Observer { t -> if (t) results_gender.text = "male" else results_gender.text = "female"})

        model.getDateOfBirth().observe(requireActivity(),
            Observer { t -> results_age.text = t.toString() })


}
}
