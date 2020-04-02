package ru.ruslan.life_expectancy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_results.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import ru.ruslan.life_expectancy.Model.SharedViewModel

import ru.ruslan.life_expectancy.R

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

/*    private lateinit var viewModel: ResultsViewModel*/
    private lateinit var viewModel : SharedViewModel

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

        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("d.M.yyyy")
        val today: String =  current.format(formatter)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        viewModel.getCountry().observe(viewLifecycleOwner,
            Observer { t -> results_country.text = t.toString() })

        viewModel.getGender().observe(viewLifecycleOwner,
            Observer { t -> if (t) results_gender.text = "male" else results_gender.text = "female"})

        viewModel.getDateOfBirth().observe(viewLifecycleOwner,
            Observer { t ->
                results_age.text = t.toString()
                val birthday = LocalDate.parse(t.toString(), formatter)
                val numberOfPassedDays = ChronoUnit.DAYS.between(birthday, current)
                results_text.text = "Today ($today) is your ${numberOfPassedDays}th day!"
            })
}
}
