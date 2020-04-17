package ru.ruslan.life_expectancy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_results.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import ru.ruslan.life_expectancy.Model.SharedViewModel

import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.utils.Country
import kotlin.math.roundToLong

class SavedResultsFragment : Fragment() {

    companion object {
        fun newInstance() = SavedResultsFragment()
    }

    private val viewModel: SharedViewModel by activityViewModels()
    private var birthDate: String = ""
    private var gender: Boolean = false
    private var country: Country = Country(1)

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

        to_save.visibility = View.GONE
        edit_text_name.visibility = View.GONE
        save_button.visibility = View.GONE
        results_age.visibility = View.GONE
        results_gender.visibility = View.GONE

        viewModel.getSavedPerson().observe(viewLifecycleOwner, Observer { t ->
            country = viewModel.countriesList.find { it.countryName == t.country }!!
            results_country.text = "${country.countryName} is ranked #${country.rank}"
            if (t.gender) gender = true

            birthDate = t.birthday

            val birthday = LocalDate.parse(t.birthday, formatter)
            val numberOfPassedDays = ChronoUnit.DAYS.between(birthday, current)
            results_text.text = "Today ($today) is your ${numberOfPassedDays}th day!"
            val expectedAge = if (gender) country.maleYears else country.femaleYears
            results_years_left.text =
                "You are expected to live $expectedAge years in your country"
            val expectedDaysFromAge = expectedAge.toLong() * 365.2425
            val numberOfDaysLeft = expectedDaysFromAge - numberOfPassedDays
            results_days_left.text =
                "i.e. you have approx ${numberOfDaysLeft.toInt()} more days (${(numberOfDaysLeft / 365.2425).roundToLong()} years) to live"
            val expectedDate = birthday.plusYears(expectedAge.toLong())
            results_date_of_death.text =
                "That means you are expected to live at least till ${expectedDate.year}"

        })
    }
}
