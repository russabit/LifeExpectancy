package ru.ruslan.life_expectancy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_results.*
import org.threeten.bp.LocalDate
import org.threeten.bp.chrono.ChronoLocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import ru.ruslan.life_expectancy.Model.SharedViewModel

import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.utils.Country
import kotlin.math.exp

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

    private val viewModel : SharedViewModel by activityViewModels()

    private var gender : Boolean = false
    private var country : Country = Country()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("d.M.yyyy")
        val today: String = current.format(formatter)

        viewModel.getCountry().observe(viewLifecycleOwner,
            Observer { t ->
                results_country.text = t.countryName
                country = t
            })

        viewModel.getGender().observe(viewLifecycleOwner,
            Observer { t -> if (t)
            { results_gender.text = "male"
                gender = true}
            else results_gender.text = "female" })

        viewModel.getDateOfBirth().observe(viewLifecycleOwner,
            Observer { t ->
                results_age.text = t.toString()
                val birthday = LocalDate.parse(t.toString(), formatter)
                val numberOfPassedDays = ChronoUnit.DAYS.between(birthday, current)
                results_text.text = "Today ($today) is your ${numberOfPassedDays}th day!"
                val expectedAge = if (gender) country.maleYears else country.femaleYears
                results_years_left.text = "You are expected to live $expectedAge years in your country"
                val expectedDaysFromAge = expectedAge.toLong() * 365.2425
                val numberOfDaysLeft = expectedDaysFromAge - numberOfPassedDays
                results_days_left.text = "i.e. you have approx ${numberOfDaysLeft.toInt()} more days to live"
                val expected_date = birthday.plusYears(expectedAge.toLong())
                results_date_of_death.text = "you are expected to live at least till ${expected_date.year}"
            })
}
}
